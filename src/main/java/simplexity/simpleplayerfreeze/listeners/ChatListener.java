package simplexity.simpleplayerfreeze.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;

public class ChatListener implements Listener {
    
    @EventHandler
    public void onChat(AsyncChatEvent chatEvent) {
        if (!(ConfigSettings.chatBehavior == 1 || ConfigSettings.chatBehavior == 2)) return;
        switch (ConfigSettings.chatBehavior) {
            case 1:
                if (Util.isFrozen(chatEvent.getPlayer())) {
                    chatEvent.setCancelled(true);
                    Util.sendErrorMessage(chatEvent.getPlayer(), ConfigSettings.cannotChat);
                }
                break;
            case 2:
                if (!Util.isFrozen(chatEvent.getPlayer())) return;
                Component message = chatEvent.message();
                chatEvent.viewers().clear();
                chatEvent.viewers().add(chatEvent.getPlayer());
                for (Player player : JoinListener.spyList) {
                    Util.formatMutedMessages(player, ConfigSettings.shadowMuteFormat,
                            chatEvent.getPlayer(), message);
                }
                if (ConfigSettings.consoleSeesMutedMessages) {
                    Util.formatMutedMessages(SimplePlayerFreeze.server.getConsoleSender(),
                            ConfigSettings.shadowMuteFormat, chatEvent.getPlayer(), message);
                }
                break;
        }
        
    }
    
}
