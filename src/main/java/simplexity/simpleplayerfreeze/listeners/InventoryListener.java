package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent inventoryOpenEvent) {
        if (!(inventoryOpenEvent.getPlayer() instanceof Player player)) return;
        if (Util.isFrozen(player)) {
            inventoryOpenEvent.setCancelled(true);
            inventoryOpenEvent.getInventory().close();
        }
    }
}
