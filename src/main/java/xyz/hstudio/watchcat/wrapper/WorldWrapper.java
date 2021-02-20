package xyz.hstudio.watchcat.wrapper;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import xyz.hstudio.watchcat.util.Vector3D;

public class WorldWrapper {

    protected final WorldServer worldServer;

    public WorldWrapper(org.bukkit.World bukkitWorld) {
        this.worldServer = ((CraftWorld) bukkitWorld).getHandle();
    }

    public WorldWrapper(World world) {
        this.worldServer = world.getWorld().getHandle();
    }

    public org.bukkit.World bukkit() {
        return worldServer.getWorld();
    }

    public BlockWrapper getBlock(int x, int y, int z) {
        Chunk chunk = worldServer.getChunkIfLoaded(x >> 4, z >> 4);
        if (chunk == null) {
            return null;
        }
        BlockPosition bPos = new BlockPosition(x, y, z);
        return new BlockWrapper(this, chunk.getType(bPos), bPos);
    }

    public BlockWrapper getBlock(Vector3D vec) {
        return getBlock(vec.getBlockX(), vec.getBlockY(), vec.getBlockZ());
    }

    public Entity getEntity(int id) {
        return worldServer.getEntity(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorldWrapper)) {
            return false;
        }
        return bukkit().getUID().equals(((WorldWrapper) obj).bukkit().getUID());
    }
}