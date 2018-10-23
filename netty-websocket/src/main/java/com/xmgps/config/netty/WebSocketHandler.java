package com.xmgps.config.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;

public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static Logger logger = LoggerFactory.getLogger("netty");

    private static Timer timer = null;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg){
        try{
            Channel channel = ctx.channel();
            String params = msg.text();
            JSONObject json = JSON.parseObject(params);
            int code = json.getIntValue("code");
            switch (code){
                case 1:
                    logger.info("收到ip：" + channel.remoteAddress() + "____信息：" + params);
                    if(null == timer){
                        startTimer();
                    }
            }

        }catch (Exception e){
            logger.error("连接错误：channelRead0()-------" + e);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        try{
            Channel channel = ctx.channel();
            Constants.channels.add(channel);
            logger.info("用户加入连接++++++++++++++++当前连接数：" + Constants.channels.size() + "人");
        }catch (Exception e){
            logger.error("连接错误：handlerAdded()-------" + e);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        try{
            Channel channel = ctx.channel();
            Constants.channels.remove(channel);
            logger.info("用户断开连接----------------当前连接数：" + Constants.channels.size() + "人");
        }catch (Exception e){
            logger.error("连接错误：handlerRemoved()-------" + e);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        try{
            Channel channel = ctx.channel();
            logger.info("在线：" + channel.remoteAddress());
        }catch (Exception e){
            logger.error("连接错误：channelActive()-------" + e);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        try{
            Channel channel = ctx.channel();
            logger.info("掉线：" + channel.remoteAddress());
        }catch (Exception e){
            logger.error("连接错误：channelInactive()-------" + e);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        try{
            Channel channel = ctx.channel();
            logger.error("异常：" + channel.remoteAddress());
            // 当出现异常就关闭连接
            logger.error(cause.toString());
            ctx.close();
        }catch (Exception e){
            logger.error("连接错误：exceptionCaught()-------" + e);
        }
    }


    public void startTimer(){
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new WebSocketTimerTask(),0,500);
        logger.info("推送定时器启动");
    }
}
