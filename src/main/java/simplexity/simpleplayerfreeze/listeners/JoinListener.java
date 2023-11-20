package simplexity.simpleplayerfreeze.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import simplexity.simpleplayerfreeze.ConfigSettings;
import simplexity.simpleplayerfreeze.Util;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        if (!ConfigSettings.freezePersist) return;
        if (!joinEvent.getPlayer().isOp()) {
            joinEvent.getPlayer().getPersistentDataContainer().set(Util.isFrozenKey, PersistentDataType.BOOLEAN, Boolean.TRUE);
            Util.setFrozen(joinEvent.getPlayer());
            joinEvent.getPlayer().sendRichMessage(ConfigSettings.loginMessage);
        }
    }

}
