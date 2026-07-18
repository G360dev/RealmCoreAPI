package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event triggered before a player teleports to a realm.
 * This event is cancellable and can be used to prevent realm teleports.
 *
 * @author G360 Development Team
 * @version 1.7.00
 */
public class PreRealmTeleportEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final Player player;
    private final Location destination;
    private boolean cancelled;

    /**
     * Creates a new PreRealmTeleportEvent.
     *
     * @param realm The destination realm
     * @param player The player being teleported
     * @param destination The teleport destination
     */
    public PreRealmTeleportEvent(Realm realm, Player player, Location destination) {
        this.realm = realm;
        this.player = player;
        this.destination = destination;
    }

    /**
     * Gets the destination realm.
     *
     * @return The destination realm
     */
    public Realm getRealm() { return realm; }

    /**
     * Gets the player being teleported.
     *
     * @return The player being teleported
     */
    public Player getPlayer() { return player; }

    /**
     * Gets the teleport destination.
     *
     * @return The teleport destination
     */
    public Location getDestination() { return destination; }

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
     * @param cancel true to cancel the teleport, false otherwise
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
