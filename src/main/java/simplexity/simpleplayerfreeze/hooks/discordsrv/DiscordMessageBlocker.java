package simplexity.simpleplayerfreeze.hooks.discordsrv;

import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.GameChatMessagePreProcessEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ChatBehavior;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;

public class DiscordMessageBlocker {

   public DiscordMessageBlocker(Plugin plugin){
    }

    @Subscribe
    public void onGameChatMessage(GameChatMessagePreProcessEvent preMessageEvent) {
        Player player = preMessageEvent.getPlayer();
        if (ConfigHandler.getInstance().getChatBehavior() != ChatBehavior.SHADOW_MUTE) return;
        if (!ConfigHandler.getInstance().shouldHideFromDiscordSrv()) return;
        if (!Util.isFrozen(player)) return;
        preMessageEvent.setCancelled(true);
    }
}
