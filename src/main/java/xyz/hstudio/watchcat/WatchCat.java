package xyz.hstudio.watchcat;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hstudio.watchcat.util.BlockUtil;

import java.net.URLClassLoader;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class WatchCat extends JavaPlugin {

    @Getter
    private final Map<UUID, Cat> cats = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {
        try {
            Class.forName("net.minecraft.server.v1_16_R3.MinecraftServer");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unsupported version!");
        }

        BlockUtil.isSolid(Material.AIR);

        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onJoin(PlayerJoinEvent e) {
                Player shit = e.getPlayer();
                cats.put(shit.getUniqueId(), new Cat(shit));
            }

            @EventHandler
            public void onQuit(PlayerQuitEvent e) {
                Player shit = e.getPlayer();
                cats.remove(shit.getUniqueId());
            }
        }, this);
    }

    @Override
    public void onDisable() {
        cats.values().forEach(cat -> cat.packetHandler.unregister());

        try {
            ((URLClassLoader) getClassLoader()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
