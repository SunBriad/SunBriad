package netty.io;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketClientDemo {

    private int port;
    private String ip;
    private OutputStream os;
    private InputStream is;

    public SocketClientDemo(int port, String ip) throws IOException {
        Socket socket =new Socket(ip,port);
            // 获取输入流
       os= socket.getOutputStream();
       // 把输出流变为打印流
        PrintStream print  =new PrintStream(os);
        print.println("123456");
        print.println(os);
        print.flush();//刷新打印流
        //关闭socket 输出
        socket.shutdownOutput();
         //获取输入流 获取 服务端的数据
        is= socket.getInputStream();
        // 包装为输入字符流
        InputStreamReader isr = new InputStreamReader(is);
        // 缓冲区
        BufferedReader bis = new BufferedReader(isr);
        String info="";
        String temp=null;//临时变量
        while ((temp=bis.readLine())!=null){
            info  += temp;
            System.out.println("接受到的信息"+info);
        }
    }
}
