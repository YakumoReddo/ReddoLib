package com.satsukirin.ReddoLib;

import org.bukkit.plugin.java.JavaPlugin;

import com.satsukirin.ReddoLib.Logger.ReddoLogger;

public class PluginMain extends JavaPlugin {
	
	private static PluginMain instance;
	private ReddoLogger logger;
	
	@Override
	public void onEnable() {
		PluginMain.instance=this;
		//Create Logger
		logger=new ReddoLogger();
		
	}
	
	public static ReddoLogger getReddoLogger() {
		return ReddoLogger.getInstance();
	}
	public static PluginMain getInstance() {
		return instance;
	}
}
