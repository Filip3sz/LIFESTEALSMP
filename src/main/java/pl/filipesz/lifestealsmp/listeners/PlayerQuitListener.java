package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class PlayerQuitListener implements Listener {

    private final HashMap<Player, BukkitTask> combatLog;

    public PlayerQuitListener(HashMap<Player, BukkitTask> combatLog) {
        this.combatLog = combatLog;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Player t = e.getPlayer().getKiller();
        e.setQuitMessage("");
        if (combatLog.containsKey(p)) {
            p.setHealth(0);
            combatLog.get(p).cancel();
            Bukkit.broadcastMessage(UtilManager.fixColor("&8[&c&l!&8] &8» &7" + p.getName() + " &czostal zabity przez &7" + t.getName()));
        }
    }
}
