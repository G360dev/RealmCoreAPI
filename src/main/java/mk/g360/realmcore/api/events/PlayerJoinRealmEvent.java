package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event triggered when a player enters a realm's area.
 * This event can be used to track player movement between realms.
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public class PlayerJoinRealmEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Realm realm;
    private final Player player;

    /**
     * Creates a new PlayerJoinRealmEvent.
     *
     * @param realm The realm the player is entering
     * @param player The player who is entering the realm
     */
    public PlayerJoinRealmEvent(Realm realm, Player player) {
        this.realm = realm;
        this.player = player;
    }

    /**
     * Gets the realm that the player is entering.
     *
     * @return The realm being entered
     */
    public Realm getRealm() {
        return realm;
    }

    /**
     * Gets the player who is entering the realm.
     *
     * @return The player entering the realm
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
