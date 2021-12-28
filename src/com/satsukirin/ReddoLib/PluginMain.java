package com.satsukirin.ReddoLib;

import org.bukkit.plugin.java.JavaPlugin;

import com.satsukirin.ReddoLib.Command.ReddoLibCommands;
import com.satsukirin.ReddoLib.Items.ItemManager;
import com.satsukirin.ReddoLib.Logger.ReddoLogger;

public class PluginMain extends JavaPlugin {
	
	private static PluginMain instance;
	private ReddoLogger logger;
	
	@Override
	public void onEnable() {
		PluginMain.instance=this;
		//Create Logger
		logger=new ReddoLogger();
		
		new ItemManager();
		
		//create command executor
		ReddoLibCommands rlc = new ReddoLibCommands();
		this.getCommand("rl").setExecutor(rlc);
		this.getCommand("reddolib").setExecutor(rlc);
		
	}
	
	public static ReddoLogger getReddoLogger() {
		return ReddoLogger.getInstance();
	}
	public static PluginMain getInstance() {
		return instance;
	}
	public static ItemManager getItemManager() {
		return ItemManager.getInstance();
	}
}
