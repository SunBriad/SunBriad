package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyTimeServer {

    public void bind(int port) throws InterruptedException {
        //配置服务端的NIO 线程组
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap =new ServerBootstrap();
            bootstrap.group(boss,workerGroup)//设置主从线程组
                    .channel(NioServerSocketChannel.class)//设置nio双向通道
                    .option(ChannelOption.SO_BACKLOG ,1024)
                    .childHandler(new ChildChannelHandler());////字处理器,用于处理workerGroup中的任务
            // 绑定端口 ，同步等待成功
            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println("等待端口");
            //等待服务器监听端口关闭
            f.channel().closeFuture().sync();
            System.out.println("等待端口");
        }finally {
            boss.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }

    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length >0){
            try{
                port =Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
            }
        }
        new NettyTimeServer().bind(port);
    }


}
