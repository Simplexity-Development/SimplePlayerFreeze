package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;

public class DropListener implements Listener {

    @EventHandler
    public void onPickup(PlayerDropItemEvent playerDropItemEvent) {
        if (!ConfigHandler.getInstance().shouldPreventItemDrop()) return;
        if (Util.isFrozen(playerDropItemEvent.getPlayer())) {
            playerDropItemEvent.setCancelled(true);
        }
    }

}
