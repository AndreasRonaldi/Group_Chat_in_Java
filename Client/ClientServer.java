package Client;

import java.io.*;
import java.net.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import static javax.swing.JOptionPane.showMessageDialog;

public class ClientServer implements Runnable {

    static Socket clientSocket = null;
    static BufferedReader is = null;
    static DataOutputStream os = null;

    static String output = null;
    static IncomingReader reader = null;
    static Thread tReader = null;

    static String name = "???";
    static Group group = null;

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
        startReader();
    }

    public static void handleSendMsg(String input) {
        InputToServer("/chat " + input);
    }

    public static void handleLogin(String username, String password) {
        InputToServer("/login " + username + " " + password);
        ClientServer.name = username;
    }

    public static void handleSignup(String username, String password) {
        InputToServer("/signup " + username + " " + password);
        ClientServer.name = username;
    }

    public static void handleCreateRoom(String name) {
        InputToServer("/addgroup " + name);
    }

    public static void handleRemoveRoom() {
        InputToServer("/removegroup " + group.id);
    }

    public static void handleGetGroupList() {
        InputToServer("/listgroup");
    }

    public static void handleJoinGroup(Group joinGroup) {
        if (joinGroup != null)
            group = joinGroup;

        InputToServer("/joingroup " + group.id);
    }

    public static void handleExitGroup() {
        group = null;
        InputToServer("/exitgroup");
    }

    public static void handleOpenMember() {
        if (group.ownerUsername.equals(name)) {
            Client.openDetailOwner();
        } else {
            Client.openDetailMember();
        }
        InputToServer("/listuseringroup");
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
        reader.shouldRun = false;
        tReader.interrupt();
    }

    private static void InputToServer(String input) {
        try {
            System.out.println("> Send: " + input);
            os.writeBytes(input + "\n");
        } catch (IOException ex) {
            System.out.println("ERROR: writing to server.");
        }

        // try {
        // output = is.readLine();
        // System.out.println("> Read: " + output);
        // return output;
        // } catch (IOException e) {
        // System.out.println("ERROR: reading server intention.");
        // }

        // return "";
    }

    public void stopServer() throws IOException {
        clientSocket.close();
    }

    private class IncomingReader implements Runnable {
        boolean shouldRun = true;

        @Override
        public void run() {
            try {
                String message;
                while ((message = is.readLine()) != null && shouldRun) {
                    System.out.println("> Read: " + message);
                    handleInputFromServer(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createWarningMsg(String msg) {
        JOptionPane opt = new JOptionPane(msg, JOptionPane.WARNING_MESSAGE,
                JOptionPane.DEFAULT_OPTION, null, new Object[] {}); // no buttons
        final JDialog dlg = opt.createDialog("Error");
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000);
                    dlg.dispose();

                } catch (Throwable th) {

                }
            }
        }).start();
        dlg.setVisible(true);
    }

    public static void handleInputFromServer(String input) {
        String inputs[] = input.split(" ", 2);
        String action = inputs[0];

        Object res;
        Object[] arrRes;

        Boolean bolres;

        switch (action) {
            case "/login":
                res = inputs[1];
                if (Boolean.valueOf("" + res)) // login success
                {
                    Client.closeLogin();
                    Client.closeSignup();
                    Client.openDashboard();
                } else
                    showMessageDialog(null, "Wrong Username/Password");
                break;
            case "/signup":
                res = inputs[1];
                if (!Boolean.valueOf("" + res)) // signup unsuccessful
                    showMessageDialog(null, "Username is taken");
                break;
            case "/addgroup":
                res = inputs[1];
                if (Boolean.valueOf("" + res)) {
                    showMessageDialog(null, "Room created");
                    handleGetGroupList();
                } else {
                    showMessageDialog(null, "Room can't contained special character");
                    Client.dashboard.handleCreateGroup(null);
                }
                break;
            case "/removegroup":
                res = inputs[1];
                if (!Boolean.valueOf("" + res)) {
                    createWarningMsg("You are not the owner of the group");
                }
                break;
            case "/kickuser":
                // TODO:
                break;
            case "/listgroup":
                Client.dashboard.handleChangeModelList(inputs[1]);
                break;
            case "/joingroup":
                bolres = Boolean.valueOf("" + inputs[1]);
                if (bolres) {
                    Client.openChat();
                    Client.closeDashboard();
                } else {
                    showMessageDialog(null, "Group chat is not found, Please Refresh List");
                }
                break;
            case "/exitgroup":
                res = inputs[1];
                switch ("" + res) {
                    case "kick":
                        createWarningMsg("You have been kicked!");
                        break;
                    case "remove":
                        createWarningMsg("The Group has been disband.");
                        break;
                    default:
                        break;
                }
                Client.closeChat();
                Client.openDashboard();
                break;
            case "/listuseringroup":
                Client.detailMember(inputs[1]);
                break;
            case "/chat":
                Client.chat.displayMessage(inputs[1]);
                break;
            case "/exit":
                createWarningMsg("Server is Closing...");
                Client.shutDownClient();
                break;
            default:
                System.out.println("> ERROR: Something is send? [" + input + "]");
                break;
        }
    }
}
