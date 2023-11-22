package simplexity.simpleplayerfreeze.listeners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class CommandListener implements Listener {
    
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent commandEvent) {
        if (!ConfigSettings.preventCommands) return;
        if (!Util.isFrozen(commandEvent.getPlayer())) return;
        String command = commandEvent.getMessage().split(" ")[0];
        if (!ConfigSettings.whitelistedCommandList.contains(command)) {
            commandEvent.getPlayer().sendRichMessage(ConfigSettings.cannotUseCommand);
            commandEvent.setCancelled(true);
        }
    }

}
