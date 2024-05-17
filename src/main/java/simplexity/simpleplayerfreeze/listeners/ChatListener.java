package simplexity.simpleplayerfreeze.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent chatEvent) {
        String shadowMuteFormat = LocaleHandler.getInstance().getShadowMuteFormat();
        if (!(ConfigHandler.chatBehavior == 1 || ConfigHandler.chatBehavior == 2)) return;
        switch (ConfigHandler.chatBehavior) {
            case 1:
                if (Util.isFrozen(chatEvent.getPlayer())) {
                    chatEvent.setCancelled(true);
                    Util.sendErrorMessage(chatEvent.getPlayer(), LocaleHandler.getInstance().getCannotChat());
                }
                break;
            case 2:
                if (!Util.isFrozen(chatEvent.getPlayer())) return;
                Component message = chatEvent.message();
                chatEvent.viewers().clear();
                chatEvent.viewers().add(chatEvent.getPlayer());
                for (Player player : JoinListener.spyList) {
                    Util.formatMutedMessages(player, shadowMuteFormat,
                            chatEvent.getPlayer(), message);
                }
                if (ConfigHandler.getInstance().shouldConsoleSeesMutedMessages()) {
                    Util.formatMutedMessages(SimplePlayerFreeze.server.getConsoleSender(),
                            shadowMuteFormat, chatEvent.getPlayer(), message);
                }
                break;
        }

    }

}
