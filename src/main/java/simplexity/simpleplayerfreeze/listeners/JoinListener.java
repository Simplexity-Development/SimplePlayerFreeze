package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.freeze.FreezeFunctionality;

import java.util.ArrayList;

public class JoinListener implements Listener {
    
    public static ArrayList<Player> notifyList = new ArrayList<>();
    public static ArrayList<Player> spyList = new ArrayList<>();
    
    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        if (player.hasPermission(Util.freezeNotify)) {
            notifyList.add(player);
        }
        if (player.hasPermission(Util.freezeChatSpy)) {
            spyList.add(player);
        }
        if (Util.isFrozen(player) && ConfigSettings.freezePersist) {
            FreezeFunctionality.setFrozen(player);
            for (Player playerNotif : notifyList) {
                Util.sendUserMessageWithPlayer(playerNotif, ConfigSettings.loginNotif, player);
            }
            Util.sendUserMessageWithPlayer(SimplePlayerFreeze.getSFConsoleSender(),
                    ConfigSettings.loginNotifConsole, player);
        } else if (Util.isFrozen(player)) {
            FreezeFunctionality.setUnfrozen(player);
            for (Player playerNotif : notifyList) {
                Util.sendUserMessageWithPlayer(playerNotif, ConfigSettings.loginNotifNowUnfrozen, player);
            }
            Util.sendUserMessageWithPlayer(SimplePlayerFreeze.getSFConsoleSender(),
                    ConfigSettings.loginNotifConsoleUnfreezing, player);
        }
    }
    
}
