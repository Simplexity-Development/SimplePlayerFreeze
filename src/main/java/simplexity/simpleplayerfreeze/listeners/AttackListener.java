package simplexity.simpleplayerfreeze.listeners;

import io.papermc.paper.event.player.PrePlayerAttackEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;

public class AttackListener implements Listener {
    @EventHandler
    public void onAttack(PrePlayerAttackEntityEvent attackEvent) {
        if (!ConfigHandler.getInstance().shouldPreventAttack()) return;
        if (Util.isFrozen(attackEvent.getPlayer())) {
            attackEvent.setCancelled(true);
        }
    }
}
