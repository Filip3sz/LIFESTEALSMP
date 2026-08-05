package pl.filipesz.lifestealsmp.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ItemsGUI {

    public static String guiName = UtilManager.fixColor("&3Itemy.");

    public static void openGui(CommandSender sender) {
        Player p = (Player) sender;
        Inventory inventory = Bukkit.createInventory(p, 27, guiName);
        ItemStack key = new ItemStack(Material.NAME_TAG, 64);
        ItemMeta keyMeta = key.getItemMeta();
        keyMeta.setDisplayName(UtilManager.fixColor("&3&lMAGICZNY KLUCZ"));
        keyMeta.addEnchant(Enchantment.UNBREAKING, 10, true);
        ArrayList<String> keyLore = new ArrayList<>();
        keyLore.add(UtilManager.fixColor("&8» &eKliknij na skrzynke znajdujaca sie na spawnie by zdobyc nagrode!"));
        keyLore.add(UtilManager.fixColor("&8» &c&lUPEWNIJ SIE ZE MASZ PUSTE MIEJSCE W EQ BO W PRZECIWNYM PRZYPADKU UTRACISZ TRAFIONY PRZEDMIOT!!!"));                keyMeta.setLore(keyLore);
        keyMeta.setLore(keyLore);
        key.setItemMeta(keyMeta);
        inventory.setItem(0, key);
        p.openInventory(inventory);
        //
        ItemStack generator = new ItemStack(Material.EMERALD_ORE);
        ItemMeta generatorMeta = generator.getItemMeta();
        generatorMeta.setDisplayName(UtilManager.fixColor("&3&lGENERATOR KAMIENIA"));
        generatorMeta.addEnchant(Enchantment.UNBREAKING, 10, true);
        ArrayList<String> generatorLore = new ArrayList<>();
        generatorLore.add(UtilManager.fixColor("&8» &ePostawienie na ziemi spowoduje generowanie kamienia."));
        generatorLore.add(UtilManager.fixColor("&8» &eZebranie go jest mozliwe tylko zlotym kilofem!"));
        generatorMeta.setLore(generatorLore);
        generator.setItemMeta(generatorMeta);
        inventory.setItem(1, generator);
        p.openInventory(inventory);
    }
}
