package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;

public class InventoryInteractListener implements Listener {

    @EventHandler
    public void onCraft(InventoryInteractEvent inventoryInteractEvent) {
        if (!ConfigHandler.getInstance().shouldPreventInventoryInteraction()) return;
        if (Util.isFrozen((Player) inventoryInteractEvent.getWhoClicked())) {
            inventoryInteractEvent.setCancelled(true);
        }
    }

}
