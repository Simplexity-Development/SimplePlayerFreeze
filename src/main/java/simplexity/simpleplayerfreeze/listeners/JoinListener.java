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
import simplexity.simpleplayerfreeze.freeze.FreezeType;

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
        if (player.hasPermission(Util.freezeBypassPermission)) {
            return;
        }
        // This is gonna get annoying to look at soon
        if (Util.isServerFrozen()) {
            if (!ConfigHandler.getInstance().shouldFreezeNewLogins()) return;
            freezePlayer(joinEvent, FreezeType.SERVER);
            sendMessages(player, LocaleHandler.getInstance().getLoginNotifServerFrozen());
            return;
        }
        Boolean worldFrozen = Util.worldFrozen.get(player.getWorld());
        if (worldFrozen != null && worldFrozen) {
            if (!ConfigHandler.getInstance().shouldFreezeNewLogins()) return;
            freezePlayer(joinEvent, FreezeType.WORLD);
            sendMessages(player, LocaleHandler.getInstance().getFreezeWorldChange());
            return;
        }
        if (Util.isFrozen(player) && !ConfigHandler.getInstance().shouldFreezePersist()) {
            unfreezePlayer(joinEvent);
            sendMessages(player, LocaleHandler.getInstance().getLoginNotifNowUnfrozen());
            return;
        }
        if (Util.isFrozen(player) && ConfigHandler.getInstance().shouldFreezePersist()) {
            FreezeType freezeType = Util.getFreezeType(player);
            if (freezeType != FreezeType.INDIVIDUAL) {
                unfreezePlayer(joinEvent);
                sendMessages(player, LocaleHandler.getInstance().getLoginNotifNowUnfrozen());
                return;
            }
            freezePlayer(joinEvent, FreezeType.INDIVIDUAL);
            sendMessages(player, LocaleHandler.getInstance().getLoginNotif());

        }

    }

    private void freezePlayer(PlayerJoinEvent joinEvent, FreezeType freezeType) {
        if (!ConfigHandler.getInstance().shouldFreezeNewLogins()) return;
        Player player = joinEvent.getPlayer();
        SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(player, true, freezeType));
    }

    private void unfreezePlayer(PlayerJoinEvent joinEvent){
        Player player = joinEvent.getPlayer();
        SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(player, false, FreezeType.NONE));
    }

    private void sendMessages(Player player, String message) {
        for (Player playerNotif : notifyList) {
            Util.sendUserMessageWithPlayer(playerNotif, message, player);
        }
        if (ConfigHandler.getInstance().shouldConsoleBeNotified()) {
            Util.sendUserMessageWithPlayer(SimplePlayerFreeze.getSFConsoleSender(), message, player);
        }
    }

}
