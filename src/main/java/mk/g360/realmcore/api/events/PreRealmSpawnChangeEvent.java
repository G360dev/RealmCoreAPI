package mk.g360.realmcore.api.events;

import mk.g360.realmcore.api.Realm;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Objects;

/**
 * Fired before a realm owner changes that realm's spawn point.
 *
 * <p>Listeners may cancel the change or supply a different destination with
 * {@link #setNewSpawn(Location)}. RealmCore only accepts a replacement in the
 * same realm world, so an integration cannot redirect a realm spawn elsewhere.</p>
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public final class PreRealmSpawnChangeEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Realm realm;
    private final Player actor;
    private final Location oldSpawn;
    private Location newSpawn;
    private boolean cancelled;

    /**
     * Creates a pre-change spawn event.
     *
     * @param realm realm whose spawn is changing
     * @param actor owner requesting the change
     * @param oldSpawn current realm spawn
     * @param newSpawn requested realm spawn
     */
    public PreRealmSpawnChangeEvent(Realm realm, Player actor, Location oldSpawn, Location newSpawn) {
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

    /**
     * Replaces the requested spawn. RealmCore validates the final world before
     * applying it.
     *
     * @param newSpawn replacement location
     */
    public void setNewSpawn(Location newSpawn) {
        this.newSpawn = Objects.requireNonNull(newSpawn, "newSpawn").clone();
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
