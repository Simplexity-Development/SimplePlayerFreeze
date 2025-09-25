package simplexity.simpleplayerfreeze.hooks.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;

public class IsFrozenPlaceholder extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "spf";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Simplexity";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, @NotNull String params) {
        if (params.equalsIgnoreCase("frozenprefix")) {
            Player player = offlinePlayer.getPlayer();
            if (player == null) {
                return "Offline";
            }
            if (Util.isFrozen(player)) {
                return ConfigHandler.getInstance().getPlaceholderAPIFormat();
            }
            if (!Util.isFrozen(player)) {
                return "";
            }
            return "";
        }
        if (params.equalsIgnoreCase("frozenbool")) {
            Player player = offlinePlayer.getPlayer();
            if (player == null) {
                return "Offline";
            }
            if (Util.isFrozen(player)) {
                return "true";
            }
            if (!Util.isFrozen(player)) {
                return "false";
            }
        }
        return "";
    }

}
