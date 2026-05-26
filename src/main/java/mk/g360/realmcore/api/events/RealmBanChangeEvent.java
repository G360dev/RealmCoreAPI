package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

/**
 * Event triggered when a player is banned from or unbanned from a realm.
 * This event can be used to track changes to realm ban lists.
 *
 * @author G360 Development Team
 * @version 1.6.00
 */
public class RealmBanChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final String targetPlayerName;
    private final boolean added;
    private final @Nullable CommandSender actor;

    /**
     * Creates a new RealmBanChangeEvent.
     *
     * @param realm The realm whose ban list is changing
     * @param targetPlayerName The player being banned or unbanned
     * @param added true if the player was banned, false if unbanned
     * @param actor The command sender who caused the change, or null if unknown
     */
    public RealmBanChangeEvent(Realm realm, String targetPlayerName, boolean added, @Nullable CommandSender actor) {
        this.realm = realm;
        this.targetPlayerName = targetPlayerName;
        this.added = added;
        this.actor = actor;
    }

    /**
     * Gets the realm whose ban list is changing.
     *
     * @return The affected realm
     */
    public Realm getRealm() { return realm; }

    /**
     * Gets the player being banned or unbanned.
     *
     * @return The target player name
     */
    public String getTargetPlayerName() { return targetPlayerName; }

    /**
     * Checks whether the target player was banned.
     *
     * @return true if banned, false if unbanned
     */
    public boolean isAdded() { return added; }

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
