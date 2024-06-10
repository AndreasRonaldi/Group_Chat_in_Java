package Client;

import Client.GUI.*;
import java.io.*;
import java.net.*;

public class Client {
    private static final int SERVER_PORT = 4444;
    private static final String SERVER_ADDRESS = "localhost";

    static Socket clientSocket = null;
    static BufferedReader is = null;
    static DataOutputStream os = null;

    static BufferedReader stdin = null;
    static String userInput = null;
    static String output = null;
    
    private static boolean shouldRun = true;

    public static void main(String[] args) {
        stdin = new BufferedReader(new InputStreamReader(System.in));

        // Connect to server
        try {
            clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new DataOutputStream(clientSocket.getOutputStream());
        } catch (UnknownHostException el) {
            System.out.println("Unknown host: " + el);
        } catch (IOException e2) {
            System.out.println("Error I/O: " + e2);
        }

        // while (shouldRun) {
        //     // take input from user to server
        //     try {
        //         System.out.print("Send to Server: ");
        //         userInput = stdin.readLine();
        //         os.writeBytes(userInput + "\n");
        //     } catch (IOException ex) {
        //         System.out.println("error writing to server." + ex);
        //         shouldRun = false;
        //     }

        //     // take reply from server
        //     try {
        //         output = is.readLine();
        //         System.out.println("Got from server: " + output);
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //         shouldRun = false;
        //     }
        // }

        Login form = new Login();  
        form.setSize(300,100);  //set size of the frame  
        form.setVisible(true);  //make form visible to the user  
    }

    public static void Input(String input) throws IOException {
            os.writeBytes(input + "\n");
    }
}
