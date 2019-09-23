package netty.nio;

public class NIOTest {
    public static void main(String[] args) {
        NIOServerDemo serverDemo =new NIOServerDemo(8085);
          serverDemo.listen();
    }
}
