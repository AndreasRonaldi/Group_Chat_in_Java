package Client;

import java.io.*;
import java.net.*;

public class ClientServer implements Runnable {

    static Socket clientSocket = null;
    static BufferedReader is = null;
    static DataOutputStream os = null;

    static String output = null;
    static IncomingReader reader = null;

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
            new Thread(reader).start();
        return res;
    }

    public static Boolean handleSignup(String username, String password) {
        Boolean res = Boolean.parseBoolean(InputOutput("/signup " + username + " " + password));
        if (res)
            new Thread(reader).start();
        return res;
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
                    Client.dashboard.displayMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
