package simplexity.simpleplayerfreeze.listeners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LeaveListener implements Listener {
    
    @EventHandler
    public void onLeave(org.bukkit.event.player.PlayerQuitEvent event) {
        JoinListener.notifyList.remove(event.getPlayer());
    }
    
}
