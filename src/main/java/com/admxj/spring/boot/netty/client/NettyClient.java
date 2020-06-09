package com.admxj.spring.boot.netty.client;

import com.admxj.spring.boot.netty.NettyServerFilter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.ScheduledFuture;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author admxj
 * @version Id: NettyClient, v 0.1 2020/6/10 12:24 上午 admxj Exp $
 */
@Service("nettyClient")
public class NettyClient {

    @Resource
    private NettyServerFilter nettyClientFilter;

    public void doConnect(Bootstrap bootstrap, EventLoopGroup eventLoopGroup) {
        ChannelFuture f = null;
        try {
            if (bootstrap != null) {
                bootstrap.group(eventLoopGroup);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
                bootstrap.handler(nettyClientFilter);
                bootstrap.remoteAddress("127.0.0.1", 9876);
                f = bootstrap.connect().addListener((ChannelFuture futureListener) -> {
                    final EventLoop eventLoop = futureListener.channel().eventLoop();
                    if (!futureListener.isSuccess()) {
                        System.out.println("与服务端断开连接!在10s之后准备尝试重连!");
                        ScheduledFuture<?> schedule = eventLoop.schedule(() -> doConnect(new Bootstrap(), eventLoop), 10, TimeUnit.SECONDS);
                    }
                });
                boolean initFalg = true;
                if (initFalg) {
                    System.out.println("Netty客户端启动成功!");
                    initFalg = false;
                }
                // 阻塞
                f.channel().closeFuture().sync();
            }
        } catch (Exception e) {
            System.out.println("客户端连接失败!" + e.getMessage());
        }
    }

}
