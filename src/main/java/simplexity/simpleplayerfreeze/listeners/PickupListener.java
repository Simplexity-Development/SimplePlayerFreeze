package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.Util;

public class PickupListener implements Listener {
    
    @EventHandler
    public void onPickup(EntityPickupItemEvent pickupEvent) {
        if (!ConfigHandler.getInstance().shouldPreventItemPickup()) return;
        if (!(pickupEvent.getEntity() instanceof Player player)) return;
        if (Util.isFrozen(player)) {
            pickupEvent.setCancelled(true);
        }
    }
    
}
