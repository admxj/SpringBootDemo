package com.admxj.spring.boot;

import com.admxj.spring.boot.netty.client.NettyClient;
import com.admxj.spring.boot.netty.server.NettyServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author admxj
 */
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);

        NettyServer nettyServer = context.getBean(NettyServer.class);
        nettyServer.run();

        NettyClient nettyClient = context.getBean(NettyClient.class);
        nettyClient.doConnect(new Bootstrap(), new NioEventLoopGroup());
    }

}
