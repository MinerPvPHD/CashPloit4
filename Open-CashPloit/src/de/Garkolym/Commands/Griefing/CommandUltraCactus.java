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
package de.Garkolym.Commands.Griefing;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.Garkolym.Start;
import de.Garkolym.Commands.Command;
import de.Garkolym.Commands.CommandCategory;

public class CommandUltraCactus extends Command implements Listener {

	private boolean enabled = false;
	private int ticks = 0;
	private HashMap<Location, Integer> catusPositions = new HashMap<Location, Integer>();
	
	public CommandUltraCactus() {
		super("ultracactus", CommandCategory.GRIEFING);
		Bukkit.getPluginManager().registerEvents(this, Start.instance);
		Start.instance.eventManager.tickListener.add(this);
	}

	@Override
	public void onCommand(Player p, ArrayList<String> args) {
		if (this.enabled == false) {
			this.enabled = true;
			p.sendMessage(Start.instance.prefix + ChatColor.GREEN + "Versuche nun einen Kaktus zu plazieren :)");
		}
		else
		{
			this.catusPositions.clear();
			this.enabled = false;
			p.sendMessage(Start.instance.prefix + ChatColor.RED + "Die Kakteen wachsen nicht mehr brutal rum!");
		}
	}
	
	@EventHandler
	public void onPlace(final BlockPlaceEvent e) {
		if (this.enabled) {
			if (e.getBlock().getType().equals(Material.CACTUS)) {
				this.catusPositions.put(e.getBlock().getLocation(), e.getBlock().getLocation().getBlockY());
			}
		}
	}
	
	
	@Override
	public void onTick() {
		this.ticks++;
		if (this.ticks >= 3) {
			this.ticks = 0;
			for (Location l : this.catusPositions.keySet()) {
				if (this.catusPositions.get(l) <= l.getWorld().getMaxHeight()) {
					this.catusPositions.put(l, this.catusPositions.get(l) + 1);
					Location newCactus = new Location(l.getWorld(), l.getX(), (double) this.catusPositions.get(l), l.getZ());
					newCactus.getBlock().setType(Material.CACTUS);
				}
			}
		}
	}
	
	

}
