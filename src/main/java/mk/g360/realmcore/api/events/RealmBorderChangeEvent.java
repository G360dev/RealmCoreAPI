package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

/**
 * Event triggered when a realm's world border size changes.
 * This event can be used to track realm expansions or administrative border changes.
 *
 * @author G360 Development Team
 * @version 1.7.00
 */
public class RealmBorderChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final int oldBorder;
    private final int newBorder;
    private final @Nullable CommandSender actor;

    /**
     * Creates a new RealmBorderChangeEvent.
     *
     * @param realm The realm whose border is changing
     * @param oldBorder The previous border size
     * @param newBorder The new border size
     * @param actor The command sender who caused the change, or null if unknown
     */
    public RealmBorderChangeEvent(Realm realm, int oldBorder, int newBorder, @Nullable CommandSender actor) {
        this.realm = realm;
        this.oldBorder = oldBorder;
        this.newBorder = newBorder;
        this.actor = actor;
    }

    /**
     * Gets the realm whose border is changing.
     *
     * @return The affected realm
     */
    public Realm getRealm() { return realm; }

    /**
     * Gets the previous border size.
     *
     * @return The old border size
     */
    public int getOldBorder() { return oldBorder; }

    /**
     * Gets the new border size.
     *
     * @return The new border size
     */
    public int getNewBorder() { return newBorder; }

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
