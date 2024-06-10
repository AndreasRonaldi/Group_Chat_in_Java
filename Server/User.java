package Server;

import java.io.BufferedReader;
// import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// import java.util.logging.Level;
// import java.util.logging.Logger;

public class User extends Thread {

    public String name;

    private Socket socket;
    private BufferedReader is = null;
    private PrintWriter os = null;
    private volatile boolean shouldRun = true;

    public User(final Socket userSocket) {
        try {
            this.socket = userSocket;
            is = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
            os = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
        }
    }

    @Override
    public void run() {
        while (shouldRun) {
            try {
                String line = is.readLine();
                System.out.println("input by " + this.name + " : " + line);
                // handle Input
                handleInput(line);
            } catch (IOException e) {
                System.out.println("# Disconnected: " + this.name);
                try {
                    Server.removeUser(this);
                } catch (Exception e1) {
                    System.out.println("ERROR: Something wrong when deleting user");
                    // Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e1);
                }
            }
        }
    }

    private void handleInput(String input) throws IOException {
        System.out.println(input.equalsIgnoreCase("/exit"));
        if (input.equalsIgnoreCase("/exit")) {
            Server.removeUser(this);
        } else {
            sendMsg("This is your Messages: " + input);
        }
    }

    void sendMsg(String msg) throws IOException {
        os.println(msg);
    }

    public void stopUser() throws IOException {
        shouldRun = false;
        is.close();
        os.close();
        socket.close();
    }
}
