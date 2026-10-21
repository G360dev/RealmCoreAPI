package mk.g360.realmcore.api;

/**
 * Stable, typed membership roles exposed to RealmCore integrations.
 */
public enum RealmRole {
    OWNER,
    RESIDENT,
    TRUSTED,
    VISITOR;

    /**
     * Returns whether this role represents a member rather than a visitor.
     *
     * @return {@code true} for owners, residents, and trusted players
     */
    public boolean isMember() {
        return this != VISITOR;
    }
}
