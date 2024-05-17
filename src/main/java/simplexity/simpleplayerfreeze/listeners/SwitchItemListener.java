package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;

public class SwitchItemListener implements Listener {

    @EventHandler
    public void onSwitchItem(PlayerItemHeldEvent itemHeldEvent) {
        if (!ConfigHandler.getInstance().shouldPreventHotbarSwitch()) return;
        if (Util.isFrozen(itemHeldEvent.getPlayer())) {
            itemHeldEvent.setCancelled(true);
        }
    }

}
