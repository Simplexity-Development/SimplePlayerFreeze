package simplexity.simpleplayerfreeze.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.freeze.FreezeFunctionality;

public class UnfreezePlayer implements CommandExecutor {
    
    // Unfreezes a frozen player
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.unfreezePermission)) return false;
        if (strings.length == 0) {
            Util.sendErrorMessage(sender, ConfigSettings.noPlayer);
            return false;
        }
        Player player;
        player = Bukkit.getPlayer(strings[0]);
        if (player == null) {
            Util.sendErrorMessage(sender, ConfigSettings.noPlayer);
            return false;
        } else if (!Util.isFrozen(player)) {
            Util.sendErrorMessage(sender, ConfigSettings.notFrozen);
            return true;
        }
        FreezeFunctionality.setUnfrozen(player);
        Util.sendUserMessageWithPlayer(sender, ConfigSettings.unfreezeMessage, player);
        return true;
    }
    
}
