package com.xmgps.netty;

import com.xmgps.netty.coder.MyProtocolDecoder;
import com.xmgps.netty.coder.MyProtocolEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyProtocolEncoder());
        pipeline.addLast(new MyProtocolDecoder());
        pipeline.addLast(new MyProtocolServerHandler());
    }
}
