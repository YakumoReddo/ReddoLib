package com.satsukirin.ReddoLib.Tables;

import java.io.File;
import java.io.FileFilter;

import org.bukkit.configuration.file.YamlConfiguration;

public class TableLoader {
	
	
	public static void load(File file, boolean isDir) {
		if(isDir) {
			File[] files = file.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File arg0) {
					return (!arg0.isDirectory())&&arg0.getName().endsWith(".yml");
				}
			});
			for(File fil : files) {
				YamlConfiguration conf = YamlConfiguration.loadConfiguration(fil);
				for(String key : conf.getKeys(false)) {
					TableManager.getInstance().addTableBuilder(new TableBuilder(conf.getConfigurationSection(key)));
				}
			}
		}else {
			YamlConfiguration conf=YamlConfiguration.loadConfiguration(file);
			for(String key : conf.getKeys(false)) {
				TableManager.getInstance().addTableBuilder(new TableBuilder(conf.getConfigurationSection(key)));
			}
		}
	}
	public static void load(File file) {
		load(file, false);
	}
	
	
}

















