package simplexity.simpleplayerfreeze.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ChatBehavior;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent chatEvent) {
        String shadowMuteFormat = LocaleHandler.getInstance().getShadowMuteFormat();
        if (ConfigHandler.getInstance().getChatBehavior() == ChatBehavior.NO_CHANGE) return;
        switch (ConfigHandler.getInstance().getChatBehavior()) {
            case ChatBehavior.FULL_MUTE:
                if (Util.isFrozen(chatEvent.getPlayer())) {
                    chatEvent.setCancelled(true);
                    Util.sendErrorMessage(chatEvent.getPlayer(), LocaleHandler.getInstance().getCannotChat());
                }
                break;
            case ChatBehavior.SHADOW_MUTE:
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
