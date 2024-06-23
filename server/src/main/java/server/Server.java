package server;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// import java.util.logging.Level;
// import java.util.logging.Logger;

public class Server {
    static final int PORT = 4444; // port buat mulai server
    static final String ADDRESS = "localhost";

    static final String DB_NAME = "group_chat";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "";

    // private static Set<String> usernames = new HashSet<>();
    // private static Set<User> users = new HashSet<>();
    private static Set<ServerUser> activeUser = new HashSet<>();
    private static ArrayList<ServerUser> allUsersCon = new ArrayList<>();
    private static HashMap<Integer, ArrayList<ServerUser>> groups = new HashMap<>();

    static ServerNewCon serverNewCon = null;
    static ServerSQL serverSQL = null;

    public static void main(String[] args) {
        // Create Server Thread then run it
        System.out.println("### Starting Server ###");
        serverNewCon = new ServerNewCon(); // server for accepting new connections
        new Thread(serverNewCon).start();

        serverSQL = new ServerSQL(); // connect to database mysql xampp
        new Thread(serverSQL).start();

        // Get input in server
        try {
            listedForInput();
        } catch (Exception ex) {
            System.out.println("ERROR: Something's wrong when listing to server input");
            // Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addUserConnection(Socket userSocket) throws IOException {
        ServerUser user = new ServerUser(userSocket);
        allUsersCon.add(user);
        new Thread(user).start();
    }

    public static void removeUserConnection(ServerUser userCon) throws IOException {
        if (userCon.user != null)
            System.out.println("> Removed User " + userCon.user);
        userCon.stopUser();
        activeUser.remove(userCon);
        allUsersCon.remove(userCon);
    }

    private static void listedForInput() throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean shouldRun = true;

        while (shouldRun) {
            while (!sc.hasNextLine())
                Thread.sleep(1);

            String input = sc.nextLine();

            // Handle Server Input
            switch (input.toUpperCase()) {
                case "EXIT":
                    System.out.println("> Stopping Server");
                    shouldRun = false;
                    break;
                case "PING":
                    System.out.println("PONG");
                    break;
                case "LIST USER":
                    // System.out.println(usernames.toString());
                    ServerSQL.listOfUser();
                    break;
                case "LIST ACTIVE USER":
                    for (ServerUser Su : activeUser)
                        System.out.println(" " +
                                Su.user + "[" + (Su.group != null ? Su.group : "...") + "]");
                    break;
                case "LIST GROUP":
                    System.out.println(ServerSQL.findAllGroup());
                    break;
                case "LIST GROUP USER":
                    for (ArrayList<ServerUser> asu : groups.values()) {
                        System.out.println(asu);
                    }
                    break;
                case "SENDALL":
                    System.out.print("Message?: ");
                    while (!sc.hasNextLine())
                        Thread.sleep(1);
                    input = sc.nextLine();
                    for (ServerUser Su : activeUser)
                        Su.sendMsg("#SERVER#" + input);
                    break;
                default:
                    break;
            }
        }

        sc.close();
        shutDownServer();
    }

    private static void shutDownServer() throws Exception {
        // Send to all client to disconnect :)
        for (ServerUser user : allUsersCon) {
            user.sendMsg("/exit");
        }

        for (ServerUser user : allUsersCon) {
            user.stopUser();
        }

        allUsersCon.clear();
        serverNewCon.stopServer();
        serverSQL.stopSQL();

        System.out.println("### Server Stopped ###");
        System.exit(0);
    }

    public static void sendMsgToGroup(Integer groupId, String msg) {
        for (ServerUser su : groups.get(groupId)) {
            su.sendMsg(msg);
        }
    }

    public static Boolean addNewUser(String username, String password) {
        if (!ServerSQL.insertUser(username, password))
            return false;

        System.out.println("> Create User: " + username);
        return true;
    }

    public static Boolean login(String username, String password, ServerUser userCon) {
        Boolean res = ServerSQL.findUser(username, password);

        if (res) {
            System.out.println("> [" + username + "] logged in.");
            userCon.setUser(username);
            activeUser.add(userCon);
        }

        return res;
    }

    public static Boolean createGroup(String name, String user) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        boolean b = m.find();

        if (b)
            return false;

        Boolean res = ServerSQL.createGroup(name, user);

        return res;
    }

    public static Boolean removeGroup(Integer groupId, String user) {
        String owner = ServerSQL.findGroupOwner(groupId);
        if (!owner.equals(user)) {
            return false;
        }
        if (!ServerSQL.removeGroup(groupId)) {
            return false;
        }
        // TODO:
        sendMsgToGroup(groupId, "/exitgroup remove");
        // groups.get(groupId).clear();
        groups.remove(groupId);

        return true;
    }

    public static String listOfUserInGroup(Integer groupId) {
        if (!groups.containsKey(groupId))
            return "[]";
        return groups.get(groupId).toString();
    }

    public static String listOfGroup() {
        return ServerSQL.findAllGroup();
    }

    public static Boolean addUserToGroup(ServerUser user, Integer groupId) {
        ArrayList<ServerUser> temp = null;

        if (!ServerSQL.isValidGroup(groupId))
            return false;

        if (groups.containsKey(groupId)) {
            groups.get(groupId).add(user);
        } else {
            temp = new ArrayList<>();
            temp.add(user);
            groups.put(groupId, temp);
        }

        user.setGroup(groupId);
        return true;
    }

    public static ServerUser findActiveUser(String name) {
        for (ServerUser su : activeUser)
            if (su.toString().equals(name))
                return su;

        return null;
    }

    public static Boolean removeUserFromGroup(ServerUser user) {
        if (user == null)
            return false;
        groups.get(user.group).remove(user);
        user.setGroup(null);
        return true;
    }
}