package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

/**
 * Event triggered when a realm is opened or closed.
 * This event can be used to track changes to visitor access.
 *
 * @author G360 Development Team
 * @version 1.7.00
 */
public class RealmOpenStateChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final boolean oldOpen;
    private final boolean newOpen;
    private final @Nullable CommandSender actor;

    /**
     * Creates a new RealmOpenStateChangeEvent.
     *
     * @param realm The realm whose open state is changing
     * @param oldOpen The previous open state
     * @param newOpen The new open state
     * @param actor The command sender who caused the change, or null if unknown
     */
    public RealmOpenStateChangeEvent(Realm realm, boolean oldOpen, boolean newOpen, @Nullable CommandSender actor) {
        this.realm = realm;
        this.oldOpen = oldOpen;
        this.newOpen = newOpen;
        this.actor = actor;
    }

    /**
     * Gets the realm whose open state is changing.
     *
     * @return The affected realm
     */
    public Realm getRealm() { return realm; }

    /**
     * Gets the previous open state.
     *
     * @return true if the realm was open, false otherwise
     */
    public boolean isOldOpen() { return oldOpen; }

    /**
     * Gets the new open state.
     *
     * @return true if the realm is now open, false otherwise
     */
    public boolean isNewOpen() { return newOpen; }

    /**
     * Gets the command sender who caused the change.
     *
     * @return The actor, or null if unknown
     */
    public @Nullable CommandSender getActor() { return actor; }

    @Override
    public HandlerList getHandlers() { return HANDLERS; }

    /**
     * Gets the handler list for this event.
     *
     * @return The handler list
     */
    public static HandlerList getHandlerList() { return HANDLERS; }
}
