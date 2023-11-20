package simplexity.simpleplayerfreeze;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;

public class ConfigSettings {
    
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
    public static String cannotBeFrozen = "";
    public static String notFrozen = "";
    public static boolean freezePersist;
    public static boolean freezeGlow;
    public static boolean freezeDismount;
    public static boolean freezeFlight;
    public static boolean preventMovement;
    public static boolean preventInteract;
    public static boolean preventChat;
    public static boolean preventXPPickup;
    public static boolean preventItemPickup;
    public static boolean preventItemDrop;
    public static boolean preventHotbarSwitch;
    public static void reloadMessages() {
        FileConfiguration config = SimplePlayerFreeze.simplePlayerFreeze.getConfig();
        prefix = config.getString("prefix");
        commandErrorMessage = config.getString("command-error-message");
        cannotChat = config.getString("cannot-chat");
        freezeMessage = config.getString("freeze-message");
        unfreezeMessage = config.getString("unfreeze-message");
        reloadMessage = config.getString("reload-message");
        haveBeenFrozen = config.getString("have-been-frozen");
        haveBeenUnfrozen = config.getString("have-been-unfrozen");
        noPermission = config.getString("no-permission");
        noPlayer = config.getString("no-player");
        loginMessage = config.getString("login-message");
        loginNotif = config.getString("login-notif");
        cannotBeFrozen = config.getString("cannot-be-frozen");
        notFrozen = config.getString("not-frozen");
    }
    
    public static void reloadConfigSettings(){
        FileConfiguration config = SimplePlayerFreeze.simplePlayerFreeze.getConfig();
        freezePersist = config.getBoolean("freeze-persist");
        freezeGlow = config.getBoolean("freeze-glow");
        freezeDismount = config.getBoolean("freeze-dismount");
        freezeFlight = config.getBoolean("freeze-flight");
        preventMovement = config.getBoolean("prevent-movement");
        preventInteract = config.getBoolean("prevent-interact");
        preventChat = config.getBoolean("prevent-chat");
        preventXPPickup = config.getBoolean("prevent-xp-pickup");
        preventItemPickup = config.getBoolean("prevent-item-pickup");
        preventItemDrop = config.getBoolean("prevent-item-drop");
        preventHotbarSwitch = config.getBoolean("prevent-hotbar-switch");
        reloadMessages();
    }
}
