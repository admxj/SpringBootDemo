package com.admxj.spring.boot.netty.server;

import com.admxj.spring.boot.netty.NettyServerFilter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admxj
 * @version Id: NettyServer, v 0.1 2020/6/9 11:50 下午 admxj Exp $
 */
@Service("nettyServer")
public class NettyServer {
    /**
     * 设置服务端端口
     */
    private static final int port = 9876;
    /**
     * 通过nio方式来接收连接和处理连接
     */
    private static EventLoopGroup boss = new NioEventLoopGroup();
    /**
     * 通过nio方式来接收连接和处理连接
     */
    private static EventLoopGroup work = new NioEventLoopGroup();
    private static ServerBootstrap bootstrap = new ServerBootstrap();

    @Resource
    private NettyServerFilter nettyServerFilter;


    public void run() {
        try {
            bootstrap.group(boss, work);
            bootstrap.channel(NioServerSocketChannel.class);
            // 设置过滤器
            bootstrap.childHandler(nettyServerFilter);
            // 服务器绑定端口监听
            ChannelFuture f = bootstrap.bind(port).sync();
            System.out.println("服务端启动成功,端口是:" + port);
            // 监听服务器关闭监听
//            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}