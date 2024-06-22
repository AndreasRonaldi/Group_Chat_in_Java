package Client;

import java.util.Scanner;

import Client.GUI.*;

public class Client {
    static final int SERVER_PORT = 4444;
    static final String SERVER_ADDRESS = "localhost";

    static ClientServer server = null;
    static Dashboard dashboard = null;
    static Chat chat = null;

    public static void main(String[] args) {
        chat = new Chat();
        dashboard = new Dashboard();

        System.out.println("### Starting Client ###");
        server = new ClientServer();
        new Thread(server).start();

        System.out.println("> Open Login Form");
        Login form = new Login();
        form.setVisible(true); // make form visible to the user
        form.pack();
        form.setLocationRelativeTo(null);
    }

    public static void openDashboard() {
        System.out.println("> Open dashboard");
        // if (dashboard)
        dashboard.setVisible(true);
        dashboard.pack();
        dashboard.setLocationRelativeTo(null);
        dashboard.handleRefresh(null);
        dashboard.jLabel3.setText(ClientServer.name);
    }

    public static void closeDashboard() {
        dashboard.setVisible(false);
    }

    public static void openChat() {
        System.out.println("> Open chat");
        chat.setVisible(true);
        chat.pack();
        chat.setLocationRelativeTo(null);
    }

    public static void closeChat() {
        chat.setVisible(false);
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
                    System.out.println("> Stopping Server");
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
        shutDownClient();
    }

    public static void shutDownClient() throws Exception {
        server.stopServer();

        System.out.println("### Client Stopped ###");
    }
}
