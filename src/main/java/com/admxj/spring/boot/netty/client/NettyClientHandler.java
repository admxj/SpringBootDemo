package com.admxj.spring.boot.netty.client;

import com.admxj.spring.boot.netty.protobuf.UserInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author admxj
 * @version Id: NettyClientHandler, v 0.1 2020/6/10 12:23 上午 admxj Exp $
 */
@Service("nettyClientHandler")
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Resource
    private NettyClient nettyClient;

    /**
     * 循环次数
     */
    private int fcount = 1;

    /**
     * 建立连接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("建立连接时：" + new Date());
        ctx.fireChannelActive();
    }

    /**
     * 关闭连接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("关闭连接时：" + new Date());
        final EventLoop eventLoop = ctx.channel().eventLoop();
        nettyClient.doConnect(new Bootstrap(), eventLoop);
        super.channelInactive(ctx);
    }

    /**
     * 心跳请求处理 每4秒发送一次心跳请求;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) {
        System.out.println("循环请求的时间：" + new Date() + "，次数" + fcount);
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            // 如果写通道处于空闲状态,就发送心跳命令
            if (IdleState.WRITER_IDLE.equals(event.state())) {
                UserInfo.UserMsg.Builder userState = UserInfo.UserMsg.newBuilder().setState(2);
                ctx.channel().writeAndFlush(userState);
                fcount++;
            }
        }
    }

    /**
     * 业务逻辑处理
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 如果不是protobuf类型的数据
        if (!(msg instanceof UserInfo.UserMsg)) {
            System.out.println("未知数据!" + msg);
            return;
        }
        try {

            // 得到protobuf的数据
            UserInfo.UserMsg userMsg = (UserInfo.UserMsg) msg;
            // 进行相应的业务处理。。。
            // 这里就从简了，只是打印而已
            System.out.println(
                    "客户端接受到的用户信息。编号:" + userMsg.getId() + ",姓名:" + userMsg.getName() + ",年龄:" + userMsg.getAge());

            // 这里返回一个已经接受到数据的状态
            UserInfo.UserMsg.Builder userState = UserInfo.UserMsg.newBuilder().setState(1);
            ctx.writeAndFlush(userState);
            System.out.println("成功发送给服务端!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}