package mk.g360.realmcore.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/** Represents a player's Realm */
public interface Realm {
    String getName();
    Player getOwner();
    UUID getOwnerUUID();
    List<String> getTrusted();
    List<UUID> getTrustedUUIDs();

    World getWorld();
    World getNetherWorld();

    boolean isTrusted(String playerName);
    boolean isTrusted(UUID playerUUID);
    boolean isOwner(String playerName);
    boolean isOwner(UUID playerUUID);

    boolean isMember(Player player);
    boolean isMember(UUID playerUUID);

    boolean exists();
    boolean existsNether();

    boolean containsLocation(Location location);
}