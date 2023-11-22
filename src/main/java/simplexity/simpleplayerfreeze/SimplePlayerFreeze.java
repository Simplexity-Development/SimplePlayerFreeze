package simplexity.simpleplayerfreeze;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simpleplayerfreeze.commands.FreezePlayer;
import simplexity.simpleplayerfreeze.commands.ReloadConfig;
import simplexity.simpleplayerfreeze.commands.UnfreezePlayer;
import simplexity.simpleplayerfreeze.listeners.*;

import java.util.List;

public final class SimplePlayerFreeze extends JavaPlugin {
    public static Plugin simplePlayerFreeze;
    
    @Override
    public void onEnable() {
        simplePlayerFreeze = this;
        //Register the commands for the plugin, this is supposedly the best way to do commands now
        getServer().getCommandMap().register(Util.namespace, new FreezePlayer("freeze", "Freezes a player in place and prevents them from using normal things", "/freeze <player>", List.of("cease")));
        getServer().getCommandMap().register(Util.namespace, new UnfreezePlayer("unfreeze", "Unfreezes a player and allows them to use normal things", "/unfreeze <player>", List.of("resume")));
        getServer().getCommandMap().register(Util.namespace, new ReloadConfig("freezereload", " reloads the config", "/freezereload", List.of("simpleplayerfreezereload", "spfreload")));
        registerListeners();
        this.saveDefaultConfig();
        this.reloadConfig();
        ConfigSettings.reloadConfigSettings();
    }
    // Registers the Event Listeners for the events that will be blocked when someone is frozen
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new EXPPickupListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new CraftingListener(), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);
        getServer().getPluginManager().registerEvents(new PickupListener(), this);
        getServer().getPluginManager().registerEvents(new SwitchItemListener(), this);
        getServer().getPluginManager().registerEvents(new ItemConsumeListener(), this);
        getServer().getPluginManager().registerEvents(new CommandListener(), this);
    }
}
