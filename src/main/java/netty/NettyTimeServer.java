package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;

public class NettyTimeServer {

    public void bind(int port) throws InterruptedException {
        //配置服务端的NIO 线程组
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap =new ServerBootstrap();
            bootstrap.group(boss,workerGroup)
                    .channel(NioSctpServerChannel.class)
                    .option(ChannelOption.SO_BACKLOG ,1024)
                    .childHandler(new ChildChannelHandler());
            // 绑定端口 ，同步等待成功
            ChannelFuture f = bootstrap.bind(port).sync();
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
}
