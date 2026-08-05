package pl.filipesz.lifestealsmp.cmd;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import pl.filipesz.lifestealsmp.Main;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.util.HashMap;
import java.util.UUID;

@SuppressWarnings("ALL")
public class SpawnCMD implements CommandExecutor {

    public static HashMap<UUID, BukkitTask> tp = new HashMap<>();
    BukkitTask teleport;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Ta komenda jest tylko dla gracza.");
            return false;
        }
        Player p = (Player) sender;
        if (p.hasPermission("cmd.adm.spawn")) {
            p.teleport(new Location(p.getWorld(), 0, p.getLocation().getWorld().getHighestBlockYAt(0, 0), 0), PlayerTeleportEvent.TeleportCause.PLUGIN);
            UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &ePomyślnie przeteleportowano na spawn.");
        } else {
            teleport = new BukkitRunnable() {
                @Override
                public void run() {
                    p.teleport(new Location(p.getWorld(), 0, p.getWorld().getHighestBlockYAt(0, 0), 0), PlayerTeleportEvent.TeleportCause.PLUGIN);

                    tp.remove(p.getUniqueId());
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &ePomyślnie przeteleportowano na spawn.");
                }
            }.runTaskLater(Main.getInstance(), 100L);
            tp.put(p.getUniqueId(), teleport);
            UtilManager.sendMessage(sender, "&8[&e&l!&8] &8» &eRozgrzewam teleport...");
        }
        return false;
    }
}
