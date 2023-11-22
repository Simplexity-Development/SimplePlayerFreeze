package simplexity.simpleplayerfreeze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;

import java.util.List;

public class ReloadConfig extends Command {
    
    // Reloads the configuration
    
    public ReloadConfig(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }
    
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] strings) {
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
