package xyz.hstudio.watchcat.wrapper;

import net.minecraft.server.v1_16_R3.Entity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import xyz.hstudio.watchcat.util.AABB;
import xyz.hstudio.watchcat.util.Location;
import xyz.hstudio.watchcat.util.Vector3D;

public class EntityWrapper {

    protected final WorldWrapper world;
    protected final Entity entity;

    public EntityWrapper(org.bukkit.entity.Entity entity) {
        this.world = new WorldWrapper(entity.getWorld());
        this.entity = ((CraftEntity) entity).getHandle();
    }

    public EntityWrapper(Entity entity) {
        this.entity = entity;
        this.world = new WorldWrapper(entity.world);
    }

    public Location position() {
        return new Location(world, entity.locX(), entity.locY(), entity.locZ(), entity.yaw, entity.pitch);
    }

    public AABB cube(Vector3D pos) {
        Vector3D move = position().subtract(pos);
        return move.toAABB();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EntityWrapper)) {
            return false;
        }
        return entity.getId() == ((EntityWrapper) obj).entity.getId();
    }

    @Override
    public int hashCode() {
        return entity.hashCode();
    }
}