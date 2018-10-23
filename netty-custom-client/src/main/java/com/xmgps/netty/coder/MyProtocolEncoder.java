package com.xmgps.netty.coder;

import com.xmgps.netty.bean.MyProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyProtocolEncoder extends MessageToByteEncoder<MyProtocol> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MyProtocol msg, ByteBuf out) throws Exception {
        // 写入消息MyProtocol的具体内容
        // 1.写入消息的开头的信息标志(int类型)
        out.writeInt(msg.getHead_data());
        // 2.写入消息的长度(int 类型)
        out.writeInt(msg.getContentLength());
        // 3.写入消息的内容(byte[]类型)
        out.writeBytes(msg.getContent());
    }
}
