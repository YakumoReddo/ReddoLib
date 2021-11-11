package com.satsukirin.ReddoLib.Items;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemLoader {
	
	private Map<String, Map<String, ItemBuilder> > classfications;
	private Map<String, ItemBuilder> itemBuilderMap;
	public ItemLoader() {
		classfications = new HashMap<String, Map<String,ItemBuilder>>();
		classfications.put("Default", new HashMap<String, ItemBuilder>());
		itemBuilderMap=new HashMap<String, ItemBuilder>();
		
	}
	public void addClassfication(String cls) {
		if(classfications.containsKey(cls)) {
			return;
		}
		classfications.put(cls,new HashMap<String, ItemBuilder>());
	}
	public Map<String, ItemBuilder> getClassItemMap(String cls){
		if(classfications.containsKey(cls)) {
			return classfications.get(cls);
		}else {
			return null;
		}
	}
	public ItemBuilder getItemBuilder(String id) {
		return itemBuilderMap.get(id);
	}
	public ItemBuilder getItemBuilder(String id,String cls) {
		if(classfications.containsKey(cls)) {
			return classfications.get(cls).get(id);
		}
		return null;
	}
	public void addItemBuilder(ItemBuilder ib) {
		classfications.get(ib.getClassStr()).put(ib.getId(), ib);
		itemBuilderMap.put(ib.getId(),ib);
		
	}
	public void addItemBuilder(File fil) {
		ConfigurationSection config = YamlConfiguration.loadConfiguration(fil);
		for (String key :config.getKeys(false)) {
			addItemBuilder(new ItemBuilder(config.getConfigurationSection(key)));
		}
	}
}

















