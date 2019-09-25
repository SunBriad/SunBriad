package netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 将 msg 转换为Netty 的ByteBuf 对象。
        ByteBuf buf = (ByteBuf) msg;
        // 通过ButeBuf 的 readableBytes 方法将缓冲区中的字节数组复制到新建的byte 数组中
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        // 获取请求消息
        String body = new String(req,"UTF-8");

        System.out.println("The time server receive order : "+ body);
        // 如果是QUERY TIME ORDER 则创建应答消息
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : " BAO ORDER";

        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        // 异步发送给客户端
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将消息发送列队中的消息写入到SocketChannel 中发送给对方
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 发生异常的时候 关闭 和释放ChannelHandlerContext 相关连的句柄资源
        ctx.close();
    }


}
