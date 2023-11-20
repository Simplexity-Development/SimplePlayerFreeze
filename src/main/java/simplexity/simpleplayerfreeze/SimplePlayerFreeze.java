package simplexity.simpleplayerfreeze;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simpleplayerfreeze.commands.FreezePlayer;
import simplexity.simpleplayerfreeze.commands.ReloadConfig;
import simplexity.simpleplayerfreeze.commands.UnfreezePlayer;
import simplexity.simpleplayerfreeze.listeners.*;

import java.util.List;

public final class SimplePlayerFreeze extends JavaPlugin {
    
    //Todo: Figure out how to make the server not freak out about the player floating
    public static Plugin simplePlayerFreeze;
    
    @Override
    public void onEnable() {
        simplePlayerFreeze = this;
        getServer().getCommandMap().register(Util.namespace, new FreezePlayer("freeze", "Freezes a player in place and prevents them from using normal things", "/freeze <player>", List.of("cease")));
        getServer().getCommandMap().register(Util.namespace, new UnfreezePlayer("unfreeze", "Unfreezes a player and allows them to use normal things", "/unfreeze <player>", List.of("resume")));
        getServer().getCommandMap().register(Util.namespace, new ReloadConfig("freezereload", " reloads the config", "/freezereload", List.of("simpleplayerfreezereload", "spfreload")));
        registerListeners();
        this.saveDefaultConfig();
        this.reloadConfig();
        ConfigSettings.reloadConfigSettings();
        // Plugin startup logic
        
    }
    
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new EXPPickupListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);
        getServer().getPluginManager().registerEvents(new PickupListener(), this);
        getServer().getPluginManager().registerEvents(new SwitchItemListener(), this);
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
