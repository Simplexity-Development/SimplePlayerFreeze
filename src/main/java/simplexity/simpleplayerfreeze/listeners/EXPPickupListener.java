package simplexity.simpleplayerfreeze.listeners;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;

public class EXPPickupListener implements Listener {

    @EventHandler
    public void onEXPPickup(PlayerPickupExperienceEvent event) {
        if (!ConfigHandler.getInstance().shouldPreventXPPickup()) return;
        if (Util.isFrozen(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
