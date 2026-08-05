package pl.filipesz.lifestealsmp.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.filipesz.lifestealsmp.utils.UtilManager;

@SuppressWarnings("ALL")
public class ShopGUI {

    public static String guiName = UtilManager.fixColor("&3Sklep.");

    public static void openGui(CommandSender sender) {
        Player p = (Player) sender;
        Inventory inventory = Bukkit.createInventory(p, 27, guiName);
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta totemMeta = totem.getItemMeta();
        totemMeta.setDisplayName(UtilManager.fixColor("&3Totem &7(&31000.00&7)"));
        totem.setItemMeta(totemMeta);
        inventory.setItem(0, totem);
        p.openInventory(inventory);
    }
}
