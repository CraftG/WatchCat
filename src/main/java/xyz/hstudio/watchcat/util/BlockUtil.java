package xyz.hstudio.watchcat.util;

import org.bukkit.Material;
import xyz.hstudio.watchcat.wrapper.BlockWrapper;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class BlockUtil {

    private static final Set<Material> SOLID = EnumSet.noneOf(Material.class);

    static {
        SOLID.add(Material.SNOW);
        SOLID.add(Material.SNOW_BLOCK);
        SOLID.add(Material.LADDER);
        SOLID.add(Material.VINE);
        SOLID.add(Material.LILY_PAD);
        SOLID.add(Material.COCOA);
        SOLID.add(Material.FLOWER_POT);
        SOLID.add(Material.FARMLAND);

        for (Material material : Material.values()) {
            if (material.name().contains("COMPARATOR") || material.name().contains("DIODE")) {
                SOLID.add(material);
            }
            if (material.name().contains("CARPET")) {
                SOLID.add(material);
            }
            if (material.name().endsWith("WALL_SKULL")) {
                SOLID.add(material);
            }
        }
    }

    public static boolean isSolid(BlockWrapper block) {
        return block.isSolid() || SOLID.contains(block.type());
    }

    public static boolean isSolid(Material type) {
        return type.isSolid() || SOLID.contains(type);
    }

    public static Set<BlockWrapper> getBlocksInLocation(Location loc) {
        Set<BlockWrapper> blocks = new HashSet<>();
        blocks.add(loc.plus(0.3, 0, 0).getBlock());
        blocks.add(loc.plus(0, 0, 0.3).getBlock());
        blocks.add(loc.plus(-0.3, 0, 0).getBlock());
        blocks.add(loc.plus(0, 0, -0.3).getBlock());
        blocks.add(loc.plus(0.3, 0, 0.3).getBlock());
        blocks.add(loc.plus(-0.3, 0, -0.3).getBlock());
        blocks.add(loc.plus(0.3, 0, -0.3).getBlock());
        blocks.add(loc.plus(-0.3, 0, 0.3).getBlock());
        blocks.remove(null);
        return blocks;
    }
}