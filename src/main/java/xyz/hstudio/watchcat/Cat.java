package xyz.hstudio.watchcat;

import io.netty.channel.ChannelPipeline;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.hstudio.watchcat.api.MeowType;
import xyz.hstudio.watchcat.module.Meow;
import xyz.hstudio.watchcat.module.meows.VMove;
import xyz.hstudio.watchcat.network.PacketHandler;
import xyz.hstudio.watchcat.util.Location;
import xyz.hstudio.watchcat.wrapper.EntityWrapper;
import xyz.hstudio.watchcat.wrapper.WorldWrapper;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static xyz.hstudio.watchcat.api.MeowType.VMove;

public class Cat {

    public final EntityPlayer nms;
    public final EntityWrapper base;

    public final ChannelPipeline pipeline;
    public final PacketHandler packetHandler;
    public final Map<MeowType, Meow> checks;

    public Location position;

    public Cat(Player shit) {
        this.nms = ((CraftPlayer) shit).getHandle();
        this.base = new EntityWrapper(shit);

        this.pipeline = nms.playerConnection.networkManager.channel.pipeline();
        this.packetHandler = new PacketHandler(this);

        this.checks = Collections.unmodifiableMap(new EnumMap<>(MeowType.class) {{
            put(VMove, new VMove(Cat.this));
        }});

        this.position = base.position();
    }

    public WorldWrapper world() {
        return new WorldWrapper(nms.getWorld());
    }
}
