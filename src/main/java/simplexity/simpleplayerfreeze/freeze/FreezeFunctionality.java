package simplexity.simpleplayerfreeze.freeze;

import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;

import java.util.Objects;

public class FreezeFunctionality {

    public static void setFrozen(Player player, FreezeType freezeType) {
        if (player.hasPermission(Util.freezeBypassPermission)) return;
        // set frozen and type in PDC
        player.getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, true);
        player.getPersistentDataContainer().set(Util.freezeTypeKey, PersistentDataType.STRING, freezeType.name());
        // Set configured effects
        if (ConfigHandler.getInstance().shouldFreezeFlight()) {
            enableFlight(player);
        }
        if (ConfigHandler.getInstance().shouldFreezeGlow()) {
            player.setGlowing(true);
        }
        if (ConfigHandler.getInstance().shouldFreezeDismount() && player.isInsideVehicle()) {
            player.leaveVehicle();
        }
        if (ConfigHandler.getInstance().shouldFreezeGiveInvulnerability()) {
            player.setInvulnerable(true);
        }
        if (ConfigHandler.getInstance().shouldPreventWalking()) {
            player.setWalkSpeed(0f);
        }
        if (ConfigHandler.getInstance().shouldPreventJumping()) {
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_GRAVITY)).setBaseValue(1);
        }
        Util.sendUserMessage(player, LocaleHandler.getInstance().getHaveBeenFrozen());
    }

    public static void setUnfrozen(Player player) {
        // Disable frozen tag and set freeze type to default
        player.getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, false);
        player.getPersistentDataContainer().set(Util.freezeTypeKey, PersistentDataType.STRING,  FreezeType.NONE.name());
        // revert effects
        removeFlight(player);
        player.setInvulnerable(false);
        player.setGlowing(false);
        player.setWalkSpeed(0.2f);
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_GRAVITY)).setBaseValue(0.08);
        Util.sendUserMessageWithPlayer(player, LocaleHandler.getInstance().getHaveBeenUnfrozen(), player);
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
        if (!player.getPersistentDataContainer().getOrDefault(Util.previouslyHadFlyPerms, PersistentDataType.BOOLEAN, false) && !player.getGameMode().equals(GameMode.CREATIVE)) {
            player.setAllowFlight(false);
        }
        player.getPersistentDataContainer().remove(Util.previouslyHadFlyPerms);
        player.setFlying(false);
        player.setInvulnerable(false);
        player.setGravity(true);
    }


}
