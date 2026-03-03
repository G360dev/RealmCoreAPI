package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event triggered when a new realm is created.
 * This event is called after a realm has been successfully created in the system.
 *
 * @author G360 Development Team
 * @version 1.4.00
 */
public class RealmCreateEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;

    /**
     * Creates a new RealmCreateEvent.
     *
     * @param realm The realm that was created
     */
    public RealmCreateEvent(Realm realm) {
        this.realm = realm;
    }

    /**
     * Gets the realm that was created.
     *
     * @return The created realm
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