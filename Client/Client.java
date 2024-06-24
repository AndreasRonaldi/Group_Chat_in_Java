package Client;

import java.io.IOException;
import java.util.Scanner;

import Client.GUI.*;

public class Client {
    static final int SERVER_PORT = 4444;
    static final String SERVER_ADDRESS = "localhost";

    static ClientServer server = null;
    static Dashboard dashboard = null;
    static Login login = null;
    static Signup signup = null;
    static Chat chat = null;
    static DetailMember dMember = null;
    static DetailOwner dOwner = null;

    public static void main(String[] args) {
        chat = new Chat();
        dashboard = new Dashboard();

        System.out.println("### Starting Client ###");
        server = new ClientServer();
        new Thread(server).start();

        System.out.println("> Open Login Form");
        openLogin();

        // Get input in server
        try {
            listedForInput();
        } catch (Exception ex) {
            System.out.println("ERROR: Something's wrong when listing to server input");
            // Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if (ClientServer.group != null)
            chat.jLabel1.setText(ClientServer.group.name);
    }

    public static void closeChat() {
        chat.jTextArea1.setText("");
        chat.setVisible(false);
    }

    public static void openLogin() {
        if (login == null)
            login = new Login();
        login.setVisible(true); // make login visible to the user
        login.pack();
        login.setLocationRelativeTo(null);
    }

    public static void closeLogin() {
        if (login != null)
            login.dispose();
    }

    public static void openSignup() {
        if (signup == null)
            signup = new Signup();
        signup.setVisible(true);
        signup.pack();
        signup.setLocationRelativeTo(null);
    }

    public static void closeSignup() {
        if (signup != null)
            signup.dispose();
    }

    public static void openDetailMember() {
        if (dMember == null)
            dMember = new DetailMember();
        dMember.setVisible(true);
        dMember.pack();
        dMember.setLocationRelativeTo(null);
    }

    public static void closeDetailMember() {
        dMember.dispose();
        dMember = null;
    }

    public static void openDetailOwner() {
        if (dOwner == null)
            dOwner = new DetailOwner();
        dOwner.setVisible(true);
        dOwner.pack();
        dOwner.setLocationRelativeTo(null);
    }

    public static void closeDetailOwner() {
        dOwner.dispose();
        dOwner = null;
    }

    public static void detailMember(String list) {
        if (dMember != null)
            dMember.handleChangeModelList(list);
        else if (dOwner != null)
            dOwner.handleChangeModelList(list);
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
                    System.out.println("> Stopping Client");
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

    public static void shutDownClient() {
        try {
            server.stopServer();
        } catch (IOException e) {
            System.out.println("ERROR: Closing Server Socket");
        } finally {
            System.out.println("### Client Stopped ###");
            System.exit(0);
        }
    }
}
