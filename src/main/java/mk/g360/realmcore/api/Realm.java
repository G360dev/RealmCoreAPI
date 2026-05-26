package mk.g360.realmcore.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

/**
 * Represents a player's Realm in the RealmCore system.
 * A realm is a protected area owned by a player that can have trusted members.
 *
 * @author G360 Development Team
 * @version 1.6.00
 */
public interface Realm {
    /**
     * Gets the name of this realm.
     *
     * @return The realm name
     */
    String getName();

    /**
     * Gets the owner of this realm as a Player object.
     *
     * @return The realm owner as Player, or null if offline
     */
    Player getOwner();

    /**
     * Gets the UUID of the realm owner.
     *
     * @return The UUID of the realm owner
     */
    UUID getOwnerUUID();

    /**
     * Gets a list of trusted player names.
     *
     * @return List of trusted player names
     */
    List<String> getTrusted();

    /**
     * Gets a list of trusted player UUIDs.
     *
     * @return List of trusted player UUIDs
     */
    List<UUID> getTrustedUUIDs();

    /**
     * Gets a list of residents player names.
     *
     * @return List of trusted player UUIDs
     */
    List<String> getResidents();

    /**
     * Gets a list of residents player UUIDs.
     *
     * @return List of trusted player UUIDs
     */
    List<UUID> getResidentsUUIDs();

    /**
     * Gets the main world associated with this realm.
     *
     * @return The realm's main world
     */
    World getWorld();

    /**
     * Gets the nether world associated with this realm.
     *
     * @return The realm's nether world, or null if not generated
     */
    World getNetherWorld();

    /**
     * Gets the number of votes for this realm.
     *
     * @return The realm's total votes
     */
    int getRealmVotes();

    /**
     * Checks if a player is trusted in this realm by name.
     *
     * @param playerName The player name to check
     * @return true if the player is trusted, false otherwise
     */
    boolean isTrusted(String playerName);

    /**
     * Checks if a player is trusted in this realm by UUID.
     *
     * @param playerUUID The player UUID to check
     * @return true if the player is trusted, false otherwise
     */
    boolean isTrusted(UUID playerUUID);

    /**
     * Checks if a player is resident in this realm by name.
     *
     * @param playerName The player name to check
     * @return true if the player is trusted, false otherwise
     */
    boolean isResident(String playerName);

    /**
     * Checks if a player is resident in this realm by UUID.
     *
     * @param playerUUID The player UUID to check
     * @return true if the player is trusted, false otherwise
     */
    boolean isResident(UUID playerUUID);

    /**
     * Checks if a player is the owner of this realm by name.
     *
     * @param playerName The player name to check
     * @return true if the player is the owner, false otherwise
     */
    boolean isOwner(String playerName);

    /**
     * Checks if a player is the owner of this realm by UUID.
     *
     * @param playerUUID The player UUID to check
     * @return true if the player is the owner, false otherwise
     */
    boolean isOwner(UUID playerUUID);

    /**
     * Checks if a player is a member of this realm (owner or trusted).
     *
     * @param player The player to check
     * @return true if the player is a member, false otherwise
     */
    boolean isMember(Player player);

    /**
     * Checks if a player is a member of this realm by UUID.
     *
     * @param playerUUID The player UUID to check
     * @return true if the player is a member, false otherwise
     */
    boolean isMember(UUID playerUUID);

    /**
     * Checks if this realm exists in the main world.
     *
     * @return true if the realm exists, false otherwise
     */
    boolean exists();

    /**
     * Checks if this realm's nether world exists.
     *
     * @return true if the nether world exists, false otherwise
     */
    boolean existsNether();

    /**
     * Checks if the specified location is within this realm's boundaries.
     *
     * @param location The location to check
     * @return true if the location is within this realm, false otherwise
     */
    boolean containsLocation(Location location);

    /**
     * Sets the world border size for this realm.
     *
     * @param newSize The new border size
     * @param transition The transition duration, or null to apply instantly
     * @return The result of the operation
     */
    RealmActionResult setBorder(int newSize, Duration transition);

    /**
     * Sets whether this realm is open to visitors.
     *
     * @param open true to open the realm, false to close it
     * @return The result of the operation
     */
    RealmActionResult setOpen(boolean open);

    /**
     * Adds a trusted player to this realm.
     *
     * @param playerName The player name to trust
     * @return The result of the operation
     */
    RealmActionResult addTrusted(String playerName);

    /**
     * Removes a trusted player from this realm.
     *
     * @param playerName The player name to untrust
     * @return The result of the operation
     */
    RealmActionResult removeTrusted(String playerName);

    /**
     * Adds a resident player to this realm.
     *
     * @param playerName The player name to add as resident
     * @return The result of the operation
     */
    RealmActionResult addResident(String playerName);

    /**
     * Removes a resident player from this realm.
     *
     * @param playerName The player name to remove as resident
     * @return The result of the operation
     */
    RealmActionResult removeResident(String playerName);

    /**
     * Bans a player from this realm.
     *
     * @param playerName The player name to ban
     * @return The result of the operation
     */
    RealmActionResult addBan(String playerName);

    /**
     * Unbans a player from this realm.
     *
     * @param playerName The player name to unban
     * @return The result of the operation
     */
    RealmActionResult removeBan(String playerName);

    /**
     * Adds money to this realm's bank balance.
     *
     * @param amount The amount to add
     * @return The result of the operation
     */
    RealmActionResult addBankBalance(double amount);

    /**
     * Subtracts money from this realm's bank balance.
     *
     * @param amount The amount to subtract
     * @return The result of the operation
     */
    RealmActionResult subtractBankBalance(double amount);

    /**
     * Resets this realm's bank balance.
     *
     * @return The result of the operation
     */
    RealmActionResult resetBankBalance();

    /**
     * Adds a vote to this realm.
     *
     * @param voterName The player name voting for this realm
     * @return The result of the operation
     */
    RealmActionResult addVote(String voterName);

    /**
     * Removes a vote from this realm.
     *
     * @param voterName The player name whose vote should be removed
     * @return The result of the operation
     */
    RealmActionResult removeVote(String voterName);
}
