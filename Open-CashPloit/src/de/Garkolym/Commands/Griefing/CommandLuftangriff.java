/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 OlfillasOdikno
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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;


import de.Garkolym.Start;
import de.Garkolym.Commands.Command;
import de.Garkolym.Commands.CommandCategory;

public class CommandLuftangriff extends Command{

	private int tick = 0;
	private ArrayList<Player> vics = new ArrayList<Player>();
	private ArrayList<Entity> ent = new ArrayList<Entity>();
	public CommandLuftangriff() {
		super("luftangriff", CommandCategory.GRIEFING);
		Start.instance.eventManager.tickListener.add(this);
	}

	@Override
	public void onCommand(Player p, ArrayList<String> args) {
		if (args.size() == 1) {
			try {
				Player victim = Bukkit.getPlayer(args.get(0));
				if(victim != null){
					if (!this.vics.contains(victim)) {
						this.vics.add(victim);
					    p.sendMessage(Start.instance.prefix + ChatColor.GREEN + "Der Spieler wird nun angegriffen!");
					}else{
						this.vics.remove(victim);
					    p.sendMessage(Start.instance.prefix + ChatColor.RED + "Der Spieler wird nun nicht mehr angegriffen!");
					}
				}else{
					p.sendMessage(Start.instance.prefix + ChatColor.RED + "Der Spieler ist nicht online!");
				}			
			}catch(Exception e){
				p.sendMessage(Start.instance.prefix + ChatColor.RED + "Der Spieler ist nicht online!");
			}
		}else{
			p.sendMessage(Start.instance.prefix + ChatColor.RED + "Bitte gib nach dem Command nur den Namen des Spielers an!");
		}
		
	}
	
	
	@Override
	public void onTick() {
		tick++;
		if(tick > 30){
			for(Player p: this.vics){
				attack(p);
				tick=0;
			}
		}
		explode();
	}
	
	public void attack(Player p){
		Location l = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY()+15, p.getLocation().getZ());
		Entity e = p.getWorld().spawnEntity(l, EntityType.CHICKEN);
		ent.add(e);
	}
	
	public void explode(){
		try{
			for(Entity e: ent){
				if(e.isOnGround()){			
					e.getWorld().createExplosion(e.getLocation(), 2F);
					e.remove();
					ent.remove(e);
					
				}
			}	
		}catch (Exception e) {
		}
	
	}
	


}
