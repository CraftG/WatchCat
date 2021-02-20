package xyz.hstudio.watchcat.network;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import xyz.hstudio.watchcat.Cat;

public class PacketHandler extends ChannelDuplexHandler {

    private static final String HANDLER_NAME = "cat_cat_meow_meow";

    private final Cat cat;

    public PacketHandler(Cat cat){
        this.cat = cat;
        cat.pipeline.addAfter("packet_handler", HANDLER_NAME, this);
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
        super.channelRead(context, packet);
    }

    @Override
    public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception {
        super.write(context, packet, promise);
    }

    public void unregister() {
        if (cat.pipeline.get(HANDLER_NAME) != null) cat.pipeline.remove(HANDLER_NAME);
    }
}