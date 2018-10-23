package com.xmgps.listener;

import com.xmgps.config.netty.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class initNettyService {
    private final Logger logger = LoggerFactory.getLogger("netty");


    @PostConstruct
    public void init(){
        Thread nettyThread = new Thread(){
            public void run(){
                try{
                    WebSocket.getWebSocket().openWebsocket();
                }catch (Exception e){
                    logger.error("netty_WebSocket启动出错");
                }
            }
        };
        nettyThread.setDaemon(true);
        nettyThread.start();
    }
}
