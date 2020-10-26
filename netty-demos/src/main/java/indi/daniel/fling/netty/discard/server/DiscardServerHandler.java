package indi.daniel.fling.netty.discard.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        byte[] buf = new byte[1024];
        ByteBuf byteBuf = ((ByteBuf) msg);
        int length = byteBuf.readableBytes();
        byteBuf.readBytes(buf, 0, length);
        System.out.println("discard message: " + new String(buf, 0, length));
        ((ByteBuf) msg).release(); // (3)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
