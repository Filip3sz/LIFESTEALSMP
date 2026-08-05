package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import pl.filipesz.lifestealsmp.structs.UserFile;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ALL")
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        e.setJoinMessage("");

        if (!UserFile.get().contains("users." + uuid)) {
            UserFile.get().set("users." + uuid + ".getHearts", 2.0);
            UserFile.get().set("users." + uuid + ".cash", 0.00);
            UserFile.get().set("users." + uuid + ".kills", 0);
            UserFile.get().set("users." + uuid + ".deaths", 0);
            UserFile.save();
            ItemStack sPickaxe = new ItemStack(Material.STONE_PICKAXE, 1);
            ItemStack pork = new ItemStack(Material.COOKED_PORKCHOP, 64);
            ItemStack water = new ItemStack(Material.WATER_BUCKET, 1);
            p.getInventory().setItem(0, sPickaxe);
            p.getInventory().setItem(1, pork);
            p.getInventory().setItem(2, water);
            int x = ThreadLocalRandom.current().nextInt(0, 2000);
            int z = ThreadLocalRandom.current().nextInt(0, 2000);
            World world = Bukkit.getWorld("world");
            Location randomLocation = new Location(p.getWorld(), x, world.getHighestBlockYAt(x, z), z);
            p.teleport(randomLocation);
        }
        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(UserFile.getHearts(uuid));
        p.sendTitle("&8* &3Filipesz &8*", "&7Polaczono z trybem &3LIFESTEALSMP&7...");
    }
}
