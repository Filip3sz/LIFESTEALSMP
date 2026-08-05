package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import pl.filipesz.lifestealsmp.structs.UserFile;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("ALL")
public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Player t = e.getEntity().getKiller();

        e.setDeathMessage("");

        if (p != null) {
            UUID pUuid = p.getUniqueId();
            if (t == null) {
                p.getWorld().strikeLightningEffect(p.getLocation());
                UserFile.addDeaths(pUuid);
                UserFile.removeHearts(pUuid);
            } else {
                UUID tUuid = t.getUniqueId();
                p.getWorld().strikeLightningEffect(p.getLocation());
                UserFile.addDeaths(pUuid);
                UserFile.removeHearts(pUuid);
                UserFile.addKills(tUuid);
                UserFile.addHearts(tUuid);
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(UserFile.getHearts(tUuid));
                t.sendTitle("", "&3Zabojstwo!");
                Bukkit.broadcastMessage(UtilManager.fixColor("&8[&c&l!&8] &8» &7" + p.getName() + " &czostal zabity przez &7" + t.getName()));
            }
        }
    }
}
