package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNewCon implements Runnable {
    ServerSocket serverSocket = null;
    private volatile boolean shouldRun = true;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Server.PORT);
            Socket newUserSocket = null;

            System.out.println("> Server Now Ready to take new connection");

            while (shouldRun) {
                newUserSocket = serverSocket.accept();
                System.out.println("> Someone join the server.");
                Server.addUserConnection(newUserSocket);
            }
        } catch (Exception e) {
            if (shouldRun)
                System.out.println("ERROR: Something wrong went accept new connection to server");
            e.printStackTrace();
        }
    }

    public void stopServer() throws IOException {
        shouldRun = false;
        serverSocket.close();
    }
}
