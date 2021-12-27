package com.satsukirin.ReddoLib.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.inventory.ItemStack;

public class ItemManager {
	
	private static ItemManager instance;
	
	
	private Map<String, ItemBuilder> itemMap;
	private Set<String> idSet;
	
	public ItemManager() {
		itemMap=new HashMap<String, ItemBuilder>();
		idSet=new TreeSet<String>();
		instance=this;
	}
	public void addItemBuilder(ItemBuilder item) {
		idSet.add(item.getId());
		itemMap.put(item.getId(),item);
	}
	public boolean containId(String id) {
		return itemMap.containsKey(id);
	}
	public ItemBuilder getItemBuilder(String id) {
		return itemMap.get(id);
	}
	public ItemStack getItem(String id) {
		return getItemBuilder(id).getItem();
	}
	public void removeItemBuilder(String id) {
		idSet.remove(id);
		itemMap.remove(id);
	}
	public static ItemManager getInstance() {
		return instance;
	}
	public Set<String> getIdSet(){
		return idSet;
	}

	
}
