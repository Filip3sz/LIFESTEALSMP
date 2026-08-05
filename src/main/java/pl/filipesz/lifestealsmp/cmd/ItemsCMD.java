package pl.filipesz.lifestealsmp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import pl.filipesz.lifestealsmp.gui.ItemsGUI;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.io.Console;

public class ItemsCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Ta komenda jest tylko dla gracza.");
            return false;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("cmd.adm.items")) {
            UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cBrak uprawnien.");
            return false;
        }
        ItemsGUI.openGui(p);
        return true;
    }
}
