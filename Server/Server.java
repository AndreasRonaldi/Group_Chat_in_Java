package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

// import java.util.logging.Level;
// import java.util.logging.Logger;

public class Server {
    static final int PORT = 4444; // port buat mulai server
    static final String ADDRESS = "localhost";

    public static ArrayList<User> users = new ArrayList<>();

    static Thread t0;
    static ServerNewCon serverNewCon = null;

    public static void main(String[] args) {
        // Create Server Thread then run it
        System.out.println("### Starting Server ###");
        serverNewCon = new ServerNewCon(); // server for accepting new connections
        t0 = new Thread(serverNewCon);
        t0.start();

        // Get input in server
        try {
            listedForInput();
        } catch (Exception ex) {
            System.out.println("ERROR: Something's wrong when listing to server input");
            // Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addUser(Socket userSocket) throws IOException {
        User user = new User(userSocket);
        users.add(user);
        new Thread(user).start();
    }

    public static void removeUser(User user) throws IOException {
        System.out.println("# Removed User " + user.name);
        user.stopUser();
        users.remove(user);
    }

    public static void listedForInput() throws Exception {
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
                    System.out.println("# Stopping Server");
                    shouldRun = false;
                    break;
                case "PING":
                    System.out.println("PONG");
                    break;
                default:
                    break;
            }
        }

        sc.close();
        shutDownServer();
    }

    public static void shutDownServer() throws Exception {
        // Send to all client to disconnect :)
        for (User user : users) {
            user.stopUser();
        }

        users.clear();
        serverNewCon.stopServer();

        System.out.println("### Server Stopped ###");
    }
}