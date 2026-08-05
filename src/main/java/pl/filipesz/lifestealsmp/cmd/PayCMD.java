package pl.filipesz.lifestealsmp.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import pl.filipesz.lifestealsmp.structs.UserFile;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.util.UUID;

public class PayCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("Ta komenda jest tylko dla gracza.");
            return false;
        }
        Player p = (Player) sender;
        if (args.length <= 1) {
            UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cPoprawne uzycie: &3/pay &7(&3nick&7) &7(&3wartosc&7)");
        } else {
            Player t = Bukkit.getPlayer(args[0]);
            UUID pUuid = p.getUniqueId();
            UUID tUuid = t.getUniqueId();
            double cash;
            double balance = UserFile.getCash(pUuid);
            if (t != null) {
                try {
                    cash = Double.parseDouble(args[1]);
                } catch (Exception e) {
                    UtilManager.sendMessage(p, "&cPodaj poprawną kwotę!");
                    return true;
                }
                if (cash <= balance || cash == 0) {
                    UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cBledna wartosc.");
                } else {
                    UserFile.removeCash(pUuid, cash);
                    UserFile.addCash(tUuid, cash);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &ePrzelano &3" + cash + " &egraczowi &7" + t.getName());
                    UtilManager.sendMessage(t, "&8[&e&l!&8] &8» &eOtrzymales &3" + cash + " &eod gracza &7" + p.getName());
                }
            } else {
                UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cTen gracz jest offline badz nie istnieje.");
            }
        }
        return false;
    }
}
