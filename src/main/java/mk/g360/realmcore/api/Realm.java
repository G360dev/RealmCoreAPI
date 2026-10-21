package mk.g360.realmcore.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

/**
 * Represents a player's Realm in the RealmCore system.
 * A realm is a protected area owned by a player that can have trusted members.
 *
 * <h2>Threading contract</h2>
 * This legacy interface is synchronous. Metadata methods may perform SQL or file I/O
 * on the calling thread, while methods returning or mutating Bukkit objects require
 * normal Bukkit thread affinity. Prefer the asynchronous operations exposed by
 * {@link RealmCoreAPI} for storage-backed reads and realm-bank mutations.
 *
 * @author G360 Development Team
 * @version 1.8.00
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
     * Gets the UUID of the realm owner when the owner is online.
     *
     * @return The UUID of the realm owner, or {@code null} if the owner is
     *         offline or unavailable
     */
    UUID getOwnerUUID();

    /**
     * Gets a snapshot of trusted player names.
     *
     * @return The trusted player names; the snapshot is empty when there are
     *         no trusted players
     */
    List<String> getTrusted();

    /**
     * Gets UUIDs for trusted players that are currently online.
     *
     * @return UUIDs of online trusted players; offline players are omitted
     */
    List<UUID> getTrustedUUIDs();

    /**
     * Gets a snapshot of resident player names.
     *
     * @return The resident player names; the snapshot is empty when there are
     *         no residents
     */
    List<String> getResidents();

    /**
     * Gets UUIDs for residents that are currently online.
     *
     * @return UUIDs of online residents; offline players are omitted
     */
    List<UUID> getResidentsUUIDs();

    /**
     * Gets the loaded main world associated with this realm.
     *
     * @return The realm's main world, or {@code null} when it is not loaded
     */
    World getWorld();

    /**
     * Gets the nether world associated with this realm.
     *
     * @return The realm's Nether world, or {@code null} when it is not loaded
     *         or has not been generated
     */
    World getNetherWorld();

    /**
     * Gets the number of votes for this realm.
     *
     * @return The realm's total votes
     */
    int getRealmVotes();

    /**
     * Returns an immutable snapshot of players banned from this realm.
     *
     * @return The banned player names; the default compatibility result is
     *         empty
     */
    default List<String> getBannedPlayers() {
        return List.of();
    }

    /**
     * Checks a banned-player snapshot case-insensitively.
     *
     * @param playerName player name to check; {@code null} is never banned
     * @return {@code true} when the snapshot contains the player name
     */
    default boolean isBanned(String playerName) {
        return playerName != null && getBannedPlayers().stream()
                .anyMatch(entry -> entry.equalsIgnoreCase(playerName));
    }

    /**
     * Returns the configured border size for this realm.
     *
     * <p>RealmCore returns the persisted value, which remains available while
     * the world is unloaded. Compatibility implementations may return the
     * loaded world's value or {@code -1} when they cannot read unloaded
     * metadata.</p>
     *
     * @return the configured border diameter, or {@code -1} when unavailable
     */
    default int getBorderSize() {
        World world = getWorld();
        return world == null ? -1 : (int) Math.round(world.getWorldBorder().getSize());
    }

    /**
     * Returns the realm bank balance, or zero for legacy API implementations.
     * This is a synchronous storage read; prefer
     * {@link RealmCoreAPI#getBankBalanceAsync(String)} in tick-sensitive code.
     */
    default double getBankBalance() {
        return 0.0d;
    }

    /**
     * Returns whether visitors may enter this realm.
     *
     * @return {@code true} when the realm is open; compatibility defaults may
     *         return {@code false}
     */
    default boolean isOpen() {
        return false;
    }

    /**
     * Returns the player's effective role in this realm.
     *
     * @param playerName player name to inspect
     * @return {@code OWNER}, {@code RESIDENT}, {@code TRUSTED}, or
     *         {@code VISITOR}
     */
    default String getRole(String playerName) {
        if (isOwner(playerName)) return "OWNER";
        if (isResident(playerName)) return "RESIDENT";
        if (isTrusted(playerName)) return "TRUSTED";
        return "VISITOR";
    }

    /**
     * Returns the player's effective role using their stable UUID identity.
     *
     * @param playerUUID UUID to inspect; {@code null} is treated as a visitor
     * @return the typed role for this realm
     */
    default RealmRole getRole(UUID playerUUID) {
        if (playerUUID == null) return RealmRole.VISITOR;
        if (isOwner(playerUUID)) return RealmRole.OWNER;
        if (isResident(playerUUID)) return RealmRole.RESIDENT;
        if (isTrusted(playerUUID)) return RealmRole.TRUSTED;
        return RealmRole.VISITOR;
    }

    /**
     * Returns a clone of the currently loaded realm spawn.
     *
     * @return the spawn location, or {@link Optional#empty()} when the main
     *         world is not loaded
     */
    default Optional<Location> getSpawnLocation() {
        World world = getWorld();
        return world == null ? Optional.empty() : Optional.of(world.getSpawnLocation().clone());
    }

    /**
     * Tests whether the main realm world is currently loaded.
     *
     * @return {@code true} when {@link #getWorld()} is non-null
     */
    default boolean isLoaded() {
        return getWorld() != null;
    }

    /**
     * Checks if a player is trusted in this realm by name.
     *
     * @param playerName The player name to check
     * @return {@code true} if the player is trusted, {@code false} otherwise
     */
    boolean isTrusted(String playerName);

    /**
     * Checks if a player is trusted in this realm by UUID.
     *
     * @param playerUUID The player UUID to check
     * @return {@code true} if the player is trusted, {@code false} otherwise
     */
    boolean isTrusted(UUID playerUUID);

    /**
     * Checks if a player is resident in this realm by name.
     *
     * @param playerName The player name to check
     * @return {@code true} if the player is a resident, {@code false} otherwise
     */
    boolean isResident(String playerName);

    /**
     * Checks if a player is resident in this realm by UUID.
     *
     * @param playerUUID The player UUID to check
     * @return {@code true} if the player is a resident, {@code false} otherwise
     */
    boolean isResident(UUID playerUUID);

    /**
     * Checks if a player is the owner of this realm by name.
     *
     * @param playerName The player name to check; {@code null} is never the owner
     * @return {@code true} if the player is the owner, {@code false} otherwise
     */
    boolean isOwner(String playerName);

    /**
     * Checks if a player is the owner of this realm by UUID.
     *
     * @param playerUUID The player UUID to check
     * @return {@code true} if the player is the owner, {@code false} otherwise
     */
    boolean isOwner(UUID playerUUID);

    /**
     * Checks if a player is a member of this realm (owner or trusted).
     *
     * @param player The player to check
     * @return {@code true} if the player is the owner or trusted, {@code false}
     *         otherwise
     */
    boolean isMember(Player player);

    /**
     * Checks if a player is a member of this realm by UUID.
     *
     * @param playerUUID The player UUID to check
     * @return {@code true} if the player is the owner or trusted, {@code false}
     *         otherwise
     */
    boolean isMember(UUID playerUUID);

    /**
     * Checks if this realm exists in the main world.
     *
     * @return {@code true} if the realm exists, {@code false} otherwise
     */
    boolean exists();

    /**
     * Checks if this realm's nether world exists.
     *
     * @return {@code true} if the Nether world exists, {@code false} otherwise
     */
    boolean existsNether();

    /**
     * Checks if the specified location is within this realm's boundaries.
     *
     * @param location The location to check
     * @return {@code true} if the location belongs to this realm, {@code false}
     *         otherwise
     */
    boolean containsLocation(Location location);

    /**
     * Sets the world border size for this realm.
     *
     * @param newSize The new border size
     * @param transition The transition duration; {@code null}, zero, or a
     *                   negative duration applies immediately
     * @return The operation result. Implementations may reject the operation
     *         when the owner is offline.
     */
    RealmActionResult setBorder(int newSize, Duration transition);

    /**
     * Sets whether this realm is open to visitors.
     *
     * @param open {@code true} to open the realm, {@code false} to close it
     * @return The operation result. Implementations may reject the operation
     *         when the owner is offline.
     */
    RealmActionResult setOpen(boolean open);

    /**
     * Adds a trusted player to this realm.
     *
     * @param playerName The player name to trust
     * @return The operation result; implementations may report an offline owner
     *         or a backend validation failure
     */
    RealmActionResult addTrusted(String playerName);

    /**
     * Removes a trusted player from this realm.
     *
     * @param playerName The player name to untrust
     * @return The operation result; implementations may report an offline owner
     *         or a backend validation failure
     */
    RealmActionResult removeTrusted(String playerName);

    /**
     * Adds a resident player to this realm.
     *
     * @param playerName The player name to add as resident
     * @return The operation result; implementations may report an offline owner
     *         or a backend validation failure
     */
    RealmActionResult addResident(String playerName);

    /**
     * Removes a resident player from this realm.
     *
     * @param playerName The player name to remove as resident
     * @return The operation result; implementations may report an offline owner
     *         or a backend validation failure
     */
    RealmActionResult removeResident(String playerName);

    /**
     * Bans a player from this realm.
     *
     * @param playerName The player name to ban
     * @return The operation result; implementations may report an offline owner
     *         or a backend validation failure
     */
    RealmActionResult addBan(String playerName);

    /**
     * Unbans a player from this realm.
     *
     * @param playerName The player name to unban
     * @return The operation result; implementations may report an offline owner
     *         or a backend validation failure
     */
    RealmActionResult removeBan(String playerName);

    /**
     * Adds money to this realm's bank balance.
     * This legacy method is synchronous; prefer
     * {@link RealmCoreAPI#addBankBalanceAsync(String, double)}.
     *
     * @param amount The finite, positive amount to add
     * @return {@code INVALID_AMOUNT} for a non-positive or non-finite amount;
     *         otherwise the operation result
     */
    RealmActionResult addBankBalance(double amount);

    /**
     * Subtracts money from this realm's bank balance.
     * This legacy method is synchronous; prefer
     * {@link RealmCoreAPI#subtractBankBalanceAsync(String, double)}.
     *
     * @param amount The finite, positive amount to subtract
     * @return {@code INVALID_AMOUNT} for a non-positive or non-finite amount,
     *         {@code INSUFFICIENT_FUNDS} when the balance is too low, or success
     */
    RealmActionResult subtractBankBalance(double amount);

    /**
     * Resets this realm's bank balance.
     * This legacy method is synchronous; prefer
     * {@link RealmCoreAPI#resetBankBalanceAsync(String)}.
     *
     * @return The operation result after resetting the balance
     */
    RealmActionResult resetBankBalance();

    /**
     * Adds a vote to this realm.
     *
     * @param voterName The player name voting for this realm
     * @return {@code INVALID_VOTER} for a blank name,
     *         {@code ALREADY_VOTED} when the player already voted, or success
     */
    RealmActionResult addVote(String voterName);

    /**
     * Removes a vote from this realm.
     *
     * @param voterName The player name whose vote should be removed
     * @return {@code INVALID_VOTER} for a blank name,
     *         {@code NOT_VOTED} when no vote exists, or success
     */
    RealmActionResult removeVote(String voterName);
}
