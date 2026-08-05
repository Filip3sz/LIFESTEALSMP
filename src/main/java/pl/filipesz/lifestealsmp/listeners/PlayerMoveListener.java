package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;
import pl.filipesz.lifestealsmp.cmd.SpawnCMD;
import pl.filipesz.lifestealsmp.utils.UtilManager;

@SuppressWarnings("ALL")
public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        World world = Bukkit.getWorld("world");
        WorldBorder wb = world.getWorldBorder();
        BukkitTask task = SpawnCMD.tp.get(p.getUniqueId());

        if (loc.getBlockX() > wb.getSize() / 2 - 30 || loc.getBlockX() < wb.getSize() / 2 - wb.getSize() + 30 || loc.getBlockZ() > wb.getSize() / 2 - 30 || loc.getBlockZ() < wb.getSize() / 2 - wb.getSize() + 30) {
            p.playSound(p.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1, 1);
            p.sendActionBar(UtilManager.fixColor("&cZblizasz sie do borderu."));
        }

        if (task != null) {
            if (e.getFrom().getBlockX() != e.getTo().getBlockX()
                    || e.getFrom().getBlockY() != e.getTo().getBlockY()
                    || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
                task.cancel();
                SpawnCMD.tp.remove(p.getUniqueId());
                UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cTeleport zostal anulowany.");
            }
        }
    }
}
