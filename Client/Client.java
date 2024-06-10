package Client;

import java.io.*;
import java.net.*;

public class Client {
    private static final int SERVER_PORT = 4444;
    private static final String SERVER_ADDRESS = "localhost";

    static Socket clientSocket = null;
    static BufferedReader is = null;
    static DataOutputStream os = null;

    private static boolean shouldRun = true;

    public static void main(String[] args) {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String userInput = null;
        String output = null;

        try {
            clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new DataOutputStream(clientSocket.getOutputStream());
        } catch (UnknownHostException el) {
            System.out.println("Unknown host: " + el);
        } catch (IOException e2) {
            System.out.println("Error I/O: " + e2);
        }

        while (shouldRun) {
            // take input from user to server
            try {
                System.out.print("Send to Server: ");
                userInput = stdin.readLine();
                os.writeBytes(userInput + "\n");
            } catch (IOException ex) {
                System.out.println("error writing to server." + ex);
                shouldRun = false;
            }

            // take reply from server
            try {
                output = is.readLine();
                System.out.println("Got from server: " + output);
            } catch (IOException e) {
                e.printStackTrace();
                shouldRun = false;
            }
        }
    }
}
