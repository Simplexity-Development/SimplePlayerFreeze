package simplexity.simpleplayerfreeze;

import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simpleplayerfreeze.commands.FreezePlayer;
import simplexity.simpleplayerfreeze.commands.ReloadConfig;
import simplexity.simpleplayerfreeze.commands.UnfreezePlayer;
import simplexity.simpleplayerfreeze.listeners.EventListeners;

import java.util.List;

public final class SimplePlayerFreeze extends JavaPlugin {
    
    
    private static SimplePlayerFreeze instance;
    
    @Override
    public void onEnable() {
        instance = this;
        this.getServer().getPluginManager().registerEvents(new EventListeners(), this);
        getServer().getCommandMap().register(Util.namespace, new FreezePlayer("freeze", "Freezes a player in place and prevents them from using normal things", "/freeze <player>", List.of("cease")));
        getServer().getCommandMap().register(Util.namespace, new UnfreezePlayer("unfreeze", "Unfreezes a player and allows them to use normal things", "/unfreeze <player>", List.of("resume")));
        getServer().getCommandMap().register(Util.namespace, new ReloadConfig("freezereload", " reloads the config", "/freezereload", List.of("simpleplayerfreezereload", "spfreload")));
        this.saveDefaultConfig();
        this.reloadConfig();
        Util.reloadMessages();
        // Plugin startup logic
        
    }
    
    public static SimplePlayerFreeze getInstance() {
        return instance;
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
