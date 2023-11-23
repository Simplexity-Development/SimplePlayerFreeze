package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class SwitchItemListener implements Listener {
    
    @EventHandler
    public void onSwitchItem(PlayerItemHeldEvent itemHeldEvent) {
        if (!ConfigSettings.preventHotbarSwitch) return;
        if (Util.isFrozen(itemHeldEvent.getPlayer())) {
            itemHeldEvent.setCancelled(true);
        }
    }
    
}
