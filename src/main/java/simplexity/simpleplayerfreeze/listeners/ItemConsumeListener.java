package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.Util;

public class ItemConsumeListener implements Listener {
    
    @EventHandler
    public void onItemUse(PlayerItemConsumeEvent consumeEvent) {
        if (!ConfigHandler.getInstance().shouldPreventItemUse()) return;
        if (Util.isFrozen(consumeEvent.getPlayer())) {
            consumeEvent.setCancelled(true);
        }
    }
    
}
