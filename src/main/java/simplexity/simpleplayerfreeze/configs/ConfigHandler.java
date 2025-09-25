package simplexity.simpleplayerfreeze.configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;

import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {

    private static ConfigHandler instance;
    private String placeholderAPIFormat;
    private boolean freezePersist, freezeGlow, freezeDismount, freezeFlight, freezeInvulnerability, preventMovement,
            preventInteract, preventCrafting, preventXPPickup, preventItemPickup, preventItemDrop, preventItemUse,
            preventHotbarSwitch, preventInventoryInteraction, preventInventoryOpen, preventWalking, preventCommands, preventAttack, consoleSeesMutedMessages,
            consoleNotify, preventJumping, freezeNewLogins, freezeWorldChange, hideFromDiscordSrv;
    private ChatBehavior chatBehavior;
    private final ArrayList<String> whitelistedCommandList = new ArrayList<>();

    private ConfigHandler() {
    }

    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
    }

    public void reloadConfigSettings() {
        SimplePlayerFreeze.getInstance().reloadConfig();
        LocaleHandler.getInstance().loadLocale();
        reloadConfigBooleans();
        reloadMessages();
        reloadConfigCommands();
        reloadConfigIntegers();
    }

    private void reloadMessages() {
        FileConfiguration config = SimplePlayerFreeze.getInstance().getConfig();
        placeholderAPIFormat = config.getString("placeholder-api-format");

    }

    private void reloadConfigBooleans() {
        FileConfiguration config = SimplePlayerFreeze.getInstance().getConfig();
        freezePersist = config.getBoolean("freeze-persist", true);
        freezeGlow = config.getBoolean("freeze-glow", true);
        freezeDismount = config.getBoolean("freeze-dismount", true);
        freezeFlight = config.getBoolean("freeze-flight", false);
        freezeInvulnerability = config.getBoolean("freeze-invulnerability", true);
        preventWalking = config.getBoolean("prevent-walking", true);
        preventMovement = config.getBoolean("prevent-movement", true);
        preventJumping = config.getBoolean("prevent-jumping", true);
        preventInteract = config.getBoolean("prevent-interact", true);
        preventCrafting = config.getBoolean("prevent-crafting", true);
        preventXPPickup = config.getBoolean("prevent-xp-pickup", true);
        preventItemPickup = config.getBoolean("prevent-item-pickup", true);
        preventItemDrop = config.getBoolean("prevent-item-drop", true);
        preventItemUse = config.getBoolean("prevent-item-use", true);
        preventHotbarSwitch = config.getBoolean("prevent-hotbar-switch", true);
        preventCommands = config.getBoolean("prevent-commands", true);
        preventAttack = config.getBoolean("prevent-attack", true);
        preventInventoryInteraction = config.getBoolean("prevent-inventory-interaction", true);
        preventInventoryOpen = config.getBoolean("prevent-inventory-open", true);
        consoleSeesMutedMessages = config.getBoolean("console-sees-muted-messages", true);
        consoleNotify = config.getBoolean("console-notify", true);
        freezeNewLogins = config.getBoolean("freeze-new-logins", true);
        freezeWorldChange = config.getBoolean("freeze-world-change", true);
        hideFromDiscordSrv = config.getBoolean("hide-from-discord", true);
    }

    private void reloadConfigCommands() {
        FileConfiguration config = SimplePlayerFreeze.getInstance().getConfig();
        whitelistedCommandList.clear();
        List<String> commandList = config.getStringList("whitelisted-commands");
        whitelistedCommandList.addAll(commandList);
    }

    private void reloadConfigIntegers() {
        FileConfiguration config = SimplePlayerFreeze.getInstance().getConfig();
        int chatBehaviorInt = config.getInt("chat-behavior");
        if (ChatBehavior.getBehaviorFromInt(chatBehaviorInt) == null) {
            SimplePlayerFreeze.getInstance().getLogger().warning(
                    "Chat behavior value is invalid. Defaulting to NO_CHANGE.");
            chatBehavior = ChatBehavior.NO_CHANGE;
        } else {
            chatBehavior = ChatBehavior.getBehaviorFromInt(chatBehaviorInt);
        }
    }

    @NotNull
    public ChatBehavior getChatBehavior(){
        return chatBehavior;
    }

    @NotNull
    public ArrayList<String> getWhitelistedCommandList() {
        return whitelistedCommandList;
    }

    public boolean shouldFreezePersist() {
        return freezePersist;
    }

    public boolean shouldFreezeGlow() {
        return freezeGlow;
    }

    public boolean shouldFreezeDismount() {
        return freezeDismount;
    }

    public boolean shouldFreezeFlight() {
        return freezeFlight;
    }

    public boolean shouldFreezeGiveInvulnerability() {
        return freezeInvulnerability;
    }

    public boolean shouldPreventMovement() {
        return preventMovement;
    }

    public boolean shouldPreventInteract() {
        return preventInteract;
    }

    public boolean shouldPreventCrafting() {
        return preventCrafting;
    }

    public boolean shouldPreventXPPickup() {
        return preventXPPickup;
    }

    public boolean shouldPreventItemPickup() {
        return preventItemPickup;
    }

    public boolean shouldPreventItemDrop() {
        return preventItemDrop;
    }

    public boolean shouldPreventItemUse() {
        return preventItemUse;
    }

    public boolean shouldPreventHotbarSwitch() {
        return preventHotbarSwitch;
    }

    public boolean shouldPreventInventoryInteraction() {
        return preventInventoryInteraction;
    }

    public boolean shouldPreventWalking() {
        return preventWalking;
    }

    public boolean shouldPreventCommands() {
        return preventCommands;
    }

    public boolean shouldConsoleSeesMutedMessages() {
        return consoleSeesMutedMessages;
    }

    public boolean shouldConsoleBeNotified() {
        return consoleNotify;
    }

    public String getPlaceholderAPIFormat() {
        return placeholderAPIFormat;
    }

    public boolean shouldPreventJumping() {
        return preventJumping;
    }

    public boolean shouldPreventAttack() {
        return preventAttack;
    }

    public boolean shouldPreventInventoryOpen() {
        return preventInventoryOpen;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean shouldFreezeNewLogins() {
        return freezeNewLogins;
    }

    public boolean shouldFreezeWorldChange() {
        return freezeWorldChange;
    }

    public boolean shouldHideFromDiscordSrv() {
        return hideFromDiscordSrv;
    }
}
