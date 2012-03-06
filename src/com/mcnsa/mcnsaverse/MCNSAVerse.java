package com.mcnsa.mcnsaverse;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.mcnsa.mcnsaverse.listeners.PlayerListener;
import com.mcnsa.mcnsaverse.managers.CommandManager;

public class MCNSAVerse extends JavaPlugin {
	// load the minecraft logger
	Logger log = Logger.getLogger("Minecraft");

	// APIs
	public PermissionManager permissions = null;

	// and commands
	public CommandManager commandManager = null;

	// keep track of listeners
	public PlayerListener playerListener = null;

	public void onEnable() {
		// set up APIs
		this.setupPermissions();
		
		// setup things
		commandManager = new CommandManager(this);
		
		// set up listeners
		playerListener = new PlayerListener(this);
		
		log("plugin enabled!");
	}
	
	public void onDisable() {
		log("plugin disabled!");
	}

	// for simpler logging
	public void log(String info) {
		log.info("[MCNSAVerse] " + info);
	}

	// for error reporting
	public void error(String info) {
		log.info("[MCNSAVerse] <ERROR> " + info);
	}

	// for debugging
	// (disable for final release)
	public void debug(String info) {
		log.info("[MCNSAVerse] <DEBUG> " + info);
	}

	// load the permissions plugin
	public void setupPermissions() {
		if(Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
			this.permissions = PermissionsEx.getPermissionManager();
			log("permissions successfully loaded!");
		}
		else {
			error("PermissionsEx not found!");
		}
	}

	// just an interface function for checking permissions
	// if permissions are down, default to OP status.
	public boolean hasPermission(Player player, String permission) {
		if(permissions != null) {
			return permissions.has(player, "mcnsaverse." + permission);
		}
		else {
			return player.isOp();
		}
	}
}
