package com.xmgps.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
//        pipeline.addLast("frameDecode", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//        pipeline.addLast("frameEncode", new LengthFieldPrepender(4));
//        pipeline.addLast("decode", new StringDecoder(CharsetUtil.UTF_8));
//        pipeline.addLast("encode", new StringEncoder(CharsetUtil.UTF_8));
//        pipeline.addLast("encode",new StringEncoder());
//        pipeline.addLast("decode",new StringDecoder());
//        pipeline.addLast("ping", new IdleStateHandler(25, 15, 10, TimeUnit.SECONDS));
        pipeline.addLast(new TcpServerHandler());
    }
}
