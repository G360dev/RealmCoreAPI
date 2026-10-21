package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Objects;

/**
 * Fired on the server thread after a realm world becomes available.
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public final class RealmLoadEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Describes why the realm world became available.
     */
    public enum Cause {
        CREATED,
        LOADED
    }

    private final Realm realm;
    private final World world;
    private final Cause cause;

    /**
     * Creates a realm-load event.
     *
     * @param realm realm that became available
     * @param world loaded or newly created world
     * @param cause reason the world became available
     * @throws NullPointerException if any argument is null
     */
    public RealmLoadEvent(Realm realm, World world, Cause cause) {
        this.realm = Objects.requireNonNull(realm, "realm");
        this.world = Objects.requireNonNull(world, "world");
        this.cause = Objects.requireNonNull(cause, "cause");
    }

    /**
     * Gets the realm that became available.
     *
     * @return The realm that became available
     */
    public Realm getRealm() {
        return realm;
    }

    /**
     * Gets the loaded or newly created world.
     *
     * @return The loaded or newly created world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Gets the cause that made the realm world available.
     *
     * @return The load cause
     */
    public Cause getCause() {
        return cause;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Gets the handler list for this event.
     *
     * @return The handler list
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
