package xyz.hstudio.watchcat.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.hstudio.watchcat.Cat;
import xyz.hstudio.watchcat.WatchCat;

@RequiredArgsConstructor
public abstract class Event {

    protected static final WatchCat INST = JavaPlugin.getPlugin(WatchCat.class);

    protected final Cat cat;
    @Getter
    @Setter
    private boolean cancelled;

    public boolean pre() {
        return true;
    }

    public void post() {
    }
}
