package simplexity.simpleplayerfreeze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;

public class ReloadConfig implements CommandExecutor {
    
    // Reloads the configuration
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.reloadPermission)) {
            sender.sendRichMessage(LocaleHandler.getInstance().getNoPermission());
            return false;
        }
        ConfigHandler.getInstance().reloadConfigSettings();
        Util.sendUserMessage(sender, LocaleHandler.getInstance().getReloadMessage());
        return true;
    }
}
