package simplexity.simpleplayerfreeze;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simpleplayerfreeze.commands.FreezeAll;
import simplexity.simpleplayerfreeze.commands.FreezePlayer;
import simplexity.simpleplayerfreeze.commands.FreezeSpy;
import simplexity.simpleplayerfreeze.commands.ReloadConfig;
import simplexity.simpleplayerfreeze.commands.UnfreezeAll;
import simplexity.simpleplayerfreeze.commands.UnfreezePlayer;
import simplexity.simpleplayerfreeze.configs.ConfigHandler;
import simplexity.simpleplayerfreeze.hooks.discordsrv.DiscordSrvRegistration;
import simplexity.simpleplayerfreeze.listeners.AttackListener;
import simplexity.simpleplayerfreeze.listeners.ChatListener;
import simplexity.simpleplayerfreeze.listeners.CommandListener;
import simplexity.simpleplayerfreeze.listeners.CraftingListener;
import simplexity.simpleplayerfreeze.listeners.DropListener;
import simplexity.simpleplayerfreeze.listeners.EXPPickupListener;
import simplexity.simpleplayerfreeze.listeners.FreezeListener;
import simplexity.simpleplayerfreeze.listeners.InteractListener;
import simplexity.simpleplayerfreeze.listeners.InventoryOpenListener;
import simplexity.simpleplayerfreeze.listeners.ItemConsumeListener;
import simplexity.simpleplayerfreeze.listeners.JoinListener;
import simplexity.simpleplayerfreeze.listeners.LeaveListener;
import simplexity.simpleplayerfreeze.listeners.MoveListener;
import simplexity.simpleplayerfreeze.listeners.PickupListener;
import simplexity.simpleplayerfreeze.listeners.SwitchItemListener;
import simplexity.simpleplayerfreeze.listeners.WorldEnterListener;
import simplexity.simpleplayerfreeze.hooks.placeholderapi.IsFrozenPlaceholder;

public final class SimplePlayerFreeze extends JavaPlugin {
    public static SimplePlayerFreeze instance;
    public static Server server;
    public static ConsoleCommandSender sfConsoleSender;

    @Override
    public void onEnable() {
        instance = this;
        server = getServer();
        sfConsoleSender = server.getConsoleSender();
        boolean papiEnabled = getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
        boolean discordSrvEnabled = getServer().getPluginManager().isPluginEnabled("DiscordSRV");
        this.getCommand("freeze").setExecutor(new FreezePlayer());
        this.getCommand("unfreeze").setExecutor(new UnfreezePlayer());
        this.getCommand("freezereload").setExecutor(new ReloadConfig());
        this.getCommand("freezespy").setExecutor(new FreezeSpy());
        this.getCommand("freezeall").setExecutor(new FreezeAll());
        this.getCommand("unfreezeall").setExecutor(new UnfreezeAll());
        if (papiEnabled) {
            new IsFrozenPlaceholder().register();
        }
        if (discordSrvEnabled) {
            DiscordSrvRegistration.registerListener(this);
        }
        registerListeners();
        this.saveDefaultConfig();
        this.reloadConfig();
        ConfigHandler.getInstance().reloadConfigSettings();
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
        getServer().getPluginManager().registerEvents(new AttackListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpenListener(), this);
        getServer().getPluginManager().registerEvents(new FreezeListener(), this);
        getServer().getPluginManager().registerEvents(new WorldEnterListener(), this);
    }

    public static ConsoleCommandSender getSFConsoleSender() {
        return sfConsoleSender;
    }

    public static SimplePlayerFreeze getInstance() {
        return instance;
    }
}
