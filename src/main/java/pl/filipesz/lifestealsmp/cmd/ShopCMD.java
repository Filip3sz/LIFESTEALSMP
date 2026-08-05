package pl.filipesz.lifestealsmp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import pl.filipesz.lifestealsmp.gui.ShopGUI;

public class ShopCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Ta komenda jest tylko dla gracza.");
            return false;
        }
        Player p = (Player) sender;
        ShopGUI.openGui(p);
        return true;
    }
}
