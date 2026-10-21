package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event triggered when a player leaves a realm's area.
 * This event can be used to track player movement between realms.
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public class PlayerLeaveRealmEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final Player player;

    /**
     * Creates a new PlayerLeaveRealmEvent.
     *
     * @param realm The realm the player is leaving
     * @param player The player who is leaving the realm
     */
    public PlayerLeaveRealmEvent(Realm realm, Player player) {
        this.realm = realm;
        this.player = player;
    }

    /**
     * Gets the realm that the player is leaving.
     *
     * @return The realm being left
     */
    public Realm getRealm() {
        return realm;
    }

    /**
     * Gets the player who is leaving the realm.
     *
     * @return The player leaving the realm
     */
    public Player getPlayer() {
        return player;
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
