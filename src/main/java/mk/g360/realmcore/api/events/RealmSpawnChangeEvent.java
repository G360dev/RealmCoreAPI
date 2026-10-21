package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Objects;

/**
 * Fired after a realm owner successfully changes that realm's spawn point.
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public final class RealmSpawnChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Realm realm;
    private final Player actor;
    private final Location oldSpawn;
    private final Location newSpawn;

    /**
     * Creates a post-change spawn event.
     *
     * @param realm realm whose spawn changed
     * @param actor owner that changed the spawn
     * @param oldSpawn previous realm spawn
     * @param newSpawn applied realm spawn
     */
    public RealmSpawnChangeEvent(Realm realm, Player actor, Location oldSpawn, Location newSpawn) {
        this.realm = Objects.requireNonNull(realm, "realm");
        this.actor = Objects.requireNonNull(actor, "actor");
        this.oldSpawn = Objects.requireNonNull(oldSpawn, "oldSpawn").clone();
        this.newSpawn = Objects.requireNonNull(newSpawn, "newSpawn").clone();
    }

    public Realm getRealm() {
        return realm;
    }

    public Player getActor() {
        return actor;
    }

    public Location getOldSpawn() {
        return oldSpawn.clone();
    }

    public Location getNewSpawn() {
        return newSpawn.clone();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
