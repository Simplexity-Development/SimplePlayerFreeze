package simplexity.simpleplayerfreeze.commands;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.Util;

import java.util.List;

public class UnfreezePlayer extends Command {
    
    public UnfreezePlayer(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }
    
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.unfreezePermission)) return false;
        if (strings.length == 0) {
            sender.sendRichMessage(Util.noPlayer);
            return false;
        }
        Player player;
        player = Bukkit.getPlayer(strings[0]);
        if (player == null) {
            sender.sendRichMessage(Util.noPlayer);
            return false;
        } else {
            Util.setUnfrozen(player);
            sender.sendMessage(Util.miniMessage.deserialize(Util.unfreezeMessage,
                    Placeholder.component("name", player.displayName())));
            player.sendRichMessage(Util.haveBeenUnfrozen);
        }
        return false;
    }
}
