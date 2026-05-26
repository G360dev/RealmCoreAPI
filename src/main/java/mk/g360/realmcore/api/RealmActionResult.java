package mk.g360.realmcore.api;

/**
 * Represents the result of a realm API action.
 * This result can be used to check whether an operation succeeded and why it failed.
 *
 * @param success Whether the operation succeeded
 * @param code A short machine-readable result code
 * @param message A human-readable result message
 *
 * @author G360 Development Team
 * @version 1.6.00
 */
public record RealmActionResult(boolean success, String code, String message) {

    /**
     * Creates a successful action result.
     *
     * @param message The human-readable success message
     * @return A successful action result
     */
    public static RealmActionResult ok(String message) {
        return new RealmActionResult(true, "OK", message);
    }

    /**
     * Creates a failed action result.
     *
     * @param code A short machine-readable failure code
     * @param message The human-readable failure message
     * @return A failed action result
     */
    public static RealmActionResult fail(String code, String message) {
        return new RealmActionResult(false, code, message);
    }
}

