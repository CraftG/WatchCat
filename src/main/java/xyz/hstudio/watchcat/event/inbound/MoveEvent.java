package xyz.hstudio.watchcat.event.inbound;

import xyz.hstudio.watchcat.Cat;
import xyz.hstudio.watchcat.event.Event;
import xyz.hstudio.watchcat.util.Location;
import xyz.hstudio.watchcat.util.Vector3D;

public class MoveEvent extends Event {

    public final Location to;
    public final Location from;
    public final boolean onGround;
    public final boolean hasLook;
    public final boolean hasPos;

    public Vector3D velocity;

    public boolean onGroundReally;

    public MoveEvent(Cat cat, Location to, boolean onGround, boolean hasLook, boolean hasPos) {
        super(cat);
        this.from = cat.position;
        this.to = to;
        this.onGround = onGround;
        this.hasLook = hasLook;
        this.hasPos = hasPos;
    }

    @Override
    public boolean pre() {
        this.velocity = to.minus(from);

        this.onGroundReally = to.onGround(0.02);

        return true;
    }

    @Override
    public void post() {
        cat.position = to;
    }
}