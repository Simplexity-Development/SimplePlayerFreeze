package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;
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
        if (Util.isFrozen(player) && ConfigHandler.getInstance().shouldFreezePersist()) {
            FreezeFunctionality.setFrozen(player);
            for (Player playerNotif : notifyList) {
                Util.sendUserMessageWithPlayer(playerNotif, LocaleHandler.getInstance().getLoginNotif(), player);
            }
            Util.sendUserMessageWithPlayer(SimplePlayerFreeze.getSFConsoleSender(),
                    LocaleHandler.getInstance().getLoginNotifConsole(), player);
        } else if (Util.isFrozen(player)) {
            FreezeFunctionality.setUnfrozen(player);
            for (Player playerNotif : notifyList) {
                Util.sendUserMessageWithPlayer(playerNotif, LocaleHandler.getInstance().getLoginNotifNowUnfrozen(), player);
            }
            Util.sendUserMessageWithPlayer(SimplePlayerFreeze.getSFConsoleSender(),
                    LocaleHandler.getInstance().getLoginNotifConsoleUnfreezing(), player);
        }
    }

}
