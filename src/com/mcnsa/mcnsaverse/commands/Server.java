package com.mcnsa.mcnsaverse.commands;

import org.bukkit.entity.Player;

import com.mcnsa.mcnsaverse.MCNSAVerse;
import com.mcnsa.mcnsaverse.util.ColourHandler;
import com.mcnsa.mcnsaverse.util.Command;
import com.mcnsa.mcnsaverse.util.CommandInfo;

@CommandInfo(alias = "server", permission = "server", usage = "<server>[:port]", description = "joins a specified server")
public class Server implements Command {
	@SuppressWarnings("unused")
	private static MCNSAVerse plugin = null;
	public Server(MCNSAVerse instance) {
		plugin = instance;
	}
	
	@Override
	public Boolean handle(Player player, String sArgs) {
		// make sure they have args
		if(sArgs.trim().length() < 1) {
			return false;
		}
		
		// see if they have a port specified
		String name = "";
		Integer port = 25565;
		if(sArgs.contains(":")) {
			String[] parts = sArgs.split(":");
			name = parts[0].trim().replace(" ", "");
			try {
				port = Integer.parseInt(parts[1].trim());
			}
			catch(Exception e) {
				port = 25565;
			}
		}
		else {
			name = sArgs.trim().replace(" ", "");
		}
		
		// send them the redirect message
		ColourHandler.sendMessage(player, "&4 &c &2 &a redirect:" + name + ":" + port);
		player.kickPlayer("Please join: " + name + ":" + port);
		return true;
	}
}
