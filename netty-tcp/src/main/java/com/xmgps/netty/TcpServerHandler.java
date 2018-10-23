package com.xmgps.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class TcpServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        String recieved = new String(con, "UTF-8");
        System.out.println("服务器接收到消息：" + recieved);
        byte[] response = ("你好啊" + "\n").getBytes("GBK");
        ByteBuf message = Unpooled.buffer();
        message.writeBytes(response);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("--------服务注册 通道 -------");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("--------服务激活 通道 -------");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("--------服务失效 通道 -------");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("--------服务异常 通道 -------");
    }
}
