package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;

public class InventoryOpenListener implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent openEvent) {

        if (!ConfigHandler.getInstance().shouldPreventInventoryOpen()) return;
        if (Util.isFrozen((Player) openEvent.getPlayer())) {
            openEvent.setCancelled(true);
        }
    }
}
