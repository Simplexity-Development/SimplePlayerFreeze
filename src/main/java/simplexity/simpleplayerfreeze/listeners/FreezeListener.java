package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import simplexity.simpleplayerfreeze.events.PlayerFreezeEvent;
import simplexity.simpleplayerfreeze.freeze.FreezeFunctionality;

public class FreezeListener implements Listener {
    @EventHandler
    public void onPlayerFreeze(PlayerFreezeEvent freezeEvent) {
        Player player = freezeEvent.getPlayer();
        if (freezeEvent.setFrozen()) {
            FreezeFunctionality.setFrozen(player);
        } else {
            FreezeFunctionality.setUnfrozen(player);
        }
    }


}
