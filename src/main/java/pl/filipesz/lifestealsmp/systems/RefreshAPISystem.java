package pl.filipesz.lifestealsmp.systems;

import org.bukkit.scheduler.BukkitRunnable;
import pl.filipesz.lifestealsmp.Main;
import pl.filipesz.lifestealsmp.utils.UtilManager;

public class RefreshAPISystem {

    public static void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                UtilManager.updateScoreboard();
            }
        }.runTaskTimer(Main.getInstance(), 20L, 20L);
    }
}
