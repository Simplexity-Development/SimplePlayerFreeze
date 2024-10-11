package simplexity.simpleplayerfreeze.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;
import simplexity.simpleplayerfreeze.events.PlayerFreezeEvent;
import simplexity.simpleplayerfreeze.freeze.FreezeType;

public class FreezePlayer implements CommandExecutor {

    // Freezes the player, or unfreezes the player if they are already frozen.

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.freezePermission)) {
            Util.sendErrorMessage(sender, LocaleHandler.getInstance().getNoPermission());
            return false;
        }
        if (strings.length == 0) {
            Util.sendErrorMessage(sender, LocaleHandler.getInstance().getNoPlayer());
            return false;
        }
        Player player;
        player = Bukkit.getPlayer(strings[0]);
        if (player == null) {
            Util.sendErrorMessage(sender, LocaleHandler.getInstance().getNoPermission());
            return false;
        }
        if (player.hasPermission(Util.freezeBypassPermission)) {
            Util.sendErrorMessage(sender, LocaleHandler.getInstance().getCannotBeFrozen());
            return false;
        }
        if (Util.isFrozen(player)) {
            SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(player, false, FreezeType.NONE));
            Util.sendUserMessageWithPlayer(sender, LocaleHandler.getInstance().getUnfreezeMessage(), player);
        } else {
            SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(player, true, FreezeType.INDIVIDUAL));
            Util.sendUserMessageWithPlayer(sender, LocaleHandler.getInstance().getFreezeMessage(), player);
        }
        return true;
    }
}

