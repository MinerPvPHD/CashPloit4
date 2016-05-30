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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.Garkolym.Start;
import de.Garkolym.Commands.Command;
import de.Garkolym.Commands.CommandCategory;

public class CommandGlasVirus extends Command implements Listener {

	private Location virusPosition = null;
	private int size = 0;
	private boolean activated = false;
	
	public CommandGlasVirus() {
		super("glasvirus", CommandCategory.TROLLING);
		Bukkit.getPluginManager().registerEvents(this, Start.instance);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player p, ArrayList<String> args) {
		
		if (!this.activated) {
			this.activated = true;
			this.virusPosition = p.getLocation();
			Bukkit.getScheduler().runTask(Start.instance, new Runnable() {
				@Override
				public void run() {
					p.getLocation().getBlock().setType(Material.GLASS);
					p.getLocation().getBlock().setData((byte) 1337);
					p.sendMessage(Start.instance.prefix + ChatColor.GREEN + "GlasBox wurde gespawnt! Der darf nur nicht abgebaut werden!");
				}
			});

		}
		else
		{
			this.size = 0;
			this.activated = false;
			p.sendMessage(Start.instance.prefix + ChatColor.RED + "Die GlasBox wächst nicht mehr.");
		}
		
		
		
		
	}

	
	@EventHandler
	public void onBlockBreak(final BlockBreakEvent e) {
		if (this.activated) {
//			if (e.getBlock().getType().equals(Material.GLASS) && e.getBlock().getData() == (byte) 1337) {
				this.size++;
				for (double x = this.virusPosition.getX() - this.size; x <= this.virusPosition.getX() + this.size; x++) {
					for (double y = this.virusPosition.getY() - this.size; y <= this.virusPosition.getY() + this.size; y++) {
						for (double z = this.virusPosition.getZ() - this.size; z <= this.virusPosition.getZ() + this.size; z++) {
							new Location(e.getBlock().getWorld(),x,y,z).getBlock().setType(Material.GLASS);
						}
					}
				}
//			}
		}
	}
	
	

}
