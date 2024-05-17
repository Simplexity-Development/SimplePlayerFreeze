package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;

public class CraftingListener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent craftEvent) {
        if (!ConfigHandler.getInstance().shouldPreventCrafting()) return;
        if (Util.isFrozen((Player) craftEvent.getWhoClicked())) {
            craftEvent.setCancelled(true);
        }
    }

}
