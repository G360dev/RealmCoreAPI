package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

/**
 * Event triggered when a realm changes owner.
 * This event can be used to track ownership transfers between players.
 *
 * @author G360 Development Team
 * @version 1.6.00
 */
public class RealmOwnerChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm oldRealm;
    private final Realm newRealm;
    private final String oldOwner;
    private final String newOwner;
    private final @Nullable CommandSender actor;

    /**
     * Creates a new RealmOwnerChangeEvent.
     *
     * @param oldRealm The realm before ownership transfer
     * @param newRealm The realm after ownership transfer
     * @param oldOwner The previous owner name
     * @param newOwner The new owner name
     * @param actor The command sender who caused the change, or null if unknown
     */
    public RealmOwnerChangeEvent(Realm oldRealm, Realm newRealm, String oldOwner, String newOwner, @Nullable CommandSender actor) {
        this.oldRealm = oldRealm;
        this.newRealm = newRealm;
        this.oldOwner = oldOwner;
        this.newOwner = newOwner;
        this.actor = actor;
    }

    /**
     * Gets the realm before ownership transfer.
     *
     * @return The old realm
     */
    public Realm getOldRealm() { return oldRealm; }

    /**
     * Gets the realm after ownership transfer.
     *
     * @return The new realm
     */
    public Realm getNewRealm() { return newRealm; }

    /**
     * Gets the previous owner name.
     *
     * @return The old owner name
     */
    public String getOldOwner() { return oldOwner; }

    /**
     * Gets the new owner name.
     *
     * @return The new owner name
     */
    public String getNewOwner() { return newOwner; }

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
