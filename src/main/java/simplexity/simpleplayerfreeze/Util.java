package simplexity.simpleplayerfreeze;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.persistence.PersistentDataType;

public class Util {
    
    public static MiniMessage miniMessage = MiniMessage.miniMessage();
    public static String namespace = "simpleplayerfreeze";
    public static NamespacedKey isFrozenKey = new NamespacedKey(namespace, "isfrozen");
    public static Permission freezePermission = new Permission("spf.freeze");
    public static Permission unfreezePermission = new Permission("spf.unfreeze");
    public static Permission reloadPermission = new Permission("spf.reload");
    
    public static String prefix = "";
    public static String commandErrorMessage = "";
    public static String cannotChat = "";
    public static String freezeMessage = "";
    public static String unfreezeMessage = "";
    public static String reloadMessage = "";
    public static String haveBeenFrozen = "";
    public static String haveBeenUnfrozen = "";
    public static String noPermission = "";
    public static String noPlayer = "";
    public static String loginMessage = "";
    public static String loginNotif = "";
    
    public static void reloadMessages() {
        prefix = SimplePlayerFreeze.getInstance().getConfig().getString("prefix");
        commandErrorMessage = SimplePlayerFreeze.getInstance().getConfig().getString("command-error-message");
        cannotChat = SimplePlayerFreeze.getInstance().getConfig().getString("cannot-chat");
        freezeMessage = SimplePlayerFreeze.getInstance().getConfig().getString("freeze-message");
        unfreezeMessage = SimplePlayerFreeze.getInstance().getConfig().getString("unfreeze-message");
        reloadMessage = SimplePlayerFreeze.getInstance().getConfig().getString("reload-message");
        haveBeenFrozen = SimplePlayerFreeze.getInstance().getConfig().getString("have-been-frozen");
        haveBeenUnfrozen = SimplePlayerFreeze.getInstance().getConfig().getString("have-been-unfrozen");
        noPermission = SimplePlayerFreeze.getInstance().getConfig().getString("no-permission");
        noPlayer = SimplePlayerFreeze.getInstance().getConfig().getString("no-player");
        loginMessage = SimplePlayerFreeze.getInstance().getConfig().getString("login-message");
        loginNotif = SimplePlayerFreeze.getInstance().getConfig().getString("login-notif");
    }
    
    public static void setFrozen(Player player) {
        player.getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, true);
        player.setGlowing(true);
        player.setWalkSpeed(0f);
    }
    
    public static void setUnfrozen(Player player) {
        player.getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, false);
        player.setGlowing(false);
        player.setWalkSpeed(0.2f);
    }
    
}
