package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.Util;

public class MoveListener implements Listener {
    
    @EventHandler
    public void onMove(PlayerMoveEvent moveEvent) {
        if (!ConfigHandler.getInstance().shouldPreventMovement()) return;
        if (Util.isFrozen(moveEvent.getPlayer())) {
            moveEvent.setCancelled(true);
        }
    }
    
}
