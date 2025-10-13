package mk.g360.realmcore.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public interface RealmCoreAPI {
    Realm getRealm(Player player);
    Realm getRealm(UUID playerUUID);

    // Nuevos métodos solicitados
    Realm getRealm(Location location);
    boolean hasAnyRealm(Player player);
    boolean hasAnyRealm(UUID playerUUID);

    Realm getRealmByName(String realmName);

    default String getVersion() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("RealmCore");
        return plugin != null ? plugin.getDescription().getVersion() : "unknown";
    }

    static RealmCoreAPI getInstance() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("RealmCore");
        return plugin instanceof RealmCoreProvider ? ((RealmCoreProvider) plugin).getAPI() : null;
    }
}