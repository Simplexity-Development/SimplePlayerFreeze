package simplexity.simpleplayerfreeze;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.persistence.PersistentDataType;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;

public class Util {
    
    public static MiniMessage miniMessage = MiniMessage.miniMessage();
    public static String namespace = "simpleplayerfreeze";
    public static NamespacedKey isFrozenKey = new NamespacedKey(namespace, "isfrozen");
    public static NamespacedKey previouslyHadFlyPerms = new NamespacedKey(namespace, "previousflyperms");
    public static NamespacedKey freezeSpyDisabled = new NamespacedKey(namespace, "freezespy");
    public static Permission freezePermission = new Permission("spf.freeze");
    public static Permission unfreezePermission = new Permission("spf.unfreeze");
    public static Permission freezeBypassPermission = new Permission("spf.bypass");
    public static Permission freezeNotify = new Permission("spf.notify");
    public static Permission freezeChatSpy = new Permission("spf.chatspy");
    public static Permission reloadPermission = new Permission("spf.reload");
    
    public static void sendErrorMessage(CommandSender sender, String message) {
        if (message.isEmpty()) return;
        sender.sendMessage(miniMessage.deserialize(message));
    }
    public static void sendUserMessage(CommandSender sender, String message) {
        if (message.isEmpty()) return;
        sender.sendMessage(miniMessage.deserialize(LocaleHandler.getInstance().getPrefix() + message));
    }
    
    public static void sendUserMessageWithPlayer(CommandSender sender, String message, Player player) {
        if (message.isEmpty()) return;
        sender.sendMessage(miniMessage.deserialize(ConfigHandler.getInstance().getPrefix() + message,
                Placeholder.component("name", player.displayName())));
    }
    
    public static void formatMutedMessages(CommandSender sender, String format, Player player, Component message) {
        if (format.isEmpty()) return;
        sender.sendMessage(miniMessage.deserialize(format,
                Placeholder.component("player", player.displayName()),
                Placeholder.component("message", message)));
    }
    
    
    public static boolean isFrozen(Player player) {
        return player.getPersistentDataContainer().getOrDefault(isFrozenKey, PersistentDataType.BOOLEAN, false);
    }
    
}
