package simplexity.simpleplayerfreeze;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigSettings {
    public static String prefix = "";
    public static String commandErrorMessage = "";
    public static String cannotChat = "";
    public static String cannotUseCommand = "";
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
    public static String shadowMuteFormat = "";
    public static String freezeSpyEnabled = "";
    public static String freezeSpyDisabled = "";
    public static String onlyPlayer = "";
    public static String placeholderAPIFormat = "";
    public static boolean freezePersist;
    public static boolean freezeGlow;
    public static boolean freezeDismount;
    public static boolean freezeFlight;
    public static boolean freezeInvulnerability;
    public static boolean preventMovement;
    public static boolean preventInteract;
    public static boolean preventCrafting;
    public static boolean preventXPPickup;
    public static boolean preventItemPickup;
    public static boolean preventItemDrop;
    public static boolean preventItemUse;
    public static boolean preventHotbarSwitch;
    public static boolean preventInventoryInteraction;
    public static boolean preventCommands;
    public static int chatBehavior;
    public static ArrayList<String> whitelistedCommandList = new ArrayList<>();
    
    public static void reloadConfigSettings(){
        reloadConfigBooleans();
        reloadMessages();
        reloadConfigCommands();
        reloadConfigIntegers();
    }
    private static void reloadMessages() {
        FileConfiguration config = SimplePlayerFreeze.simplePlayerFreeze.getConfig();
        prefix = config.getString("prefix");
        commandErrorMessage = config.getString("command-error-message");
        cannotChat = config.getString("cannot-chat");
        cannotUseCommand = config.getString("cannot-use-command");
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
        shadowMuteFormat = config.getString("shadow-mute-format");
        freezeSpyEnabled = config.getString("freeze-spy-enabled");
        freezeSpyDisabled = config.getString("freeze-spy-disabled");
        onlyPlayer = config.getString("only-player");
        placeholderAPIFormat = config.getString("placeholder-api-format");
    }
    
    private static void reloadConfigBooleans() {
        FileConfiguration config = SimplePlayerFreeze.simplePlayerFreeze.getConfig();
        freezePersist = config.getBoolean("freeze-persist");
        freezeGlow = config.getBoolean("freeze-glow");
        freezeDismount = config.getBoolean("freeze-dismount");
        freezeFlight = config.getBoolean("freeze-flight");
        freezeInvulnerability = config.getBoolean("freeze-invulnerability");
        preventMovement = config.getBoolean("prevent-movement");
        preventInteract = config.getBoolean("prevent-interact");
        preventCrafting = config.getBoolean("prevent-crafting");
        preventXPPickup = config.getBoolean("prevent-xp-pickup");
        preventItemPickup = config.getBoolean("prevent-item-pickup");
        preventItemDrop = config.getBoolean("prevent-item-drop");
        preventItemUse = config.getBoolean("prevent-item-use");
        preventHotbarSwitch = config.getBoolean("prevent-hotbar-switch");
        preventCommands = config.getBoolean("prevent-commands");
        preventInventoryInteraction = config.getBoolean("prevent-inventory-interaction");
    }
    
    private static void reloadConfigCommands() {
        FileConfiguration config = SimplePlayerFreeze.simplePlayerFreeze.getConfig();
        whitelistedCommandList.clear();
        List<String> commandList = config.getStringList("whitelisted-commands");
        whitelistedCommandList.addAll(commandList);
    }
    
    private static void reloadConfigIntegers() {
        FileConfiguration config = SimplePlayerFreeze.simplePlayerFreeze.getConfig();
        chatBehavior = config.getInt("chat-behavior");
        if (!( 0 <= chatBehavior && chatBehavior <= 2)) {
            SimplePlayerFreeze.simplePlayerFreeze.getLogger().warning("Chat behavior value is invalid. Defaulting to 0.");
            chatBehavior = 2;
        }
    }
}
