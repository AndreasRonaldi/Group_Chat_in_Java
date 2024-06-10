package Server;

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
            while (shouldRun) {
                newUserSocket = serverSocket.accept();
                System.out.println("# Someone join the server.");
                Server.addUser(newUserSocket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopServer() throws IOException {
        shouldRun = false;
        serverSocket.close();
    }
}
