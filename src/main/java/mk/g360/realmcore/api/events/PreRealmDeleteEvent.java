package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

/**
 * Event triggered before a realm is deleted.
 * This event is cancellable and can be used to prevent realm deletion.
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public class PreRealmDeleteEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final @Nullable CommandSender actor;
    private boolean cancelled;

    /**
     * Creates a new PreRealmDeleteEvent.
     *
     * @param realm The realm that is about to be deleted
     * @param actor The command sender who caused the deletion, or null if unknown
     */
    public PreRealmDeleteEvent(Realm realm, @Nullable CommandSender actor) {
        this.realm = realm;
        this.actor = actor;
    }

    /**
     * Gets the realm that is about to be deleted.
     *
     * @return The realm being deleted
     */
    public Realm getRealm() { return realm; }

    /**
     * Gets the command sender who caused the deletion.
     *
     * @return The actor, or null if unknown
     */
    public @Nullable CommandSender getActor() { return actor; }

    /**
     * Checks whether this event has been cancelled.
     *
     * @return true if cancelled, false otherwise
     */
    @Override
    public boolean isCancelled() { return cancelled; }

    /**
     * Sets whether this event should be cancelled.
     *
     * @param cancel true to cancel the deletion, false otherwise
     */
    @Override
    public void setCancelled(boolean cancel) { this.cancelled = cancel; }

    @Override
    public HandlerList getHandlers() { return HANDLERS; }

    /**
     * Gets the handler list for this event.
     *
     * @return The handler list
     */
    public static HandlerList getHandlerList() { return HANDLERS; }
}
