package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class CraftingListener implements Listener {
    
    @EventHandler
    public void onCraft(CraftItemEvent craftEvent) {
        if (!ConfigSettings.preventCrafting) return;
        if (Util.isFrozen((Player) craftEvent.getWhoClicked())) {
            craftEvent.setCancelled(true);
        }
    }
    
}
