package simplexity.simpleplayerfreeze.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.listeners.JoinListener;

public class FreezeSpy implements CommandExecutor {
    
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!sender.hasPermission(Util.freezeChatSpy)) {
            Util.sendErrorMessage( sender, ConfigSettings.noPermission);
            return false;
        }
        if (!(sender instanceof Player player)) {
            Util.sendErrorMessage( sender, ConfigSettings.onlyPlayer);
            return false;
        }
        toggleSpy(player);
        return true;
    }
    
    private void toggleSpy(Player player) {
        if (!player.getPersistentDataContainer().getOrDefault(Util.freezeSpyDisabled, PersistentDataType.BOOLEAN, false)) {
            player.getPersistentDataContainer().set(Util.freezeSpyDisabled, PersistentDataType.BOOLEAN, true);
            Util.sendUserMessage(player, ConfigSettings.freezeSpyDisabled);
            JoinListener.spyList.remove(player);
        } else {
            player.getPersistentDataContainer().set(Util.freezeSpyDisabled, PersistentDataType.BOOLEAN, false);
            Util.sendUserMessage(player, ConfigSettings.freezeSpyEnabled);
            JoinListener.spyList.add(player);
        }
    }
}
