package com.satsukirin.ReddoLib;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {
	
	private static PluginMain instance;
	
	
	@Override
	public void onEnable() {
		PluginMain.instance=this;
	}
	
	public static PluginMain getInstance() {
		return instance;
	}
}
