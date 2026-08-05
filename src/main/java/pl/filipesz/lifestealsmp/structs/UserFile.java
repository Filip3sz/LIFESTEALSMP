package pl.filipesz.lifestealsmp.structs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@SuppressWarnings("ALL")
public class UserFile {

    private static File file;
    private static FileConfiguration config;

    public static void setup(JavaPlugin plugin) {
        file = new File(plugin.getDataFolder(), "users.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return config;
    }

    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double getHearts(UUID uuid) {
        return config.getDouble("users." + uuid + ".getHearts");
    }

    public static void addHearts(UUID uuid) {
        double hearts = getHearts(uuid);
        if (hearts == 60.0) {
            return;
        }
        config.set("users." + uuid + ".getHearts", hearts + 2.0);
        save();
    }

    public static void removeHearts(UUID uuid) {
        double hearts = getHearts(uuid);
        if (hearts == 2.0) {
            return;
        }
        config.set("users." + uuid + ".getHearts", hearts - 2.0);
        save();
    }

    public static double getCash(UUID uuid) {
        return config.getDouble("users." + uuid + ".cash");
    }

    public static void addCash(UUID uuid, double amount) {
        double cash = getCash(uuid);
        config.set("users." + uuid + ".cash", cash += amount);
        save();
    }

    public static void removeCash(UUID uuid, double amount) {
        double cash = getCash(uuid);
        config.set("users." + uuid + ".cash", cash -= amount);
        save();
    }

    public static int getKills(UUID uuid) {
        return config.getInt("users." + uuid + ".kills");
    }

    public static void addKills(UUID uuid) {
        int kills = getKills(uuid);
        config.set("users." + uuid + ".kills", kills + 1);
        save();
    }

    public static int getDeaths(UUID uuid) {
        return config.getInt("users." + uuid + ".deaths");
    }

    public static void addDeaths(UUID uuid) {
        int deaths = getDeaths(uuid);
        config.set("users." + uuid + ".deaths", deaths + 1);
        save();
    }

    public static void rankReset(UUID uuid) {
        config.set("users." + uuid + ".kills", 0);
        config.set("users." + uuid + ".deaths", 0);
        save();
    }
}
