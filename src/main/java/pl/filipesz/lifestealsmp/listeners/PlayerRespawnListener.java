package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import pl.filipesz.lifestealsmp.structs.UserFile;

import java.util.Objects;
import java.util.UUID;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        ItemStack pork = new ItemStack(Material.COOKED_PORKCHOP, 64);
        p.getInventory().setItem(0, pork);

        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(UserFile.getHearts(uuid));
        p.setHealth(UserFile.getHearts(uuid));
    }
}
