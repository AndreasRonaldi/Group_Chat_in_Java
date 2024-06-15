package Server;

import java.io.BufferedReader;
// import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ServerUser extends Thread {

    public User user;
    public Group group;

    private Socket socket;
    private BufferedReader is = null;
    private PrintWriter os = null;
    private volatile boolean shouldRun = true;

    public ServerUser(final Socket userSocket) {
        try {
            this.socket = userSocket;
            is = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            os = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
        }
    }

    public void stopUser() throws IOException {
        shouldRun = false;
        Server.logout(this);
        is.close();
        os.close();
        socket.close();
    }

    @Override
    public void run() {
        while (shouldRun) {
            try {
                String line = is.readLine();
                if (user != null)
                    System.out.println("> Input [" + this.user.username + "]: " + line);
                else
                    System.out.println("> Input [new]: " + line);
                // handle Input
                handleInput(line);
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

        boolean bolOutput = false;

        // System.out.println(Arrays.toString(inputs));

        switch (action) {
            case "/signup":
                bolOutput = Server.addNewUser(inputs[1], inputs[2]);
                if (!bolOutput) { // username taken
                    sendMsg("" + bolOutput);
                    break;
                }
            case "/login":
                bolOutput = Server.login(inputs[1], inputs[2], this);
                sendMsg("" + bolOutput);
                break;
            case "/logout":
                Server.logout(this);
                sendMsg("Done");
                this.stopUser();
                break;
            case "/exit":
                Server.removeUserConnection(this);
                break;
            default:
                sendMsg("This is your Messages: " + input);
                break;
        }
    }

    public void sendMsg(String msg) {
        os.println(msg);
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
