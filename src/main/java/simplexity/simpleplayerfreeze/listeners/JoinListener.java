package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;
import simplexity.simpleplayerfreeze.events.PlayerFreezeEvent;

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
        // This is gonna get annoying to look at soon
        if (Util.isServerFrozen() && !player.hasPermission(Util.freezeBypassPermission) && ConfigHandler.getInstance().shouldFreezeNewLogins()) {
            SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(player, true));
            if (!ConfigHandler.getInstance().shouldNotifyNewLogins()) return;
            for (Player playerNotif : notifyList) {
                Util.sendUserMessageWithPlayer(playerNotif, LocaleHandler.getInstance().getLoginNotifServerFrozen(), player);
            }

        }
        if (Util.isFrozen(player)) {
            SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(player, false));
            for (Player playerNotif : notifyList) {
                Util.sendUserMessageWithPlayer(playerNotif, LocaleHandler.getInstance().getLoginNotifNowUnfrozen(), player);
            }
            Util.sendUserMessageWithPlayer(SimplePlayerFreeze.getSFConsoleSender(),
                    LocaleHandler.getInstance().getLoginNotifConsoleUnfreezing(), player);
            return;
        }
        if (Util.isFrozen(player) && ConfigHandler.getInstance().shouldFreezePersist()) {
            SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(player, true));
            for (Player playerNotif : notifyList) {
                Util.sendUserMessageWithPlayer(playerNotif, LocaleHandler.getInstance().getLoginNotif(), player);
            }
            Util.sendUserMessageWithPlayer(SimplePlayerFreeze.getSFConsoleSender(),
                    LocaleHandler.getInstance().getLoginNotifConsole(), player);
        }

    }

}
