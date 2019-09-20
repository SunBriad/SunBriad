package netty.io;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServerDemo {

    public SocketServerDemo(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
    }
}
