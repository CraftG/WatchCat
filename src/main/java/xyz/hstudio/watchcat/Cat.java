package xyz.hstudio.watchcat;

import io.netty.channel.ChannelPipeline;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.hstudio.watchcat.network.PacketHandler;

public class Cat {

    public final EntityPlayer nms;

    public final ChannelPipeline pipeline;
    public final PacketHandler packetHandler;

    public Cat(Player shit){
        this.nms = ((CraftPlayer) shit).getHandle();

        this.pipeline = nms.playerConnection.networkManager.channel.pipeline();
        this.packetHandler = new PacketHandler(this);
    }
}