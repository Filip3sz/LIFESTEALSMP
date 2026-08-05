package pl.filipesz.lifestealsmp.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import pl.filipesz.lifestealsmp.structs.UserFile;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.io.Console;
import java.util.UUID;

public class RankResetCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Ta komenda jest tylko dla gracza.");
            return false;
        }
        Player p = (Player) sender;
        UUID uuid = p.getUniqueId();
        double cash = UserFile.getCash(uuid);
        if (cash >= 100.00) {
            UserFile.removeCash(uuid, 100.00);
            UserFile.rankReset(uuid);
            UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &ePomyslnie zresetowales ranking!");
        } else {
            UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cNie posiadasz pieniedzy o ilosci 100.00!");
        }
        return false;
    }
}
