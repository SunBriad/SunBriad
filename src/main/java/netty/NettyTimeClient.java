package netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;

public class NettyTimeClient {
    public void connect(int port ,String host)throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap =new Bootstrap();
            bootstrap.group(group).channel(NioSctpServerChannel.class).option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new TimeClientHandler());
                }
            });
            //发起异步连接操作
            ChannelFuture f = bootstrap.connect(host,port);
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        }finally {
            //优雅退出
            group.shutdownGracefully();
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
        new NettyTimeClient().connect(port,"localhost");
    }
}
