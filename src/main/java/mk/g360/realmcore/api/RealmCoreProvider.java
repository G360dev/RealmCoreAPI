package mk.g360.realmcore.api;

/**
 * Provider interface for the RealmCore API.
 * This interface serves as the entry point for obtaining the RealmCore API instance.
 *
 * @author G360 Development Team
 * @version 1.8.00
 */
public interface RealmCoreProvider {
    /**
     * Retrieves the main API instance for RealmCore.
     *
     * @return The RealmCoreAPI instance, or null if not available
     */
    RealmCoreAPI getAPI();
}
