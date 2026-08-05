package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pl.filipesz.lifestealsmp.gui.ShopGUI;
import pl.filipesz.lifestealsmp.structs.UserFile;
import pl.filipesz.lifestealsmp.utils.UtilManager;

import java.util.UUID;

@SuppressWarnings("ALL")
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (ShopGUI.guiName.equalsIgnoreCase(e.getView().getTitle())) {
            e.setCancelled(true);
            //
            if (e.getSlot() == 0) {
                UUID uuid = p.getUniqueId();
                double cash = UserFile.getCash(uuid);
                if (cash >= 1000.00) {
                    UserFile.removeCash(uuid, 1000.00);
                    ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                    p.getInventory().addItem(totem);
                    p.closeInventory();
                    UtilManager.sendMessage(p, "&8[&e&l!&8] &8» &eZakupiłeś Totem!");
                } else {
                    p.closeInventory();
                    UtilManager.sendMessage(p, "&8[&c&l!&8] &8» &cNie posiadasz pieniedzy o ilosci 1000.00!");
                }
            }
        }
    }
}
