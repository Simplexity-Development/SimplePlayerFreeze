package simplexity.simpleplayerfreeze.hooks.discordsrv;

import github.scarsz.discordsrv.DiscordSRV;
import org.bukkit.plugin.Plugin;

public class DiscordSrvRegistration {

    public static void registerListener(Plugin plugin) {
        DiscordSRV.api.subscribe(new DiscordMessageBlocker(plugin));
    }
}
