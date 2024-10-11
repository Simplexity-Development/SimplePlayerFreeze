package simplexity.simpleplayerfreeze.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simpleplayerfreeze.SimplePlayerFreeze;
import simplexity.simpleplayerfreeze.Util;
import simplexity.simpleplayerfreeze.configs.LocaleHandler;
import simplexity.simpleplayerfreeze.events.PlayerFreezeEvent;
import simplexity.simpleplayerfreeze.freeze.FreezeType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FreezeAll implements TabExecutor {
    ArrayList<String> tabComplete = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (freezeWorld(sender, args)) return true;
        freezeServer(sender);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        tabComplete.clear();
        if (!sender.hasPermission(Util.freezeWorldPermission)) {
            return List.of("");
        }
        List<World> worlds = SimplePlayerFreeze.server.getWorlds();
        for (World world : worlds) {
            tabComplete.add(world.getName());
        }
        return tabComplete;
    }

    public boolean freezeWorld(CommandSender sender, String[] args) {
        String worldName;
        if (args.length == 0) return false;
        if (!sender.hasPermission(Util.freezeWorldPermission)) {
            Util.sendErrorMessage(sender, LocaleHandler.getInstance().getNoPermission());
            return false;
        }
        worldName = args[0];
        World requestedWorld = SimplePlayerFreeze.server.getWorld(worldName);
        if (requestedWorld == null) {
            Util.sendErrorMessage(sender, LocaleHandler.getInstance().getWorldNotFound());
            return false;
        }
        List<Player> players = requestedWorld.getPlayers();
        for (Player player : players) {
            if (Util.isFrozen(player)) continue;
            SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(player, true, FreezeType.WORLD));
        }
        Util.worldFrozen.put(requestedWorld, true);
        Util.sendUserMessageWithWorld(sender, LocaleHandler.getInstance().getFreezeWorldMessage(), requestedWorld);
        return true;
    }

    public void freezeServer(CommandSender sender) {
        if (!sender.hasPermission(Util.freezeServerPermission)) {
            Util.sendErrorMessage(sender, LocaleHandler.getInstance().getNoPermission());
            return;
        }
        Collection<? extends Player> players = SimplePlayerFreeze.server.getOnlinePlayers();
        for (Player player : players) {
            if (Util.isFrozen(player)) continue;
            SimplePlayerFreeze.getInstance().getServer().getPluginManager().callEvent(new PlayerFreezeEvent(player, true, FreezeType.SERVER));
        }
        Util.setServerFrozen(true);
        Util.sendUserMessage(sender, LocaleHandler.getInstance().getFreezeServerMessage());
    }
}

