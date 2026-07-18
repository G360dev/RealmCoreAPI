package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

/**
 * Event triggered when a vote is added to or removed from a realm.
 * This event can be used to track realm vote totals and voter changes.
 *
 * @author G360 Development Team
 * @version 1.7.00
 */
public class RealmVoteChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final String voterName;
    private final int oldVotes;
    private final int newVotes;
    private final boolean added;
    private final @Nullable CommandSender actor;

    /**
     * Creates a new RealmVoteChangeEvent.
     *
     * @param realm The realm whose votes are changing
     * @param voterName The player whose vote changed
     * @param oldVotes The previous vote count
     * @param newVotes The new vote count
     * @param added true if a vote was added, false if removed
     * @param actor The command sender who caused the change, or null if unknown
     */
    public RealmVoteChangeEvent(Realm realm, String voterName, int oldVotes, int newVotes, boolean added, @Nullable CommandSender actor) {
        this.realm = realm;
        this.voterName = voterName;
        this.oldVotes = oldVotes;
        this.newVotes = newVotes;
        this.added = added;
        this.actor = actor;
    }

    /**
     * Gets the realm whose votes are changing.
     *
     * @return The affected realm
     */
    public Realm getRealm() { return realm; }

    /**
     * Gets the player whose vote changed.
     *
     * @return The voter name
     */
    public String getVoterName() { return voterName; }

    /**
     * Gets the previous vote count.
     *
     * @return The old vote count
     */
    public int getOldVotes() { return oldVotes; }

    /**
     * Gets the new vote count.
     *
     * @return The new vote count
     */
    public int getNewVotes() { return newVotes; }

    /**
     * Checks whether a vote was added.
     *
     * @return true if added, false if removed
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
