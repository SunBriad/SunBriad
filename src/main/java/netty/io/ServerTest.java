package netty.io;

import org.junit.Test;

import java.io.IOException;

public class ServerTest {

    public static void main(String[] args) throws IOException {
        SocketServerDemo s = new SocketServerDemo(1111);


    }

    @Test
    public void Client() throws IOException {
        SocketClientDemo socketClientDemo = new SocketClientDemo(8085,"localhost");
    }


}
