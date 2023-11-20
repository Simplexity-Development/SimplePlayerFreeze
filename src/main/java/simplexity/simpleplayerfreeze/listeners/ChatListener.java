package simplexity.simpleplayerfreeze.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent chatEvent) {
        if (!ConfigSettings.preventChat) return;
        if (Util.isFrozen(chatEvent.getPlayer())) {
            chatEvent.setCancelled(true);
            chatEvent.getPlayer().sendMessage("You are frozen! You cannot chat!");
        }
    }

}
