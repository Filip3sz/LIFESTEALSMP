package pl.filipesz.lifestealsmp.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import pl.filipesz.lifestealsmp.Main;
import pl.filipesz.lifestealsmp.structs.UserFile;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("ALL")
public class UtilManager {

    //ChatUtil
    public static String fixColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void sendMessage(CommandSender commandSender, String string) {
        commandSender.sendMessage(fixColor(string));
    }

    // Clear Items
    public static void clearItems() {
        World[] worlds = Bukkit.getServer().getWorlds().toArray(new World[0]);
        World[] array;

        for (int length = (array = worlds).length, i = 0; i < length; i++) {
            World world = array[i];

            for (org.bukkit.entity.Entity e : world.getEntities()) {
                if (!(e instanceof Item)
                        && !(e instanceof FallingBlock)
                        && !(e instanceof Arrow)) {
                    continue;
                }
                e.remove();
            }
        }
    }

    // ScoreboardUtil
    private static final DecimalFormat dfBorder = new DecimalFormat("0");

    public static void createScoreboard(Player p) {
        ScoreboardManager m = Bukkit.getScoreboardManager();
        Scoreboard b = m.getNewScoreboard();
        Objective o = b.registerNewObjective("Stats", "dummy");
        o.setDisplayName(UtilManager.fixColor("&3&lLIFESTEAL SMP"));
        o.setDisplaySlot(DisplaySlot.SIDEBAR);

        World world = Bukkit.getWorld("world");

        UUID uuid = p.getUniqueId();
        int players = Bukkit.getOnlinePlayers().size();
        int ping = p.getPing();
        int hearts = (int) (UserFile.getHearts(uuid) / 2.0);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", symbols);

        Score s = o.getScore(UtilManager.fixColor(""));
        s.setScore(10);

        Score s2 = o.getScore(UtilManager.fixColor("&7Gracze online:"));
        s2.setScore(9);

        Score s3 = o.getScore(UtilManager.fixColor("&8» &3" + players + "&7/&3100"));
        s3.setScore(8);

        Score s4 = o.getScore(UtilManager.fixColor(" "));
        s4.setScore(7);

        Score s5 = o.getScore(UtilManager.fixColor("&8» &7Zabojstwa: &3" + UserFile.getKills(uuid)));
        s5.setScore(6);

        Score s6 = o.getScore(UtilManager.fixColor("&8» &7Smierci: &3" + UserFile.getDeaths(uuid)));
        s6.setScore(5);

        Score s7 = o.getScore(UtilManager.fixColor("&8» &7Serca: &3" + hearts));
        s7.setScore(4);

        Score s8 = o.getScore(UtilManager.fixColor("&8» &7Pieniadze: " + df.format(UserFile.getCash(uuid))));
        s8.setScore(3);

        Score s9 = o.getScore(UtilManager.fixColor("  "));
        s9.setScore(2);

        Score s10 = o.getScore(UtilManager.fixColor("&7Faza testowa &3&lFILIPESZ-CORE"));
        s10.setScore(1);

        p.setScoreboard(b);
    }

    public static void updateScoreboard() {
        for (Player everyone : Bukkit.getOnlinePlayers()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    createScoreboard(everyone);
                }
            }.runTaskTimer(Main.getInstance(), 40L, 40L);
        }
    }
}
