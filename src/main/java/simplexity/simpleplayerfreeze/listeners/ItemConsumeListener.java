package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class ItemConsumeListener implements Listener {
    
    @EventHandler
    public void onItemUse(PlayerItemConsumeEvent consumeEvent) {
        if (!ConfigSettings.preventItemUse) return;
        if (Util.isFrozen(consumeEvent.getPlayer())) {
            consumeEvent.setCancelled(true);
        }
    }
    
}
