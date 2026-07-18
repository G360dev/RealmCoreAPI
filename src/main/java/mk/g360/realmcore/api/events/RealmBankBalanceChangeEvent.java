package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

/**
 * Event triggered when a realm's bank balance changes.
 * This event can be used to track deposits, withdrawals, and balance resets.
 *
 * @author G360 Development Team
 * @version 1.7.00
 */
public class RealmBankBalanceChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final double oldBalance;
    private final double newBalance;
    private final @Nullable CommandSender actor;

    /**
     * Creates a new RealmBankBalanceChangeEvent.
     *
     * @param realm The realm whose bank balance is changing
     * @param oldBalance The previous bank balance
     * @param newBalance The new bank balance
     * @param actor The command sender who caused the change, or null if unknown
     */
    public RealmBankBalanceChangeEvent(Realm realm, double oldBalance, double newBalance, @Nullable CommandSender actor) {
        this.realm = realm;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
        this.actor = actor;
    }

    /**
     * Gets the realm whose bank balance is changing.
     *
     * @return The affected realm
     */
    public Realm getRealm() { return realm; }

    /**
     * Gets the previous bank balance.
     *
     * @return The old balance
     */
    public double getOldBalance() { return oldBalance; }

    /**
     * Gets the new bank balance.
     *
     * @return The new balance
     */
    public double getNewBalance() { return newBalance; }

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
