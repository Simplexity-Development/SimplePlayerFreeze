package simplexity.simpleplayerfreeze.freeze;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class FreezeFunctionality {
    
    public static void setFrozen(Player player) {
        player.getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, true);
        if (ConfigSettings.freezeFlight) {
            enableFlight(player);
        }
        if (ConfigSettings.freezeGlow) {
            player.setGlowing(true);
        }
        if (ConfigSettings.freezeDismount && player.isInsideVehicle()) {
            player.leaveVehicle();
        }
        if (ConfigSettings.freezeInvulnerability) {
            player.setInvulnerable(true);
        }
        player.setWalkSpeed(0f);
        player.sendRichMessage(ConfigSettings.haveBeenFrozen);
    }
    
    public static void setUnfrozen(Player player) {
        player.getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, false);
        removeFlight(player);
        player.setInvulnerable(false);
        player.setGlowing(false);
        player.setWalkSpeed(0.2f);
        player.sendRichMessage(ConfigSettings.haveBeenUnfrozen);
    }
    
    public static void enableFlight(Player player) {
        boolean hasAllowFlight = player.getAllowFlight();
        if (hasAllowFlight) {
            player.getPersistentDataContainer().set(Util.previouslyHadFlyPerms, PersistentDataType.BOOLEAN, true);
        }
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setGravity(false);
    }
    
    public static void removeFlight(Player player) {
        if (!player.getPersistentDataContainer().getOrDefault(Util.previouslyHadFlyPerms, PersistentDataType.BOOLEAN, false)) {
            player.setAllowFlight(false);
        }
        player.getPersistentDataContainer().remove(Util.previouslyHadFlyPerms);
        player.setFlying(false);
        player.setInvulnerable(false);
        player.setGravity(true);
    }
    
    
}
