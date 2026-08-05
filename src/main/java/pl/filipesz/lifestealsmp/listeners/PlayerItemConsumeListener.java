package pl.filipesz.lifestealsmp.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("ALL")
public class PlayerItemConsumeListener implements Listener {

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        ItemStack hand = p.getItemInHand();

        if (hand.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 140, 0));
        }
    }
}
