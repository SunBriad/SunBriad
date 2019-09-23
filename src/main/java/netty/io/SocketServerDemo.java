package netty.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {

    private int  port ;

    private InputStream is;
    public SocketServerDemo(int port) throws IOException {
        // 创建服务器socket
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("打开服务器！！");
         // 打开socket
        Socket socket= serverSocket.accept();
        System.out.println("接受数据");
            // 获取输入流
        is = socket.getInputStream();
        // 将输入流输出
        InputStreamReader inputStreamReader =new InputStreamReader(is);
        BufferedReader br = new BufferedReader(inputStreamReader);

        while (br.readLine() != null){
            System.out.println(br.readLine());
        }

//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
//        System.out.println(is.read());
    }


}
