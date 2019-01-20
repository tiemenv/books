package server;

import util.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(Config.PORT);

            while (true) {
                Socket socket = serverSocket.accept();

                Connection connection = new Connection(socket);

                new Thread(connection).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
