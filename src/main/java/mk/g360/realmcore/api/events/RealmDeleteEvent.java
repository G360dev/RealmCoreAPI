package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event triggered when a realm is deleted.
 * This event is called after a realm has been successfully removed from the system.
 *
 * @author G360 Development Team
 * @version 1.3.00
 */
public class RealmDeleteEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;

    /**
     * Creates a new RealmDeleteEvent.
     *
     * @param realm The realm that was deleted
     */
    public RealmDeleteEvent(Realm realm) {
        this.realm = realm;
    }

    /**
     * Gets the realm that was deleted.
     *
     * @return The deleted realm
     */
    public Realm getRealm() {
        return realm;
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