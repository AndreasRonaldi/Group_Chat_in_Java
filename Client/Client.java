package Client;

import java.util.Scanner;

import Client.GUI.*;

public class Client {
    static final int SERVER_PORT = 4444;
    static final String SERVER_ADDRESS = "localhost";

    static ClientServer server = null;
    static Dashboard dashboard = null;

    public static void main(String[] args) {
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
        System.out.println("open dashboard");
        dashboard.setVisible(true);
        dashboard.pack();
        dashboard.setLocationRelativeTo(null);
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
