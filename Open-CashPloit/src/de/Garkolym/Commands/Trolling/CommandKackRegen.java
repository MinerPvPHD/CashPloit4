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
package de.Garkolym.Commands.Trolling;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.Garkolym.Start;
import de.Garkolym.Commands.Command;
import de.Garkolym.Commands.CommandCategory;

public class CommandKackRegen extends Command {

	private ArrayList<Player> vics = new ArrayList<Player>();
	private int ticks = 0;
	
	public CommandKackRegen() {
		super("kackregen", CommandCategory.TROLLING);
		Start.instance.eventManager.tickListener.add(this);
	}

	@Override
	public void onCommand(Player p, ArrayList<String> args) {
		if (args.size() == 1) {
			try {
				Player victim = Bukkit.getPlayer(args.get(0));
				if (!this.vics.contains(victim)) {
					this.vics.add(victim);
					p.sendMessage(Start.instance.prefix + ChatColor.GREEN + "Über dem Spieler wird nun gekackt!");
				}
				else
				{
					this.vics.remove(victim);
					p.sendMessage(Start.instance.prefix + ChatColor.RED + "Der KackRegen ist nun zu Ende!");
				}
			} catch (Exception e) {
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onTick() {
		this.ticks++;
		if (this.ticks > 2) {
			this.ticks = 0;
			for (Player v : this.vics) {
				v.getWorld().spawnFallingBlock(new Location(v.getWorld(), v.getLocation().getX(), v.getLocation().getY() + 5, v.getLocation().getZ()), 35, (byte) 12);
			}
		}
	}
	

}
