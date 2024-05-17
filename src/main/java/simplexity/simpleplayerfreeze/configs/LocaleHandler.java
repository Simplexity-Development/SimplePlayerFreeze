package simplexity.simpleplayerfreeze.configs;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class LocaleHandler {
    private static LocaleHandler instance;
    private final String fileName = "locale.yml";
    private final File localeFile = new File(SimplePlayerFreeze.getInstance().getDataFolder(), fileName);
    private final FileConfiguration localeConfig = new YamlConfiguration();
    private final Logger logger = SimplePlayerFreeze.getInstance().getLogger();
    private String prefix, cannotChat, cannotUseCommand, freezeMessage, unfreezeMessage,
            reloadMessage, haveBeenFrozen, haveBeenUnfrozen, noPermission, noPlayer, loginMessage, loginNotif,
            loginNotifNowUnfrozen, loginNotifConsole, loginNotifConsoleUnfreezing, cannotBeFrozen, notFrozen, shadowMuteFormat,
            freezeSpyEnabled, freezeSpyDisabled, onlyPlayer;


    private LocaleHandler() {
        if (!localeFile.exists()) {
            SimplePlayerFreeze.getInstance().saveResource(fileName, false);
        }
    }

    public static LocaleHandler getInstance() {
        if (instance == null) instance = new LocaleHandler();
        return instance;
    }

    public FileConfiguration getLocaleConfig() {
        return localeConfig;
    }

    public void loadLocale() {
        try {
            localeConfig.load(localeFile);
        } catch (IOException | InvalidConfigurationException e) {
            logger.severe("Issue loading locale.yml");
            e.printStackTrace();
        }
        prefix = localeConfig.getString("prefix", "<green><bold>[</bold><yellow>SPF</yellow><bold>]</bold></green> ");
        cannotChat = localeConfig.getString("cannot-chat", "You are frozen and cannot chat");
        cannotUseCommand = localeConfig.getString("cannot-use-command", "You are frozen and cannot use that command");
        freezeMessage = localeConfig.getString("freeze-message", "<green><name></green> <aqua>has been frozen</aqua>");
        unfreezeMessage = localeConfig.getString("unfreeze-message", "<green><name></green> <yellow>has been unfrozen</yellow>");
        reloadMessage = localeConfig.getString("reload-message", "<gold>The Simple Player Freeze config has been reloaded</gold>");
        haveBeenFrozen = localeConfig.getString("have-been-frozen", "<red><bold>[NOTICE]</bold></red> <gray>You have been frozen. You cannot move, interact, or chat");
        haveBeenUnfrozen = localeConfig.getString("have-been-unfrozen", "<green><bold>[NOTICE]</bold></green> <gray>You have been unfrozen. You can move, interact, and chat again.");
        noPermission = localeConfig.getString("no-permission", "<red>You do not have permission to run this command!</red>");
        noPlayer = localeConfig.getString("no-player", "<red>You did not provide a valid player, please check your spelling and try again</red>");
        loginMessage = localeConfig.getString("login-message", "<red><bold>[NOTICE]</bold></red> <gray>You were frozen during a previous session. You cannot move, interact, or chat");
        loginNotif = localeConfig.getString("login-notif", "<dark_gray><bold><name></bold></dark_gray> <gray>has logged in. They were frozen during a previous session.");
        loginNotifNowUnfrozen = localeConfig.getString("login-notif-now-unfrozen", "<dark_gray><bold><name></bold></dark_gray> <gray>has logged in. They were frozen during a previous session. They will now be unfrozen.");
        loginNotifConsole = localeConfig.getString("login-notif-console", "[Simple Freeze] <name> logged in while frozen");
        loginNotifConsoleUnfreezing = localeConfig.getString("login-notif-console-unfreezing", "[Simple Freeze] <name> logged in while frozen- unfreezing.");
        cannotBeFrozen = localeConfig.getString("cannot-be-frozen", "<red>That player cannot be frozen!</red>");
        notFrozen = localeConfig.getString("not-frozen", "<red>That player is not frozen!</red>");
        shadowMuteFormat = localeConfig.getString("shadow-mute-format", "<dark_gray>[Frozen]</dark_gray> <gray><player>: <message></gray>");
        freezeSpyEnabled = localeConfig.getString("freeze-spy-enabled", "<green>Freeze Spy has been toggled <bold><yellow>On</yellow></bold>!</green>");
        freezeSpyDisabled = localeConfig.getString("freeze-spy-disabled", "<grey>Freeze Spy has been toggled <bold><red>Off</red></bold>!</grey>");
        onlyPlayer = localeConfig.getString("only-player", "<red>Sorry! Only a player can run that command</red>");
    }

    public String getCannotChat() {
        return cannotChat;
    }

    public String getCannotUseCommand() {
        return cannotUseCommand;
    }

    public String getFreezeMessage() {
        return freezeMessage;
    }

    public String getUnfreezeMessage() {
        return unfreezeMessage;
    }

    public String getReloadMessage() {
        return reloadMessage;
    }

    public String getHaveBeenFrozen() {
        return haveBeenFrozen;
    }

    public String getHaveBeenUnfrozen() {
        return haveBeenUnfrozen;
    }

    public String getNoPermission() {
        return noPermission;
    }

    public String getNoPlayer() {
        return noPlayer;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public String getLoginNotif() {
        return loginNotif;
    }

    public String getLoginNotifNowUnfrozen() {
        return loginNotifNowUnfrozen;
    }

    public String getLoginNotifConsole() {
        return loginNotifConsole;
    }

    public String getLoginNotifConsoleUnfreezing() {
        return loginNotifConsoleUnfreezing;
    }

    public String getCannotBeFrozen() {
        return cannotBeFrozen;
    }

    public String getNotFrozen() {
        return notFrozen;
    }

    public String getShadowMuteFormat() {
        return shadowMuteFormat;
    }

    public String getFreezeSpyEnabled() {
        return freezeSpyEnabled;
    }

    public String getFreezeSpyDisabled() {
        return freezeSpyDisabled;
    }

    public String getOnlyPlayer() {
        return onlyPlayer;
    }

    public String getPrefix() {
        return prefix;
    }
}
