package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import pl.filipesz.lifestealsmp.Main;
import pl.filipesz.lifestealsmp.structs.UserFile;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("ALL")
public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        double rand = Math.random();
        ItemStack key = new ItemStack(Material.NAME_TAG);
        ItemMeta keyMeta = key.getItemMeta();
        keyMeta.setDisplayName(UtilManager.fixColor("&3&lMAGICZNY KLUCZ"));
        keyMeta.addEnchant(Enchantment.UNBREAKING, 10, true);
        ArrayList<String> keyLore = new ArrayList<>();
        keyLore.add(UtilManager.fixColor("&8» &eKliknij na skrzynke znajdujaca sie na spawnie by zdobyc nagrode!"));
        keyLore.add(UtilManager.fixColor("&8» &c&lUPEWNIJ SIE ZE MASZ PUSTE MIEJSCE W EQ BO W PRZECIWNYM PRZYPADKU UTRACISZ TRAFIONY PRZEDMIOT!!!"));
        keyMeta.setLore(keyLore);
        key.setItemMeta(keyMeta);

        if (p.getItemInHand() != null && p.getItemInHand().isSimilar(key)) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
                p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                e.setCancelled(true);
                p.getInventory().removeItem(key);
                if (rand <= 0.005) {
                    UserFile.addHearts(uuid);
                    Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(UserFile.getHearts(uuid));
                    p.sendActionBar(UtilManager.fixColor("&eZyskales dodatkowe serce!"));
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales DODATKOWE SERCE!");
                } else if (rand <= 0.05) {
                    ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
                    p.getInventory().addItem(elytra);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales ELYTRE!");
                } else if (rand <= 0.1) {
                    ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                    p.getInventory().addItem(totem);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales TOTEM!");
                } else if (rand <= 0.2) {
                    ItemStack kox = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
                    p.getInventory().addItem(kox);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales KOXA!");
                } else if (rand <= 0.32) {
                    ItemStack refil = new ItemStack(Material.GOLDEN_APPLE, 4);
                    p.getInventory().addItem(refil);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales 4 REFILE!");
                } else if (rand <= 0.42) {
                    ItemStack refil = new ItemStack(Material.GOLDEN_APPLE, 8);
                    p.getInventory().addItem(refil);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales 8 REFILI!");
                } else if (rand <= 0.50) {
                    ItemStack refil = new ItemStack(Material.GOLDEN_APPLE, 12);
                    p.getInventory().addItem(refil);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales 12 REFILI!");
                } else if (rand <= 0.57) {
                    ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
                    ItemMeta helmetMeta = helmet.getItemMeta();
                    helmetMeta.addEnchant(Enchantment.PROTECTION, 4, true);
                    helmetMeta.addEnchant(Enchantment.UNBREAKING, 3, true);
                    helmet.setItemMeta(helmetMeta);
                    p.getInventory().addItem(helmet);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales DIAX HELM!");
                } else if (rand <= 0.62) {
                    ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
                    ItemMeta chestplateMeta = chestplate.getItemMeta();
                    chestplateMeta.addEnchant(Enchantment.PROTECTION, 4, true);
                    chestplateMeta.addEnchant(Enchantment.UNBREAKING, 3, true);
                    chestplate.setItemMeta(chestplateMeta);
                    p.getInventory().addItem(chestplate);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales DIAX KLATE!");
                } else if (rand <= 0.68) {
                    ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
                    ItemMeta leggingsMeta = leggings.getItemMeta();
                    leggingsMeta.addEnchant(Enchantment.PROTECTION, 4, true);
                    leggingsMeta.addEnchant(Enchantment.UNBREAKING, 3, true);
                    leggings.setItemMeta(leggingsMeta);
                    p.getInventory().addItem(leggings);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales DIAX SPODNIE!");
                } else if (rand <= 0.75) {
                    ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
                    ItemMeta bootsMeta = boots.getItemMeta();
                    bootsMeta.addEnchant(Enchantment.PROTECTION, 4, true);
                    bootsMeta.addEnchant(Enchantment.UNBREAKING, 3, true);
                    boots.setItemMeta(bootsMeta);
                    p.getInventory().addItem(boots);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales DIAX BUTY!");
                } else if (rand <= 0.80) {
                    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
                    ItemMeta swordMeta = sword.getItemMeta();
                    swordMeta.addEnchant(Enchantment.SHARPNESS, 5, true);
                    swordMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
                    sword.setItemMeta(swordMeta);
                    p.getInventory().addItem(sword);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales DIAX MIECZ!");
                } else if (rand <= 0.90) {
                    ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                    ItemMeta pickaxeMeta = pickaxe.getItemMeta();
                    pickaxeMeta.addEnchant(Enchantment.EFFICIENCY, 5, true);
                    pickaxeMeta.addEnchant(Enchantment.FORTUNE, 3, true);
                    pickaxeMeta.addEnchant(Enchantment.UNBREAKING, 3, true);
                    pickaxe.setItemMeta(pickaxeMeta);
                    p.getInventory().addItem(pickaxe);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales DIAX KILOF!");
                } else if (rand <= 0.905) {
                    ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                    ItemMeta pickaxeMeta = pickaxe.getItemMeta();
                    pickaxeMeta.setDisplayName(UtilManager.fixColor("&3&lMAGICZNY KILOF"));
                    pickaxeMeta.addEnchant(Enchantment.EFFICIENCY, 10, true);
                    pickaxeMeta.addEnchant(Enchantment.FORTUNE, 5, true);
                    pickaxeMeta.addEnchant(Enchantment.UNBREAKING, 5, true);
                    ArrayList<String> pickaxeLore = new ArrayList<>();
                    pickaxeLore.add(UtilManager.fixColor("&8» &3Najlepszy kilof na serwerze!"));
                    pickaxeMeta.setLore(pickaxeLore);
                    pickaxe.setItemMeta(pickaxeMeta);
                    p.getInventory().addItem(pickaxe);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales MAGICZNY KILOF!");
                } else if (rand <= 0.92) {
                    ItemStack netherite = new ItemStack(Material.NETHERITE_INGOT, 1);
                    p.getInventory().addItem(netherite);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales SZTABKE NETHERITU!");
                } else if (rand <= 0.925) {
                    UserFile.addCash(uuid, 5000);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales 5000.00!");
                } else if (rand <= 0.935) {
                    UserFile.addCash(uuid, 2500);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales 2500.00!");
                } else if (rand <= 0.95) {
                    UserFile.addCash(uuid, 1000);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales 1000.00!");
                } else if (rand <= 0.97) {
                    UserFile.addCash(uuid, 750);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales 750.00!");
                } else if (rand <= 1) {
                    UserFile.addCash(uuid, 500);
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eWylosowales 500.00!");
                }
            }
        }
    }
}
