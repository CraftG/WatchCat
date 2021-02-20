package xyz.hstudio.watchcat.network;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import xyz.hstudio.watchcat.Cat;
import xyz.hstudio.watchcat.event.Event;
import xyz.hstudio.watchcat.module.Meow;

public class PacketHandler extends ChannelDuplexHandler {

    private static final String HANDLER_NAME = "cat_cat_meow_meow";

    private final Cat cat;

    public PacketHandler(Cat cat) {
        this.cat = cat;
        cat.pipeline.addAfter("packet_handler", HANDLER_NAME, this);
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object packet) throws Exception {
        Event event = PacketReader.received(cat, packet);
        if (run(event)) {
            super.channelRead(context, packet);
        }
    }

    @Override
    public void write(ChannelHandlerContext context, Object packet, ChannelPromise promise) throws Exception {
        super.write(context, packet, promise);
    }

    private boolean run(Event event) {
        try {
            if (event == null) {
                return true;
            }
            if (!event.pre()) {
                return false;
            } else {
                for (Meow check : cat.checks.values()) {
                    check.run(event);
                }
                event.post();
                return !event.isCancelled();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return true;
        }
    }

    public void unregister() {
        if (cat.pipeline.get(HANDLER_NAME) != null) cat.pipeline.remove(HANDLER_NAME);
    }
}
