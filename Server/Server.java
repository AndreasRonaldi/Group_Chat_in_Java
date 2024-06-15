package Server;

import java.io.*;
import java.net.*;
import java.util.*;

// import java.util.logging.Level;
// import java.util.logging.Logger;

public class Server {
    static final int PORT = 4444; // port buat mulai server
    static final String ADDRESS = "localhost";

    private static Set<String> usernames = new HashSet<>();
    private static Set<User> users = new HashSet<>();
    private static Set<ServerUser> activeUser = new HashSet<>();
    public static ArrayList<ServerUser> allUsersCon = new ArrayList<>();
    public static ArrayList<Group> groups = new ArrayList<>();

    static ServerNewCon serverNewCon = null;

    public static void main(String[] args) {
        // Create Server Thread then run it
        System.out.println("### Starting Server ###");
        serverNewCon = new ServerNewCon(); // server for accepting new connections
        new Thread(serverNewCon).start();

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
            System.out.println("> Removed User " + userCon.user.username);
        userCon.stopUser();
        allUsersCon.remove(userCon);
    }

    private static void listedForInput() throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean shouldRun = true;

        while (shouldRun) {
            while (!sc.hasNextLine()) {
                Thread.sleep(1);
            }
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
                    System.out.println(usernames.toString());
                    break;
                case "LIST ACTIVE USER":
                    for (ServerUser Su : activeUser)
                        System.out.print(Su.user.username + ", ");
                    System.out.println();
                    break;
                case "SENDALL":
                    System.out.print("Message?: ");
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
            user.stopUser();
        }

        allUsersCon.clear();
        serverNewCon.stopServer();

        System.out.println("### Server Stopped ###");
    }

    // Can be implemented in Database
    public static boolean addNewUser(String username, String password) {
        if (usernames.contains(username))
            return false;

        System.out.println("> Create User: " + username);
        usernames.add(username);
        User newUser = new User(username, password);
        users.add(newUser);
        return true;
    }

    public static boolean login(String username, String password, ServerUser userCon) {
        for (User user : users) {
            if (user.isItThisUser(username, password)) {
                System.out.println("> [" + username + "] logged in.");
                userCon.setUser(user);
                activeUser.add(userCon);
                return true;
            }
        }

        System.out.println("> [new]: wrong login");
        return false;
    }

    public static void logout(ServerUser user) {
        activeUser.remove(user);
    }

    public static void createGroup(String name) {
        Group newGroup = new Group(name);
        groups.add(newGroup);
    }

    public static void removeGroup() {
    }

    public static void listOfGroup() {

    }

    public static void addUserToGroup(ServerUser user, Group group) {
        user.setGroup(group);
        group.addUserCon(user);
    }

    public static void removeUserFromGroup(ServerUser user) {
        user.group.removeUserCon(user);
        user.setGroup(null);
    }
}