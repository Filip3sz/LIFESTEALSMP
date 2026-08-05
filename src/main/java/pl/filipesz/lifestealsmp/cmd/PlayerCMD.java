package pl.filipesz.lifestealsmp.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import pl.filipesz.lifestealsmp.structs.UserFile;
import pl.filipesz.lifestealsmp.utils.UtilManager;

public class PlayerCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender p = (ConsoleCommandSender) sender;
            if (args.length == 0) {
                UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cPoprawne uzycie: &3/gracz &7(&3nick&7)");
            } else if (args.length == 1) {
                Player t = Bukkit.getPlayer(args[0]);
                if (t != null) {
                    int kills = UserFile.getKills(t.getUniqueId());
                    int deaths = UserFile.getDeaths(t.getUniqueId());
                    UtilManager.sendMessage(p, "     &7&m----------&8[ &8* &3&lGracz &8* ]&7&m----------");
                    UtilManager.sendMessage(p, "&8» &7Nick: &3" + t.getName());
                    UtilManager.sendMessage(p, "&8» &7Zabojstwa: &3" + kills);
                    UtilManager.sendMessage(p, "&8» &7Smierci: &3" + deaths);
                } else {
                    UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cNie znaleziono takiego gracza w bazie.");
                }
            }
            return false;
        }
        Player p = (Player) sender;
        if (args.length == 0) {
            int kills = UserFile.getKills(p.getUniqueId());
            int deaths = UserFile.getDeaths(p.getUniqueId());
            UtilManager.sendMessage(p, "     &7&m----------&8[ &8* &3&lGracz &8* ]&7&m----------");
            UtilManager.sendMessage(p, "&8» &7Nick: &3" + p.getName());
            UtilManager.sendMessage(p, "&8» &7Zabojstwa &3" + kills);
            UtilManager.sendMessage(p, "&8» &7Smierci &3" + deaths);
        } else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if (t != null) {
                int kills = UserFile.getKills(t.getUniqueId());
                int deaths = UserFile.getDeaths(t.getUniqueId());
                UtilManager.sendMessage(p, "     &7&m----------&8[ &8* &3&lGracz &8* ]&7&m----------");
                UtilManager.sendMessage(p, "&8» &7Nick: &3" + t.getName());
                UtilManager.sendMessage(p, "&8» &7Zabojstwa &3" + kills);
                UtilManager.sendMessage(p, "&8» &7Smierci &3" + deaths);
            } else {
                UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cNie znaleziono takiego gracza w bazie.");
            }
        }
        return false;
    }
}
