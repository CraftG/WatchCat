package xyz.hstudio.watchcat.network;

import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_16_R3.PacketDataSerializer;
import net.minecraft.server.v1_16_R3.PacketPlayInFlying;
import xyz.hstudio.watchcat.Cat;
import xyz.hstudio.watchcat.event.Event;
import xyz.hstudio.watchcat.event.inbound.MoveEvent;
import xyz.hstudio.watchcat.util.Location;

import java.io.IOException;

public class PacketReader {

    public static Event received(Cat cat, Object packet) {
        if (packet instanceof PacketPlayInFlying.PacketPlayInLook) {
            return toEvent(cat, (PacketPlayInFlying.PacketPlayInLook) packet);
        } else if (packet instanceof PacketPlayInFlying.PacketPlayInPosition) {
            return toEvent(cat, (PacketPlayInFlying.PacketPlayInPosition) packet);
        } else if (packet instanceof PacketPlayInFlying.PacketPlayInPositionLook) {
            return toEvent(cat, (PacketPlayInFlying.PacketPlayInPositionLook) packet);
        }

        return null;
    }

    private static Event toEvent(Cat cat, PacketPlayInFlying.PacketPlayInLook packet) {
        PacketDataSerializer serializer = new PacketDataSerializer(Unpooled.buffer(9));
        try {
            packet.b(serializer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Location pos = cat.position;
        float yaw = serializer.readFloat();
        float pitch = serializer.readFloat();
        boolean onGround = serializer.readUnsignedByte() != 0;

        return new MoveEvent(cat, new Location(cat.world(), pos.x, pos.y, pos.z, yaw, pitch), onGround, true, false);
    }

    private static Event toEvent(Cat cat, PacketPlayInFlying.PacketPlayInPosition packet) {
        PacketDataSerializer serializer = new PacketDataSerializer(Unpooled.buffer(25));
        try {
            packet.b(serializer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Location pos = cat.position;
        double x = serializer.readDouble();
        double y = serializer.readDouble();
        double z = serializer.readDouble();
        boolean onGround = serializer.readUnsignedByte() != 0;

        return new MoveEvent(cat, new Location(cat.world(), x, y, z, pos.yaw, pos.pitch), onGround, false, true);
    }

    private static Event toEvent(Cat cat, PacketPlayInFlying.PacketPlayInPositionLook packet) {
        PacketDataSerializer serializer = new PacketDataSerializer(Unpooled.buffer(33));
        try {
            packet.b(serializer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        double x = serializer.readDouble();
        double y = serializer.readDouble();
        double z = serializer.readDouble();
        float yaw = serializer.readFloat();
        float pitch = serializer.readFloat();
        boolean onGround = serializer.readUnsignedByte() != 0;

        return new MoveEvent(cat, new Location(cat.world(), x, y, z, yaw, pitch), onGround, true, true);
    }
}