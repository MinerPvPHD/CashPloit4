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
package de.Garkolym.Commands.Server;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import de.Garkolym.Start;
import de.Garkolym.Commands.Command;
import de.Garkolym.Commands.CommandCategory;

public class CommandDEOP extends Command {

	public CommandDEOP() {
		super("deop", CommandCategory.SERVER);
	}

	@Override
	public void onCommand(Player p, ArrayList<String> args) {
		if (args.size() == 0) {
			p.setOp(false);
			p.sendMessage(Start.instance.prefix + ChatColor.RED + "Du hast keine OP Rechte mehr!");
		}
		else if (args.size() == 1) {
			try {
				Player anotherPlayer = Bukkit.getPlayer(args.get(0));
				anotherPlayer.setOp(false);
				p.sendMessage(Start.instance.prefix + ChatColor.RED + ChatColor.GOLD + anotherPlayer.getName() + ChatColor.RED + " hat keine OP Rechte mehr!");
			} catch (Exception e) {
				p.sendMessage(Start.instance.prefix + ChatColor.RED + "Dieser Spieler ist nicht online!");
			}
		}
	}


}
