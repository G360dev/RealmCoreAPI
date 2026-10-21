package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Objects;

/**
 * Fired on the server thread before RealmCore unloads an idle realm.
 *
 * <p>Listeners may cancel this event to keep the world loaded. The event is
 * only dispatched for an unload attempt; it does not imply that the unload
 * will succeed.</p>
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public final class PreRealmUnloadEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final World world;
    private final boolean saving;
    private boolean cancelled;

    /**
     * Creates a pre-unload event.
     *
     * @param realm realm whose world is being unloaded
     * @param world world being unloaded
     * @param saving whether RealmCore will save the world before unloading
     * @throws NullPointerException if {@code realm} or {@code world} is null
     */
    public PreRealmUnloadEvent(Realm realm, World world, boolean saving) {
        this.realm = Objects.requireNonNull(realm, "realm");
        this.world = Objects.requireNonNull(world, "world");
        this.saving = saving;
    }

    /**
     * Gets the realm whose world is being unloaded.
     *
     * @return The realm whose world is being unloaded
     */
    public Realm getRealm() {
        return realm;
    }

    /**
     * Gets the world being unloaded.
     *
     * @return The world being unloaded
     */
    public World getWorld() {
        return world;
    }

    /**
     * Checks whether the unload operation includes a world save.
     *
     * @return true if the world will be saved, false otherwise
     */
    public boolean isSaving() {
        return saving;
    }

    /**
     * Checks whether a listener has cancelled this unload attempt.
     *
     * @return true if the unload is cancelled, false otherwise
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancels or allows the pending unload.
     *
     * @param cancelled {@code true} to keep the world loaded
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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
