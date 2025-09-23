package simplexity.simpleplayerfreeze.listeners;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;

public class CommandListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent commandEvent) {
        if (!ConfigHandler.getInstance().shouldPreventCommands()) return;
        if (!Util.isFrozen(commandEvent.getPlayer())) return;
        String command = commandEvent.getMessage().split(" ")[0];
        if (!ConfigHandler.getInstance().getWhitelistedCommandList().contains(command)) {
            Util.sendErrorMessage(commandEvent.getPlayer(), LocaleHandler.getInstance().getCannotUseCommand());
            commandEvent.setCancelled(true);
        }
    }

}
