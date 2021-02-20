package xyz.hstudio.watchcat.wrapper;

import net.minecraft.server.v1_16_R3.AxisAlignedBB;
import net.minecraft.server.v1_16_R3.Block;
import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.IBlockData;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers;
import xyz.hstudio.watchcat.util.AABB;

import java.util.List;

public class BlockWrapper {

    protected final WorldWrapper world;
    protected final IBlockData data;
    protected final Block block;
    protected final BlockPosition bPos;
    protected final Material type;

    public BlockWrapper(WorldWrapper world, IBlockData data, BlockPosition bPos) {
        this.world = world;
        this.data = data;
        this.block = data.getBlock();
        this.bPos = bPos;
        this.type = CraftMagicNumbers.getMaterial(block);
    }

    public Material type() {
        return type;
    }

    public boolean isSolid() {
        return data.getMaterial().isSolid();
    }

    public boolean isLiquid() {
        return data.getMaterial().isLiquid();
    }

    public float friction() {
        return block.getFrictionFactor();
    }

    public AABB[] boxes() {
        List<AxisAlignedBB> bbs = data.getCollisionShape(world.worldServer, bPos).d();

        AxisAlignedBB[] raw = bbs.toArray(new AxisAlignedBB[0]);
        AABB[] boxes = new AABB[raw.length];

        for (int i = 0; i < bbs.size(); i++) {
            boxes[i] = new AABB(raw[i].minX, raw[i].minY, raw[i].minZ, raw[i].maxX, raw[i].maxY, raw[i].maxZ);
        }

        return boxes;
    }

    public int getX() {
        return bPos.getX();
    }

    public int getY() {
        return bPos.getY();
    }

    public int getZ() {
        return bPos.getZ();
    }
}