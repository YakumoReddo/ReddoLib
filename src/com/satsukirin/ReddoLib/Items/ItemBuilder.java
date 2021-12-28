package com.satsukirin.ReddoLib.Items;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.satsukirin.ReddoLib.PluginMain;


//this is a custom item class
public class ItemBuilder {
	//item id default "UnnamedItem%s"%HHmmss(Time)
	private String id;
	//item name in display default same to id
	private String name;
	//item material default grassblock
	private Material material;
	//item lores default empty(not null)
	private List<String> lores;
	//item data default empty(not null)
	private Map<String, Map<String, String> > data;
	//if item changed simply after the last rebuild
	private boolean changed;
	
	//itemstack instance
	private ItemStack tempitem;
	
	//default item builder, generate a default item
	public ItemBuilder() {
		id="UnnamedItem"+(new SimpleDateFormat("HHmmss")).format(new Date());
		name=id;
		material = Material.GRASS_BLOCK;
		lores=new ArrayList<String>();
		data=new HashMap<String, Map<String,String>>();
		changed=true;
	}
	
	public ItemBuilder(ConfigurationSection section) {
		id=section.getName();
		name=section.getString("name");
		material=Material.valueOf(section.getString("material"));
		if(section.contains("lore")) {
			lores=section.getStringList("lore");
		}
		if(section.contains("data")) {
			ConfigurationSection dataSection = section.getConfigurationSection("data");
			for(String key : dataSection.getKeys(false)) {
				ConfigurationSection csec = dataSection.getConfigurationSection(key);
				Map<String, String> tmpMap = new HashMap<String, String>();
				for(String ckey:csec.getKeys(false)) {
					tmpMap.put(ckey, csec.getString(ckey));
				}
				data.put(key, tmpMap);
			}
		}
		changed=true;
	}
	
	//rebuild itemstack instance into tempitem
	private void rebuildItem() {
		tempitem = new ItemStack(material);
		ItemMeta meta = tempitem.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lores);
		//create data pdc to storage extend pdc
		PersistentDataContainer pdcdata = meta.getPersistentDataContainer().getAdapterContext().newPersistentDataContainer();
		for(String key : data.keySet()) {
			//get all data in map
			Map<String, String> tmap = data.get(key);
			//create extend pdc
			PersistentDataContainer pdc = meta.getPersistentDataContainer().getAdapterContext().newPersistentDataContainer();
			for(Entry<String, String> ess : tmap.entrySet()) {
				pdc.set(new NamespacedKey(PluginMain.getInstance(), ess.getKey()), PersistentDataType.STRING, ess.getValue());
			}
			pdcdata.set(new NamespacedKey(PluginMain.getInstance(), key),PersistentDataType.TAG_CONTAINER,pdc);
			
		}
		meta.getPersistentDataContainer().set(new NamespacedKey(PluginMain.getInstance(), "data"), PersistentDataType.TAG_CONTAINER, null);
		tempitem.setItemMeta(meta);
		changed=false;
	}
	
	//get item
	public ItemStack getItem() {
		if(changed) {
			rebuildItem();
		}
		return tempitem.clone();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		changed=true;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		changed=true;
		this.name = name;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		changed=true;
		this.material = material;
	}
	public List<String> getLores() {
		return lores;
	}
	public Map<String, Map<String, String>> getData() {
		return data;
	}
	
}

