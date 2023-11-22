package simplexity.simpleplayerfreeze.listeners;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.freeze.FreezeFunctionality;

import java.util.ArrayList;

public class JoinListener implements Listener {
    
    public static ArrayList<Player> notifyList = new ArrayList<>();
    
    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        if (player.hasPermission(Util.freezeNotify)) {
            notifyList.add(player);
        }
        if (Util.isFrozen(player) && ConfigSettings.freezePersist) {
            FreezeFunctionality.setFrozen(player);
            for (Player playerNotif : notifyList) {
                playerNotif.sendMessage(Util.miniMessage.deserialize(
                        (ConfigSettings.prefix + ConfigSettings.loginNotif), Placeholder.component("name", player.displayName())));
            }
        } else if (Util.isFrozen(player)) {
            FreezeFunctionality.setUnfrozen(player);
            for (Player playerNotif : notifyList) {
                playerNotif.sendMessage(Util.miniMessage.deserialize(
                        (ConfigSettings.prefix + ConfigSettings.loginNotif), Placeholder.component("name", player.displayName())));
            }
        }
    }
    
}
