package simplexity.simpleplayerfreeze.placeholderapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;

public class IsFrozenPlaceholder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "spf";
    }
    
    @Override
    public @NotNull String getAuthor() {
        return SimplePlayerFreeze.simplePlayerFreeze.getDescription().getAuthors().toString();
    }
    
    @Override
    public @NotNull String getVersion() {
        return SimplePlayerFreeze.simplePlayerFreeze.getDescription().getVersion();
    }
    
    @Override
    public String onRequest(OfflinePlayer offlinePlayer, @NotNull String params) {
        if (params.equalsIgnoreCase("frozen")) {
            Player player = offlinePlayer.getPlayer();
            if (player == null) {
                return "Offline";
            }
            if (Util.isFrozen(player)) {
                return ConfigSettings.placeholderAPIFormat;
            }
            if (!Util.isFrozen(player)) {
                return "";
            }
            return "";
        }
        return null;
    }

}
