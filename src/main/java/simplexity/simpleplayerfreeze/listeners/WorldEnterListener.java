package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;
import simplexity.simpleplayerfreeze.events.PlayerFreezeEvent;
import simplexity.simpleplayerfreeze.freeze.FreezeType;

import java.util.List;

public class WorldEnterListener implements Listener {

    @EventHandler
    public void onWorldEnter(PlayerChangedWorldEvent worldChangeEvent) {
        World worldEntered = worldChangeEvent.getPlayer().getWorld();
        if (!Util.worldFrozen.containsKey(worldEntered)) return;
        Player player = worldChangeEvent.getPlayer();
        if (player.hasPermission(Util.freezeBypassPermission)) return;
        if (!ConfigHandler.getInstance().shouldFreezeWorldChange()) return;
        SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(worldChangeEvent.getPlayer(), true, FreezeType.WORLD));
        List<Player> notifyList = JoinListener.notifyList;
        for (Player notifyPlayer : notifyList) {
            Util.sendUserMessageWithPlayerAndWorld(notifyPlayer, LocaleHandler.getInstance().getFreezeWorldChange(), worldEntered, player);
        }
    }
}
