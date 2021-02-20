package xyz.hstudio.watchcat.wrapper;

import net.minecraft.server.v1_16_R3.ItemStack;
import net.minecraft.server.v1_16_R3.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_16_R3.util.CraftNamespacedKey;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class ItemWrapper {

    protected final ItemStack itemStack;
    protected final Map<Enchantment, Integer> enchantments;

    public ItemWrapper(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.enchantments = new HashMap<>();

        if (itemStack == null) {
            return;
        }
        NBTTagList tag = itemStack.getEnchantments();
        if (tag == null) {
            return;
        }

        for (int i = 0; i < tag.size(); ++i) {
            String id = tag.getCompound(i).getString("id");
            int level = tag.getCompound(i).getShort("lvl");
            Enchantment enchantment = Enchantment.getByKey(CraftNamespacedKey.fromStringOrNull(id));
            enchantments.put(enchantment, level);
        }
    }

    public boolean hasEnchantment(Enchantment enchantment) {
        return enchantments.containsKey(enchantment);
    }

    public int getEnchantmentLevel(Enchantment enchantment) {
        return enchantments.get(enchantment);
    }

    public Material type() {
        if (itemStack == null) {
            return Material.AIR;
        }

        return CraftMagicNumbers.getMaterial(itemStack.getItem());
    }
}