package com.satsukirin.ReddoLib.Items;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemLoader {
	//a map contains all class
	private Map<String, Map<String, ItemBuilder> > classfications;
	//a map contains all itembuilder
	private Map<String, ItemBuilder> itemBuilderMap;
	public ItemLoader() {
		classfications = new HashMap<String, Map<String,ItemBuilder>>();
		//set default class
		classfications.put("Default", new HashMap<String, ItemBuilder>());
		itemBuilderMap=new HashMap<String, ItemBuilder>();
		
	}
	//add new class
	public void addClassfication(String cls) {
		if(classfications.containsKey(cls)) {
			return;
		}
		classfications.put(cls,new HashMap<String, ItemBuilder>());
	}
	//get class map
	public Map<String, ItemBuilder> getClassItemMap(String cls){
		if(classfications.containsKey(cls)) {
			return classfications.get(cls);
		}else {
			return null;
		}
	}
	//get itembuilder by id
	public ItemBuilder getItemBuilder(String id) {
		return itemBuilderMap.get(id);
	}
	//get itembuilder by id and class
	public ItemBuilder getItemBuilder(String id,String cls) {
		if(classfications.containsKey(cls)) {
			return classfications.get(cls).get(id);
		}
		return null;
	}
	
	//add itembuilder to map
	public void addItemBuilder(ItemBuilder ib) {
		//create new class if not exist
		if(classfications.containsKey(ib.getClassStr())) {
			addClassfication(ib.getClassStr());
		}
		
		classfications.get(ib.getClassStr()).put(ib.getId(), ib);
		itemBuilderMap.put(ib.getId(),ib);
		
	}
	//add itembuilder from item(or name this load?)
	public void addItemBuilder(File fil) {
		ConfigurationSection config = YamlConfiguration.loadConfiguration(fil);
		for (String key :config.getKeys(false)) {
			addItemBuilder(new ItemBuilder(config.getConfigurationSection(key)));
		}
	}
}

















