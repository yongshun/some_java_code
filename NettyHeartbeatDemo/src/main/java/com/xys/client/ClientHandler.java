package com.xys.client;

import com.xys.common.CustomHeartbeatHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author xiongyongshun
 * @version 1.0
 * @email yongshun1228@gmail.com
 * @created 16/9/18 13:08
 */
public class ClientHandler extends CustomHeartbeatHandler {
    private Client client;
    public ClientHandler(Client client) {
        super("client");
        this.client = client;
    }

    @Override
    protected void handleData(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        byte[] data = new byte[byteBuf.readableBytes() - 5];
        byteBuf.skipBytes(5);
        byteBuf.readBytes(data);
        String content = new String(data);
        System.out.println(name + " get content: " + content);
    }

    @Override
    protected void handleAllIdle(ChannelHandlerContext ctx) {
        super.handleAllIdle(ctx);
        sendPingMsg(ctx);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        client.doConnect();
    }
}