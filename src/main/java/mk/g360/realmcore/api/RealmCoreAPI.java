package mk.g360.realmcore.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Main API interface for RealmCore plugin functionality.
 * Provides methods to interact with player realms and related operations.
 *
 * <h2>Threading contract</h2>
 * Legacy methods are synchronous and execute on the calling thread; they may read
 * persistent storage. Methods accepting Bukkit objects must only be
 * invoked where those objects are safe to access. RealmCore's 1.7 asynchronous
 * methods perform storage work on its bounded data executor, while compatibility
 * defaults may call the legacy method synchronously before returning a completed
 * future. Callbacks that access Bukkit state must return to the server thread.
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public interface RealmCoreAPI {
    /**
     * Retrieves the realm associated with the specified player.
     *
     * @param player The player to get the realm for
     * @return The player's realm, or {@code null} if not found
     */
    Realm getRealm(Player player);

    /**
     * Retrieves the realm associated with the specified player UUID.
     *
     * @param playerUUID The UUID of the player to get the realm for
     * @return The player's realm, or {@code null} if not found
     */
    Realm getRealm(UUID playerUUID);

    /**
     * Retrieves the realm that contains the specified location.
     *
     * @param location The location to check for realm ownership
     * @return The realm containing the location, or {@code null} if not found
     */
    Realm getRealm(Location location);

    /**
     * Checks if a player has any realm associated with their account.
     *
     * @param player The player to check
     * @return {@code true} if the player owns or belongs to at least one realm,
     *         {@code false} otherwise
     */
    boolean hasAnyRealm(Player player);

    /**
     * Checks if a player (by UUID) has any realm associated with their account.
     *
     * @param playerUUID The UUID of the player to check
     * @return {@code true} if the player owns or belongs to at least one realm,
     *         {@code false} otherwise
     */
    boolean hasAnyRealm(UUID playerUUID);

    /**
     * Retrieves a realm by its name.
     *
     * @param realmName The name of the realm to find
     * @return The realm with the specified name, or {@code null} if not found
     */
    Realm getRealmByName(String realmName);

    /**
     * Null-safe counterpart to {@link #getRealm(Player)}.
     * Added in 1.7 as a default method to preserve binary compatibility with API
     * implementors.
     *
     * @param player player to inspect; {@code null} is accepted
     * @return an optional containing the realm when found
     */
    default Optional<Realm> findRealm(Player player) {
        return Optional.ofNullable(getRealm(player));
    }

    /**
     * Null-safe counterpart to {@link #getRealm(UUID)}.
     *
     * @param playerUUID player UUID; {@code null} is accepted
     * @return an optional containing the realm when found
     */
    default Optional<Realm> findRealm(UUID playerUUID) {
        return Optional.ofNullable(getRealm(playerUUID));
    }

    /**
     * Null-safe counterpart to {@link #getRealm(Location)}.
     *
     * @param location location to inspect; {@code null} is accepted
     * @return an optional containing the realm when found
     */
    default Optional<Realm> findRealm(Location location) {
        return Optional.ofNullable(getRealm(location));
    }

    /**
     * Null-safe counterpart to {@link #getRealmByName(String)}.
     *
     * @param realmName owner name to inspect
     * @return an optional containing the realm when found
     */
    default Optional<Realm> findRealm(String realmName) {
        return Optional.ofNullable(getRealmByName(realmName));
    }

    /**
     * Returns all known realms. RealmCore returns an immutable snapshot;
     * implementations predating 1.7 safely return an empty list.
     *
     * @return a realm snapshot, empty when no realms are available
     */
    default List<Realm> getRealms() {
        return List.of();
    }

    /**
     * Returns realm snapshots in which the supplied player is owner, trusted,
     * or resident.
     *
     * @param playerName player name to inspect
     * @return matching realm snapshots, or an empty list when none are found
     */
    default List<Realm> getRealmsForMember(String playerName) {
        return List.of();
    }

    /**
     * Returns realm snapshots in which the supplied UUID is owner, trusted, or
     * resident.
     *
     * @param playerUUID player UUID to inspect
     * @return matching realm snapshots, or an empty list when none are found
     */
    default List<Realm> getRealmsForMember(UUID playerUUID) {
        return List.of();
    }

    /**
     * Asynchronously resolves a realm by owner name. RealmCore performs storage work on
     * its bounded data executor. For binary compatibility, the default implementation
     * may execute the legacy synchronous lookup before returning a completed future.
     *
     * @param realmName owner name to inspect
     * @return a future containing the realm when found, otherwise an empty optional
     */
    default CompletableFuture<Optional<Realm>> findRealmAsync(String realmName) {
        return CompletableFuture.completedFuture(findRealm(realmName));
    }

    /**
     * Asynchronously returns an immutable list of all known realms. RealmCore performs
     * the storage scan on its bounded data executor; the compatibility default may run
     * the legacy synchronous method before returning a completed future.
     *
     * @return a future containing a realm snapshot
     */
    default CompletableFuture<List<Realm>> getRealmsAsync() {
        return CompletableFuture.completedFuture(getRealms());
    }

    /**
     * Asynchronously returns realms in which the supplied player name is an owner,
     * trusted player, or resident.
     * <p>
     * RealmCore overrides this method with bounded executor semantics. The default
     * implementation preserves compatibility with third-party API implementors and
     * may execute synchronously before returning an already-completed future.
     *
     * @param playerName player name to inspect
     * @return a future containing matching realm snapshots
     */
    default CompletableFuture<List<Realm>> getRealmsForMemberAsync(String playerName) {
        return CompletableFuture.completedFuture(getRealmsForMember(playerName));
    }

    /**
     * Asynchronously reads a realm bank balance without requiring callers to invoke
     * the synchronous {@link Realm#getBankBalance()} method.
     * RealmCore overrides this method with bounded executor semantics. The compatibility
     * default may execute synchronously before returning a completed future.
     *
     * @param realmName owner name to inspect
     * @return a future containing the balance, or {@code 0.0} when the realm does not
     *         exist
     */
    default CompletableFuture<Double> getBankBalanceAsync(String realmName) {
        Realm realm = getRealmByName(realmName);
        return CompletableFuture.completedFuture(
                realm == null ? 0.0d : realm.getBankBalance());
    }

    /**
     * Asynchronously adds a positive, finite amount to a realm bank.
     * RealmCore serializes this mutation with other operations for the same realm.
     * The compatibility default may execute synchronously before returning a completed
     * future.
     *
     * @param realmName owner name to inspect
     * @param amount finite positive amount to add
     * @return a future containing {@code INVALID_AMOUNT}, {@code REALM_NOT_FOUND},
     *         or the delegated operation result
     */
    default CompletableFuture<RealmActionResult> addBankBalanceAsync(
            String realmName, double amount) {
        if (!Double.isFinite(amount) || amount <= 0.0d) {
            return CompletableFuture.completedFuture(RealmActionResult.fail(
                    "INVALID_AMOUNT", "Amount must be finite and greater than zero."));
        }
        Realm realm = getRealmByName(realmName);
        RealmActionResult result = realm == null
                ? RealmActionResult.fail("REALM_NOT_FOUND", "Realm not found.")
                : realm.addBankBalance(amount);
        return CompletableFuture.completedFuture(result);
    }

    /**
     * Asynchronously and atomically withdraws a positive, finite amount when enough
     * realm-bank funds are available.
     * RealmCore serializes this mutation on its bounded data executor. The compatibility
     * default may execute synchronously before returning a completed future.
     *
     * @param realmName owner name to inspect
     * @param amount finite positive amount to withdraw
     * @return a future containing {@code INVALID_AMOUNT}, {@code REALM_NOT_FOUND},
     *         {@code INSUFFICIENT_FUNDS}, or the delegated operation result
     */
    default CompletableFuture<RealmActionResult> subtractBankBalanceAsync(
            String realmName, double amount) {
        if (!Double.isFinite(amount) || amount <= 0.0d) {
            return CompletableFuture.completedFuture(RealmActionResult.fail(
                    "INVALID_AMOUNT", "Amount must be finite and greater than zero."));
        }
        Realm realm = getRealmByName(realmName);
        RealmActionResult result = realm == null
                ? RealmActionResult.fail("REALM_NOT_FOUND", "Realm not found.")
                : realm.subtractBankBalance(amount);
        return CompletableFuture.completedFuture(result);
    }

    /**
     * Asynchronously resets an existing realm bank to zero. RealmCore serializes this
     * mutation on its bounded data executor; the compatibility default may execute
     * synchronously before returning a completed future.
     *
     * @param realmName owner name to inspect
     * @return a future containing {@code REALM_NOT_FOUND} or the delegated operation
     *         result
     */
    default CompletableFuture<RealmActionResult> resetBankBalanceAsync(String realmName) {
        Realm realm = getRealmByName(realmName);
        RealmActionResult result = realm == null
                ? RealmActionResult.fail("REALM_NOT_FOUND", "Realm not found.")
                : realm.resetBankBalance();
        return CompletableFuture.completedFuture(result);
    }

    /**
     * Whether RealmCore currently stores realm metadata in SQL rather than YAML.
     * Compatibility implementations return {@code false} by default.
     *
     * @return {@code true} when SQL storage is active
     */
    default boolean isDatabaseStorage() {
        return false;
    }

    /**
     * Whether this instance is currently operating as a RealmCoreMulti backend.
     * Compatibility implementations return {@code false} by default.
     *
     * @return {@code true} when backend mode is active
     */
    default boolean isBackendMode() {
        return false;
    }

    /**
     * Gets the current version of the RealmCore plugin.
     *
     * @return The plugin version as a string, or {@code "unknown"} if not available
     */
    default String getVersion() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("RealmCore");
        return plugin != null ? plugin.getDescription().getVersion() : "unknown";
    }

    /**
     * Static method to obtain the RealmCore API instance.
     * This is the recommended way to access the API.
     *
     * @return The RealmCoreAPI instance, or {@code null} if the plugin is not enabled
     */
    static RealmCoreAPI getInstance() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("RealmCore");
        return plugin instanceof RealmCoreProvider ? ((RealmCoreProvider) plugin).getAPI() : null;
    }
}
