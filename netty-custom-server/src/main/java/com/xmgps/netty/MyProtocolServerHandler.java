package com.xmgps.netty;

import com.xmgps.netty.bean.MyProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyProtocolServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 用于获取客户端发来的数据信息
        MyProtocol body = (MyProtocol) msg;
        System.out.println("Server接受的客户端的信息 :" + new String(body.getContent()));

        // 会写数据给客户端
        String str = "Hi I am Server ...";
        MyProtocol response = new MyProtocol(str.getBytes().length, str.getBytes());
        // 当服务端完成写操作后，关闭与客户端的连接
        ctx.writeAndFlush(response);
        // .addListener(ChannelFutureListener.CLOSE);

        // 当有写操作时，不需要手动释放msg的引用
        // 当只有读操作时，才需要手动释放msg的引用
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // cause.printStackTrace();
        ctx.close();
        System.out.println(ctx.channel().remoteAddress() + "客户端关闭");
    }


}
