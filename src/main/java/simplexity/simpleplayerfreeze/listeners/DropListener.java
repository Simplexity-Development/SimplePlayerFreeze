package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class DropListener implements Listener {
    
    @EventHandler
    public void onPickup(PlayerDropItemEvent playerDropItemEvent) {
        if (!ConfigSettings.preventItemDrop) return;
        if (Util.isFrozen(playerDropItemEvent.getPlayer())) {
            playerDropItemEvent.setCancelled(true);
        }
    }
    
}
