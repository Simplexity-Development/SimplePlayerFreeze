package simplexity.simpleplayerfreeze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;

import java.util.List;

public class ReloadConfig extends Command {
    
    
    public ReloadConfig(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }
    
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.reloadPermission)) {
            sender.sendRichMessage(Util.noPermission);
            return false;
        }
        SimplePlayerFreeze.getInstance().reloadConfig();
        Util.reloadMessages();
        sender.sendRichMessage(Util.reloadMessage);
        return true;
    }
}
