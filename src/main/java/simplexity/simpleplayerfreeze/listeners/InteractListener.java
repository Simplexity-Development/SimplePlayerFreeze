package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class InteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent interactEvent) {
        if (!ConfigSettings.preventInteract) return;
        if (Util.isFrozen(interactEvent.getPlayer())) {
            interactEvent.setCancelled(true);
        }
    }

}
