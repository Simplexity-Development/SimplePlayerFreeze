package simplexity.simpleplayerfreeze;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simpleplayerfreeze.commands.FreezePlayer;
import simplexity.simpleplayerfreeze.commands.FreezeSpy;
import simplexity.simpleplayerfreeze.commands.ReloadConfig;
import simplexity.simpleplayerfreeze.commands.UnfreezePlayer;
import simplexity.simpleplayerfreeze.listeners.*;
import simplexity.simpleplayerfreeze.placeholderapi.IsFrozenPlaceholder;

public final class SimplePlayerFreeze extends JavaPlugin {
    public static Plugin simplePlayerFreeze;
    public static Server server;
    public static ConsoleCommandSender sfConsoleSender;
    
    @Override
    public void onEnable() {
        simplePlayerFreeze = this;
        server = getServer();
        sfConsoleSender = server.getConsoleSender();
        boolean papiEnabled = getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
        System.out.println(papiEnabled);
        //Register the commands for the plugin, this is supposedly the best way to do commands now
        this.getCommand("freeze").setExecutor(new FreezePlayer());
        this.getCommand("unfreeze").setExecutor(new UnfreezePlayer());
        this.getCommand("freezereload").setExecutor(new ReloadConfig());
        this.getCommand("freezespy").setExecutor(new FreezeSpy());
        if (papiEnabled) {
            new IsFrozenPlaceholder().register();
        }
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
    
    public static ConsoleCommandSender getSFConsoleSender() {
        return sfConsoleSender;
    }
}
