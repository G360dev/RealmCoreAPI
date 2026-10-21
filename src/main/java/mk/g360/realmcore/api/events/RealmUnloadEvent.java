package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Objects;

/**
 * Fired on the server thread after an idle realm has been unloaded successfully.
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public final class RealmUnloadEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final String worldName;
    private final boolean saved;

    /**
     * Creates a successful unload event.
     *
     * @param realm realm that was unloaded
     * @param worldName name of the world that was unloaded
     * @param saved whether the world was saved before unloading
     * @throws NullPointerException if {@code realm} or {@code worldName} is null
     */
    public RealmUnloadEvent(Realm realm, String worldName, boolean saved) {
        this.realm = Objects.requireNonNull(realm, "realm");
        this.worldName = Objects.requireNonNull(worldName, "worldName");
        this.saved = saved;
    }

    /**
     * Gets the realm that was unloaded.
     *
     * @return The realm that was unloaded
     */
    public Realm getRealm() {
        return realm;
    }

    /**
     * Gets the name of the world that was unloaded.
     *
     * @return The name of the world that was unloaded
     */
    public String getWorldName() {
        return worldName;
    }

    /**
     * Checks whether the world was saved before unloading.
     *
     * @return true if the world was saved, false otherwise
     */
    public boolean wasSaved() {
        return saved;
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
