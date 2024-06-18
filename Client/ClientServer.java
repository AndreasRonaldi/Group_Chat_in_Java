package Client;

import java.io.*;
import java.net.*;

public class ClientServer implements Runnable {

    static Socket clientSocket = null;
    static BufferedReader is = null;
    static DataOutputStream os = null;

    static String output = null;
    static IncomingReader reader = null;
    static Thread tReader = null;

    static String name = "???";
    static Integer idGroup;

    @Override
    public void run() {
        // Connect to server
        try {
            clientSocket = new Socket(Client.SERVER_ADDRESS, Client.SERVER_PORT);
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new DataOutputStream(clientSocket.getOutputStream());
        } catch (UnknownHostException el) {
            System.out.println("Unknown host: " + el);
        } catch (IOException e2) {
            System.out.println("Error I/O: " + e2);
        }

        reader = new IncomingReader();
    }

    public static void handleSendMsg(String input) {
        try {
            System.out.println("> Send: " + input);
            os.writeBytes(input + "\n");
        } catch (IOException ex) {
            System.out.println("ERROR: writing to server.");
        }
    }

    public static Boolean handleLogin(String username, String password) {
        Boolean res = Boolean.parseBoolean(InputOutput("/login " + username + " " + password));
        if (res)
            name = username;
        return res;
    }

    public static Boolean handleSignup(String username, String password) {
        Boolean res = Boolean.parseBoolean(InputOutput("/signup " + username + " " + password));
        if (res)
            name = username;
        return res;
    }

    public static Boolean handleCreateRoom(String name) {
        Boolean res = Boolean.parseBoolean(InputOutput("/addgroup " + name));
        Client.dashboard.handleRefresh(null);
        return res;
    }

    public static Boolean handleRemoveRoom(Integer id) {
        Boolean res = Boolean.parseBoolean(InputOutput("/removegroup " + id));
        Client.dashboard.handleRefresh(null);
        return res;
    }

    public static String handleGetGroupList() {
        String res = InputOutput("/listgroup");
        return res;
    }

    public static Boolean handleJoinGroup(Integer id) {
        if (idGroup != null)
            return false;
        idGroup = id;
        Boolean res = Boolean.parseBoolean(InputOutput("/joingroup " + id));
        startReader();
        return res;
    }

    public static void handleExitGroup() {
        idGroup = null;
        stopReader();
        InputOutput("/exitgroup");
    }

    public static void startReader() {
        if (tReader == null || tReader.isInterrupted()) {
            System.out.println("> Start Incoming Reader");
            tReader = new Thread(reader);
            tReader.start();
        }
    }

    public static void stopReader() {
        System.out.println("> Stop Incoming Reader");
        // reader.shouldRun = false;
        tReader.interrupt();
    }

    private static String InputOutput(String input) {
        try {
            System.out.println("> Send: " + input);
            os.writeBytes(input + "\n");
        } catch (IOException ex) {
            System.out.println("ERROR: writing to server.");
        }

        try {
            output = is.readLine();
            System.out.println("> Read: " + output);
            return output;
        } catch (IOException e) {
            System.out.println("ERROR: reading server intention.");
        }

        return "";
    }

    public void stopServer() throws IOException {
        clientSocket.close();
    }

    private class IncomingReader implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = is.readLine()) != null) {
                    // System.out.println(message);
                    Client.chat.displayMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
