package simplexity.simpleplayerfreeze.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player should be frozen or unfrozen
 */
public class PlayerFreezeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final boolean frozen;

    public PlayerFreezeEvent(Player player, final boolean frozen) {
        this.player = player;
        this.frozen = frozen;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    /**
     * The player the event involves
     * @return Player frozen
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Whether this event is going to freeze this player or unfreeze them
     * @return boolean - True to freeze the player, False to unfreeze them
     */
    public boolean setFrozen() {
        return frozen;
    }

    /**
     * Bukkit requires this
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
