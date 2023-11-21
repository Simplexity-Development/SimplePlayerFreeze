package simplexity.simpleplayerfreeze.freeze;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class FreezeFunctionality {
    
    public static void setFrozen(Player player) {
        player.getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, true);
        if (ConfigSettings.freezeFlight) {
            setFlight(player);
        }
        if (ConfigSettings.freezeGlow) {
            player.setGlowing(true);
        }
        if (ConfigSettings.freezeDismount){
            player.eject();
        }
        player.setWalkSpeed(0f);
        player.sendRichMessage(ConfigSettings.haveBeenFrozen);
    }
    
    public static void setUnfrozen(Player player) {
        player.getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, false);
        if (!player.getPersistentDataContainer().getOrDefault(Util.previouslyHadFlyPerms, PersistentDataType.BOOLEAN, false)) {
            player.setAllowFlight(false);
        }
        player.setFlying(false);
        player.setGravity(true);
        player.setGlowing(false);
        player.setWalkSpeed(0.2f);
        player.sendRichMessage(ConfigSettings.haveBeenUnfrozen);
    }
    
    public static void setFlight(Player player) {
        boolean hasAllowFlight = player.getAllowFlight();
        if (hasAllowFlight) {
            player.getPersistentDataContainer().set(Util.previouslyHadFlyPerms, PersistentDataType.BOOLEAN, true);
        }
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setGravity(false);
    }
    
    
}
