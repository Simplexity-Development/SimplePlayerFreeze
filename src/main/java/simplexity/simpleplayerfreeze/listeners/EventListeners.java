package simplexity.simpleplayerfreeze.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataType;
import simplexity.simpleplayerfreeze.Util;

public class EventListeners implements Listener {
    
    @EventHandler
    public void onMove(PlayerMoveEvent moveEvent) {
        if (isFrozen(moveEvent.getPlayer())) {
            moveEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onInteract(PlayerInteractEvent interactEvent) {
        if (isFrozen(interactEvent.getPlayer())) {
            interactEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onChat(AsyncChatEvent chatEvent) {
        if (isFrozen(chatEvent.getPlayer())) {
            chatEvent.setCancelled(true);
            chatEvent.getPlayer().sendMessage("You are frozen! You cannot chat!");
        }
    }
    
    @EventHandler
    public void onSwitchItem(PlayerItemHeldEvent itemHeldEvent) {
        if (isFrozen(itemHeldEvent.getPlayer())) {
            itemHeldEvent.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        if (!joinEvent.getPlayer().isOp()) {
            joinEvent.getPlayer().getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, Boolean.TRUE);
            Util.setFrozen(joinEvent.getPlayer());
            joinEvent.getPlayer().sendRichMessage(Util.loginMessage);
        }
    }
    
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent inventoryOpenEvent) {
        if (!(inventoryOpenEvent.getPlayer() instanceof Player player)) return;
        if (isFrozen(player)) {
            inventoryOpenEvent.setCancelled(true);
        }
    }
    
    private boolean isFrozen(Player player) {
        return player.getPersistentDataContainer().getOrDefault(Util.isFrozenKey, PersistentDataType.BOOLEAN, Boolean.FALSE);
    }
    
    
}
