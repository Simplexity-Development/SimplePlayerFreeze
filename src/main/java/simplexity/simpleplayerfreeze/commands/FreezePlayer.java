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

public class FreezePlayer implements CommandExecutor {
    
    // Freezes the player, or unfreezes the player if they are already frozen.
    
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.freezePermission)) {
            Util.sendErrorMessage(sender, ConfigSettings.noPermission);
            return false;
        }
        if (strings.length == 0) {
            Util.sendErrorMessage(sender, ConfigSettings.noPlayer);
            return false;
        }
        Player player;
        player = Bukkit.getPlayer(strings[0]);
        if (player == null) {
            Util.sendErrorMessage(sender, ConfigSettings.noPermission);
            return false;
        } else if (player.hasPermission(Util.freezeBypassPermission)) {
            Util.sendErrorMessage( sender, ConfigSettings.cannotBeFrozen);
            return false;
        } else if (Util.isFrozen(player) && sender.hasPermission(Util.unfreezePermission)) {
            FreezeFunctionality.setUnfrozen(player);
            Util.sendUserMessageWithPlayer(sender, ConfigSettings.unfreezeMessage, player);
            return true;
        } else {
            FreezeFunctionality.setFrozen(player);
            Util.sendUserMessageWithPlayer(sender, ConfigSettings.freezeMessage, player);
        }
        return true;
    }
    
}
