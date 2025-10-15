package mk.g360.realmcore.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

/**
 * Main API interface for RealmCore plugin functionality.
 * Provides methods to interact with player realms and related operations.
 *
 * @author G360 Development Team
 * @version 1.2.00
 */
public interface RealmCoreAPI {
    /**
     * Retrieves the realm associated with the specified player.
     *
     * @param player The player to get the realm for
     * @return The player's realm, or null if not found
     */
    Realm getRealm(Player player);

    /**
     * Retrieves the realm associated with the specified player UUID.
     *
     * @param playerUUID The UUID of the player to get the realm for
     * @return The player's realm, or null if not found
     */
    Realm getRealm(UUID playerUUID);

    /**
     * Retrieves the realm that contains the specified location.
     *
     * @param location The location to check for realm ownership
     * @return The realm containing the location, or null if not found
     */
    Realm getRealm(Location location);

    /**
     * Checks if a player has any realm associated with their account.
     *
     * @param player The player to check
     * @return true if the player has at least one realm, false otherwise
     */
    boolean hasAnyRealm(Player player);

    /**
     * Checks if a player (by UUID) has any realm associated with their account.
     *
     * @param playerUUID The UUID of the player to check
     * @return true if the player has at least one realm, false otherwise
     */
    boolean hasAnyRealm(UUID playerUUID);

    /**
     * Retrieves a realm by its name.
     *
     * @param realmName The name of the realm to find
     * @return The realm with the specified name, or null if not found
     */
    Realm getRealmByName(String realmName);

    /**
     * Gets the current version of the RealmCore plugin.
     *
     * @return The plugin version as a string, or "unknown" if not available
     */
    default String getVersion() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("RealmCore");
        return plugin != null ? plugin.getDescription().getVersion() : "unknown";
    }

    /**
     * Static method to obtain the RealmCore API instance.
     * This is the recommended way to access the API.
     *
     * @return The RealmCoreAPI instance, or null if the plugin is not enabled
     */
    static RealmCoreAPI getInstance() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("RealmCore");
        return plugin instanceof RealmCoreProvider ? ((RealmCoreProvider) plugin).getAPI() : null;
    }
}