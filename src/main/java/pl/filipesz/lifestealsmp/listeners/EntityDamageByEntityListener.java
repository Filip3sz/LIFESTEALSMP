package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import pl.filipesz.lifestealsmp.Main;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class EntityDamageByEntityListener implements Listener {

    public static HashMap<Player, BukkitTask> combatLog = new HashMap<>();

    public HashMap<Player, BukkitTask> getCombatLog() {
        return combatLog;
    }

    public void addToCombat(Player p) {
        if (combatLog.containsKey(p)) {
            combatLog.get(p).cancel();
        }

        BukkitTask antiLogOut = new BukkitRunnable() {
            @Override
            public void run() {
                combatLog.remove(p);
                p.sendActionBar(UtilManager.fixColor("&eSkonczyles walke. Mozesz sie wylogowac."));
            }
        }.runTaskLater(Main.getInstance(), 400L);
        combatLog.put(p, antiLogOut);
        p.sendActionBar(UtilManager.fixColor("&cJestes podczas walki! Nie wylogowywuj sie szybciej niz za 20 sekund."));
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player p)) return;
        if (!(e.getEntity() instanceof Player t)) return;

        addToCombat(p);
        addToCombat(t);

        p.resetCooldown();
    }
}
