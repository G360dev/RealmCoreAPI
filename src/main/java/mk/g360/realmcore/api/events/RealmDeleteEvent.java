package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Fired on the server thread after a realm has been deleted successfully.
 *
 * @author G360 Development Team
 * @version 1.7.00
 */
public class RealmDeleteEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;

    /**
     * Creates a realm-delete event.
     *
     * @param realm realm that was deleted
     */
    public RealmDeleteEvent(Realm realm) {
        this.realm = realm;
    }

    /** @return the realm that was deleted */
    public Realm getRealm() {
        return realm;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /** @return the handler list used by Bukkit to register listeners */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
