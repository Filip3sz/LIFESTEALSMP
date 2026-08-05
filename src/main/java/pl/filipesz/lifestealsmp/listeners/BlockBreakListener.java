package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import pl.filipesz.lifestealsmp.Main;
import pl.filipesz.lifestealsmp.structs.UserFile;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("ALL")
public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        Block b = e.getBlock();
        Location loc = b.getLocation();
        double rand = Math.random();

        if (b.getType() == Material.EMERALD_ORE) {
            ItemStack breakItem = p.getItemInHand();
            if (breakItem.getType() == Material.GOLDEN_PICKAXE) {
                ItemStack generator = new ItemStack(Material.EMERALD_ORE);
                ItemMeta generatorMeta = generator.getItemMeta();
                generatorMeta.setDisplayName(UtilManager.fixColor("&3&lGENERATOR KAMIENIA"));
                generatorMeta.addEnchant(Enchantment.UNBREAKING, 10, true);
                ArrayList <String> generatorLore = new ArrayList<>();
                generatorLore.add(UtilManager.fixColor("&8» &ePostawienie na ziemi spowoduje generowanie kamienia."));
                generatorLore.add(UtilManager.fixColor("&8» &eZebranie go jest mozliwe tylko zlotym kilofem!"));
                generatorMeta.setLore(generatorLore);
                generator.setItemMeta(generatorMeta);
                p.getInventory().addItem(generator);
                return;
            } else {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        loc.getBlock().setType(Material.AIR);
                        loc.getBlock().setType(Material.EMERALD_ORE);
                    }
                }.runTaskLater(Main.getInstance(), 40L);
            }
        }

        if (b.getType() == Material.STONE || b.getType() == Material.EMERALD_ORE) {
            e.setDropItems(false);
            ItemStack cobble = new ItemStack(Material.COBBLESTONE, 1);
            p.getInventory().addItem(cobble);
            if (rand <= 0.0001) {
                p.giveExp(10);
                UserFile.addHearts(uuid);
                Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(UserFile.getHearts(uuid));
                p.sendActionBar(UtilManager.fixColor("&eZyskales dodatkowe serce!"));
            } else if (rand <= 0.02) {
                p.giveExp(1);
            } else if (rand <= 0.05) {
                UserFile.addCash(uuid, 0.69);
                p.sendActionBar(UtilManager.fixColor("&eWykopales extra pieniadz!"));
            } else if (rand <= 0.2) {
                UserFile.addCash(uuid, 0.10);
            }
        }
    }
}
