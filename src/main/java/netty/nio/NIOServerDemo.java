package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServerDemo {

    private Integer port =8085;
    // 需要三大件

    // 轮寻器 Selector
     private Selector selector;
    // Buffer 等待区
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    // 初始化完成
    public NIOServerDemo(int port){
      // 初始化 选择器
        try {
            // 打开 管道
           ServerSocketChannel socketChannel= ServerSocketChannel.open();
           // 获取具体地址
            socketChannel.bind(new InetSocketAddress(port));
            // BIO升级版本　所以需要设置　AIO 为非阻塞模式
             socketChannel.configureBlocking(true);
             //准备选择器
             selector= Selector.open();
             // 进行轮训 接受数据
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);// 轮训打开
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen(){
        System.out.println("listen on"+ this.port + ".");
        // 轮训 主线程
            try {
                while (true){

                    selector.select();

                  Set<SelectionKey> keys= selector.selectedKeys();// 获取轮训集合
                    // 轮训获取集合
               Iterator<SelectionKey> iter = keys.iterator();//迭代器
                    while (iter.hasNext()){
                    SelectionKey key= iter.next();
                        iter.remove();
                        // 每一种key 代表一种状态
                        // 连接的每一种状态 ， 数据连接 、 数据可读、数据可写 、是否有效
                        process(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    // 具体业务方法。 每次轮训调用一次、 每一次只能做一件事 在同一时间点只能做一件事
    private void process(SelectionKey key) throws IOException {
//        每一种状态给一种反应
        // 是连接状态
        if (key.isAcceptable()){
            //获取通道
            ServerSocketChannel cancel = (ServerSocketChannel) key.channel();
            // 这个方法设置为非阻塞
            SocketChannel channel = cancel.accept();
            channel.configureBlocking(false);
            // 但数据准备就绪的时候，将状态改为可读
            key =channel.register(selector,SelectionKey.OP_READ);
        }//如果是读取状态
        else  if (key.isReadable()){
            // 从多路复用器中拿到客户的引用
            SocketChannel channel = (SocketChannel) key.channel();
            int len = channel.read(buffer);
            if (len > 0){
                buffer.flip();
                String content =new String( buffer.array(),0,len);
            key = channel.register(selector,SelectionKey.OP_WRITE);
            key.attach(channel);
                System.out.println("读取内容"+content);
            }
        }//如果是可写入状态
        else  if(key.isWritable()){
            SocketChannel channel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            channel.write(ByteBuffer.wrap(("输出："+content).getBytes()));
            channel.close();
        }
    }
}
