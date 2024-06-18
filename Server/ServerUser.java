package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
// import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ServerUser extends Thread {

    public User user;
    public Group group;

    private Socket socket;
    private BufferedReader is = null;
    // private PrintWriter pw = null;
    private BufferedWriter bw = null;
    private volatile boolean shouldRun = true;

    public ServerUser(final Socket userSocket) {
        try {
            this.socket = userSocket;
            is = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            // pw = new PrintWriter(socket.getOutputStream(), true);
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
        }
    }

    public void stopUser() throws IOException {
        shouldRun = false;
        is.close();
        // pw.close();
        bw.close();
        socket.close();
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    private Boolean needLoggedIn() {
        if (!isLoggedIn()) {
            sendMsg("You do not have access");
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (shouldRun) {
            try {
                String line = is.readLine();
                if (line.equals("null") || line.trim().equals("")) {
                    sendMsg("");
                } else {
                    if (user != null)
                        System.out.println("> Input [" + this.user.username + "]: " + line);
                    else
                        System.out.println("> Input [new]: " + line);
                    // handle Input
                    handleInput(line);
                }
            } catch (IOException e) {
                if (user != null)
                    System.out.println("> Disconnected: " + this.user.username);
                else
                    System.out.println("> Disconnected: new guy");

                try {
                    Server.removeUserConnection(this);
                } catch (Exception e1) {
                    System.out.println("ERROR: Something wrong when deleting user");
                }
            }
        }
    }

    private void handleInput(String input) throws IOException {
        String inputs[] = input.split(" ", 3);
        String action = inputs[0];

        Object output = false;
        Object[] arrOutput = null;

        // System.out.println(Arrays.toString(inputs));

        switch (action) {
            case "/signup":
                output = Server.addNewUser(inputs[1], inputs[2]);
                if (!(Boolean) output) { // username taken
                    sendMsg("" + output);
                    break;
                }
            case "/login":
                output = Server.login(inputs[1], inputs[2], this);
                sendMsg("" + output);
                break;
            case "/addgroup":
                if (needLoggedIn())
                    break;
                output = Server.createGroup(inputs[1], user);
                if ((Boolean) output)
                    sendMsg("Group Created");
                else
                    sendMsg("Name Cannot Contained Special Character");
                break;
            case "/removegroup":
                if (needLoggedIn())
                    break;
                output = Server.removeGroup(Integer.valueOf(inputs[1]), user);
                if (!(Boolean) output) { // not owner
                    sendMsg("Can't be Removed");
                    break;
                }
                sendMsg("Group Removed");
                break;
            case "/listgroup":
                if (needLoggedIn())
                    break;
                output = Server.listOfGroup();
                sendMsg("" + output);
                break;
            case "/joingroup":
                if (needLoggedIn())
                    break;
                output = Server.addUserToGroup(this, Integer.valueOf(inputs[1]));
                if (!(Boolean) output) { // not owner
                    sendMsg("There's no group with that id");
                    break;
                }
                // sendMsg("User Join");
                group.sendMsgToAll(user.username + " has join.");
                break;
            case "/chat":
                if (group == null) {
                    sendMsg("You need to be in a group to chat");
                    break;
                }
                String temp[] = input.split(" ", 1);
                group.sendMsgToAll(user.username + ": " + temp[1]);
                break;
            case "/logout":
            case "/exit":
                sendMsg("exitting...");
                Server.removeUserConnection(this);
                break;
            // case null:
            default:
                if (user == null) {
                    sendMsg("You do not have access");
                    break;
                }
                if (group == null) {
                    sendMsg("You need to be in a group to chat");
                    break;
                }
                group.sendMsgToAll(user.username + ": " + input);
                break;
        }
    }

    public void sendArray(Object[] arr) {
        // os.
    }

    public void sendMsg(String msg) {
        // pw.println(msg);
        try {
            bw.write(msg);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.out.println("ERROR: Sending to " + user.username);
        }
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
