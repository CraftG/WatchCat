package xyz.hstudio.watchcat.module;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.hstudio.watchcat.Cat;
import xyz.hstudio.watchcat.WatchCat;
import xyz.hstudio.watchcat.event.Event;

public abstract class Meow {

    protected static final WatchCat INST = JavaPlugin.getPlugin(WatchCat.class);

    protected final Cat cat;

    protected Meow(Cat cat) {
        this.cat = cat;
    }

    public void run(Event event) {
    }
}
