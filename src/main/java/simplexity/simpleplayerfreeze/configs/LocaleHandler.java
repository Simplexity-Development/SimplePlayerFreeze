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
    private String prefix, cannotChat, cannotUseCommand, freezeMessage, unfreezeMessage, freezeServerMessage, freezeWorldMessage,
            reloadMessage, haveBeenFrozen, haveBeenUnfrozen, noPermission, noPlayer, loginMessage, loginNotif,
            loginNotifNowUnfrozen, cannotBeFrozen, notFrozen, shadowMuteFormat,
            freezeSpyEnabled, freezeSpyDisabled, onlyPlayer, freezeWorldChange, loginNotifServerFrozen, worldNotFound,
            unfreezeWorldMessage, unfreezeServerMessage;


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
        freezeServerMessage = localeConfig.getString("freeze-server-message", "<aqua>The server has been frozen</aqua>");
        freezeWorldMessage = localeConfig.getString("freeze-world-message", "<aqua>The <green><name></green> world has been frozen<aqua>");
        unfreezeMessage = localeConfig.getString("unfreeze-message", "<green><name></green> <yellow>has been unfrozen</yellow>");
        reloadMessage = localeConfig.getString("reload-message", "<gold>The Simple Player Freeze config has been reloaded</gold>");
        haveBeenFrozen = localeConfig.getString("have-been-frozen", "<red><bold>[NOTICE]</bold></red> <gray>You have been frozen. You cannot move, interact, or chat");
        haveBeenUnfrozen = localeConfig.getString("have-been-unfrozen", "<green><bold>[NOTICE]</bold></green> <gray>You have been unfrozen. You can move, interact, and chat again.");
        noPermission = localeConfig.getString("no-permission", "<red>You do not have permission to run this command!</red>");
        noPlayer = localeConfig.getString("no-player", "<red>You did not provide a valid player, please check your spelling and try again</red>");
        loginMessage = localeConfig.getString("login-message", "<red><bold>[NOTICE]</bold></red> <gray>You were frozen during a previous session. You cannot move, interact, or chat");
        loginNotif = localeConfig.getString("login-notif", "<dark_gray><bold><name></bold></dark_gray> <gray>has logged in. They were frozen during a previous session.");
        loginNotifNowUnfrozen = localeConfig.getString("login-notif-now-unfrozen", "<dark_gray><bold><name></bold></dark_gray> <gray>has logged in. They were frozen during a previous session. They will now be unfrozen.");
        cannotBeFrozen = localeConfig.getString("cannot-be-frozen", "<red>That player cannot be frozen!</red>");
        notFrozen = localeConfig.getString("not-frozen", "<red>That player is not frozen!</red>");
        shadowMuteFormat = localeConfig.getString("shadow-mute-format", "<dark_gray>[Frozen]</dark_gray> <gray><player>: <message></gray>");
        freezeSpyEnabled = localeConfig.getString("freeze-spy-enabled", "<green>Freeze Spy has been toggled <bold><yellow>On</yellow></bold>!</green>");
        freezeSpyDisabled = localeConfig.getString("freeze-spy-disabled", "<grey>Freeze Spy has been toggled <bold><red>Off</red></bold>!</grey>");
        onlyPlayer = localeConfig.getString("only-player", "<red>Sorry! Only a player can run that command</red>");
        freezeWorldChange = localeConfig.getString("freeze-world-change", "<dark_gray><bold><name></bold></dark_gray> <gray>has switched to <world>. Since that world is frozen, they will now be frozen");
        loginNotifServerFrozen = localeConfig.getString("login-notif-server-frozen", "<dark_gray><bold><name></bold></dark_gray> <gray>has logged in. Since the server is currently frozen, they will now be frozen");
        worldNotFound = localeConfig.getString("world-not-found", "<red>The world <yellow><name></yellow> could not be found. Please check your spelling and try again</red>");
        unfreezeWorldMessage = localeConfig.getString("unfreeze-world-message", "<yellow>The <green><world></green> world has been unfrozen</yellow>");
        unfreezeServerMessage = localeConfig.getString("unfreeze-server-message", "<yellow>The server has been frozen</yellow>");
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

    public String getFreezeServerMessage() {
        return freezeServerMessage;
    }

    public String getFreezeWorldMessage() {
        return freezeWorldMessage;
    }

    public String getFreezeWorldChange() {
        return freezeWorldChange;
    }

    public String getLoginNotifServerFrozen() {
        return loginNotifServerFrozen;
    }

    public String getWorldNotFound() {
        return worldNotFound;
    }

    public String getUnfreezeWorldMessage() {
        return unfreezeWorldMessage;
    }

    public String getUnfreezeServerMessage() {
        return unfreezeServerMessage;
    }
}
