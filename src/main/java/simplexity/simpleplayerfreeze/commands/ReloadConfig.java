package simplexity.simpleplayerfreeze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;

public class ReloadConfig implements CommandExecutor {
    
    // Reloads the configuration
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.reloadPermission)) {
            sender.sendRichMessage(ConfigSettings.noPermission);
            return false;
        }
        SimplePlayerFreeze.simplePlayerFreeze.reloadConfig();
        ConfigSettings.reloadConfigSettings();
        sender.sendRichMessage(ConfigSettings.prefix + ConfigSettings.reloadMessage);
        return true;
    }
}
