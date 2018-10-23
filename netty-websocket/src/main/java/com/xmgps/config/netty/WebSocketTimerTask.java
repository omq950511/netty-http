package com.xmgps.config.netty;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.TimerTask;

public class WebSocketTimerTask extends TimerTask{
    private final Logger logger = LoggerFactory.getLogger("netty");

    @Override
    public void run() {
        List<Channel> channels = Constants.channels;
        if(channels != null && channels.size() > 0){
            for(Channel channel : Constants.channels){
                String currentTime = String.valueOf(System.currentTimeMillis());
                logger.info("发送数据：" + currentTime);
                channel.writeAndFlush(new TextWebSocketFrame(currentTime));
            }
        }
    }
}
