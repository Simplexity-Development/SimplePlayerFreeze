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
    public static NamespacedKey previouslyHadFlyPerms = new NamespacedKey(namespace, "previousflyperms");
    public static Permission freezePermission = new Permission("spf.freeze");
    public static Permission unfreezePermission = new Permission("spf.unfreeze");
    public static Permission freezeBypassPermission = new Permission("spf.bypass");
    public static Permission reloadPermission = new Permission("spf.reload");
    
    
    
    
    
    public static boolean isFrozen(Player player) {
        return player.getPersistentDataContainer().getOrDefault(isFrozenKey, PersistentDataType.BOOLEAN, Boolean.FALSE);
    }
    
}
