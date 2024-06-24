package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerUser extends Thread {

    public String user = null;
    public Integer group;

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
        if (group != null)
            Server.removeUserFromGroup(this);
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
                        System.out.println("> Input [" + this.user + "]: " + line);
                    else
                        System.out.println("> Input [new]: " + line);
                    // handle Input
                    handleInput(line);
                }
            } catch (IOException e) {
                if (user != null)
                    System.out.println("> Disconnected: " + this.user);
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
        // Object[] arrOutput = null;

        ServerUser suTemp = null;

        // System.out.println(Arrays.toString(inputs));

        switch (action) {
            case "/signup":
                output = Server.addNewUser(inputs[1], inputs[2]);
                if (!(Boolean) output) { // username taken
                    sendMsg("/signup " + output);
                    break;
                }
            case "/login":
                output = Server.login(inputs[1], inputs[2], this);
                sendMsg("/login " + output);
                break;
            // Reading all this without stop from client
            case "/addgroup":
                if (needLoggedIn())
                    break;
                output = Server.createGroup(inputs[1], user);
                sendMsg("/addgroup " + output);
                break;
            case "/removegroup":
                if (needLoggedIn())
                    break;
                output = Server.removeGroup(Integer.valueOf(inputs[1]), user);
                sendMsg("/removegroup " + output);
                break;
            case "/listgroup":
                if (needLoggedIn())
                    break;
                output = Server.listOfGroup();
                sendMsg("/listgroup " + output);
                break;
            case "/joingroup":
                if (needLoggedIn())
                    break;
                output = Server.addUserToGroup(this, Integer.valueOf(inputs[1]));
                if (!(Boolean) output) { // group not found
                    sendMsg("/joingroup " + output);
                    break;
                }
                sendMsg("/joingroup " + output);
                Server.sendMsgToGroup(group, "/chat " + user + " has join room.");
                break;
            case "/exitgroup":
                if (needLoggedIn())
                    break;
                Server.sendMsgToGroup(group, "/chat " + user + " exit room.");
                // TODO:
                Server.removeUserFromGroup(this);
                sendMsg("/exitgroup exit");
                break;
            case "/kick":
                // TODO:
                if (needLoggedIn())
                    break;
                if (!user.equals(ServerSQL.findGroupOwner(group))) {
                    sendMsg("you can't be doing this");
                    return;
                }
                suTemp = Server.findActiveUser(inputs[1]);
                output = Server.removeUserFromGroup(suTemp);
                if (!(Boolean) output) { // user not found
                    sendMsg("/kick false");
                    break;
                }
                suTemp.sendMsg("/exitgroup kick");
                sendMsg("/kick true");
                Server.sendMsgToGroup(group, "/chat " + inputs[1] + " has been kicked from this room.");
            case "/listuseringroup":
                if (needLoggedIn())
                    break;
                if (group == null)
                    break;
                output = Server.listOfUserInGroup(group);
                sendMsg("/listuseringroup " + output);
                break;
            case "/chat":
                if (group == null) {
                    sendMsg("false");
                    break;
                }
                String temp[] = input.split(" ", 2);
                Server.sendMsgToGroup(group, "/chat " + user + ": " + temp[1]);
                break;
            case "/logout":
            case "/exit":
                sendMsg("/exit");
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
                sendMsg("What....?");
                // group.sendMsgToAll(user.username + ": " + input);
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
            System.out.println("ERROR: Sending to " + user);
        }
    }

    public void setGroup(Integer groupId) {
        this.group = groupId;
    }

    public void setUser(String username) {
        this.user = username;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
