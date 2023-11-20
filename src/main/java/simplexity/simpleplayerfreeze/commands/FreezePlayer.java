package simplexity.simpleplayerfreeze.commands;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

import java.util.List;

public class FreezePlayer extends Command {
    //
    public FreezePlayer(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }
    
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.freezePermission)) {
            sender.sendRichMessage(ConfigSettings.noPermission);
            return false;
        }
        if (strings.length == 0) {
            sender.sendRichMessage(ConfigSettings.prefix + ConfigSettings.noPlayer);
            return false;
        }
        Player player;
        player = Bukkit.getPlayer(strings[0]);
        if (player == null) {
            sender.sendRichMessage(ConfigSettings.prefix + ConfigSettings.noPlayer);
            return false;
        } else if (player.hasPermission(Util.freezeBypassPermission)) {
            sender.sendRichMessage(ConfigSettings.prefix + ConfigSettings.cannotBeFrozen);
            return false;
        
        } else if (Util.isFrozen(player)) {
            Util.setUnfrozen(player);
            sender.sendMessage(Util.miniMessage.deserialize(ConfigSettings.unfreezeMessage,
                    Placeholder.component("name", player.displayName())));
            player.sendRichMessage(ConfigSettings.haveBeenUnfrozen);
            return true;
        } else {
            Util.setFrozen(player);
            sender.sendMessage(Util.miniMessage.deserialize(ConfigSettings.freezeMessage,
                    Placeholder.component("name", player.displayName())));
            player.sendRichMessage(ConfigSettings.haveBeenFrozen);
        }
        return true;
    }
}
