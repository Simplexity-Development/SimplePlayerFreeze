package simplexity.simpleplayerfreeze.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
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
                    chatEvent.getPlayer().sendRichMessage(ConfigSettings.cannotChat);
                }
                break;
            case 2:
                if (!Util.isFrozen(chatEvent.getPlayer())) return;
                Component message = chatEvent.message();
                chatEvent.viewers().clear();
                chatEvent.viewers().add(chatEvent.getPlayer());
                for (Player player : JoinListener.spyList) {
                    player.sendMessage(Util.miniMessage.deserialize(
                            ConfigSettings.shadowMuteFormat,
                            Placeholder.component("player", chatEvent.getPlayer().displayName()),
                            Placeholder.component("message", message)));
                }
                SimplePlayerFreeze.server.getConsoleSender().sendMessage(Util.miniMessage.deserialize(
                        ConfigSettings.shadowMuteFormat,
                        Placeholder.component("player", chatEvent.getPlayer().displayName()),
                        Placeholder.component("message", message)));
                break;
        }
        
    }
    
}
