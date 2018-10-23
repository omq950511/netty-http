package com.xmgps.config.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocket {
    private final Logger logger = LoggerFactory.getLogger("netty");

    private static WebSocket webSocket = null;
    public static WebSocket getWebSocket(){
        if(webSocket == null){
            webSocket = new WebSocket();
        }
        return webSocket;
    }

    public void openWebsocket(){
        // Boss线程：由这个线程池提供的线程是boss种类的，用于创建、连接、绑定socket， （有点像门卫）然后把这些socket传给worker线程池。
        // 在服务器端每个监听的socket都有一个boss线程来处理。在客户端，只有一个boss线程来处理所有的socket。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // Worker线程：Worker线程执行所有的异步I/O，即处理操作
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            // ServerBootstrap 启动NIO服务的辅助启动类,负责初始话netty服务器，并且开始监听端口的socket请求
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup);
            // 设置非阻塞,用它来建立新accept的连接,用于构造serversocketchannel的工厂类
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new WebSocketInitializer());
            ChannelFuture f = b.bind(Constants.PORT).sync();
            logger.info("netty_WebSocket启动");
            f.channel().closeFuture().sync();
        }catch (Exception e){
            logger.info("错误：" + e);
        }finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            logger.info("netty_WebSocket关闭");
        }
    }
}
