package com.satsukirin.ReddoLib.Items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

//this is a custom item class
public class ItemBuilder {
	//item inPlugin id
	private String id;
	//item name show on inventory
	private String name;
	//item material
	private Material material;
	//item lore
	private List<String> lore;
	//item nbts
	private Map<String,String> nodeMap;
	//item classification
	private String classStr;
	//item 
	public ItemBuilder(ConfigurationSection section) {
		id = section.getName();
		if(section.contains("id")) {
			
			name = section.getString("name");
		}
		material = Material.valueOf(section.getString("material"));
		if(section.contains("lore")) {
			lore=section.getStringList("lore");
		}
		
		if(section.contains("tag")) {
			ConfigurationSection tagsec = section.getConfigurationSection("tag");
			nodeMap = new HashMap<String, String>();
			for(Entry<String, Object> ent : tagsec.getValues(false).entrySet()) {
				nodeMap.put(ent.getKey(),(String)ent.getValue());
			}
			
		}
		if(section.contains("class")) {
			classStr=section.getString("class");
		}else {
			classStr="Default";
		}
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public List<String> getLore() {
		return lore;
	}
	public void setLore(List<String> lore) {
		this.lore = lore;
	}
	public Map<String, String> getNodeMap() {
		return nodeMap;
	}
	public void setNodeMap(Map<String, String> nodeMap) {
		this.nodeMap = nodeMap;
	}
	public String getClassStr() {
		return classStr;
	}
	public void setClassStr(String classStr) {
		this.classStr = classStr;
	}
}





/*
标准物品配置文件格式如下

物品id (String)
  -物品名 (String)
  -物品材质 (MATERIAL)*
  -物品描述 (List(String))
  -物品标签 (Node)
    -标签1:值
    -标签2:值
  -物品分类 (String)
Item1:
  material: minecraft:stone
  name: TestItem1
  lore:
  - Lore1
  - Lore2
  tag:
    key1:value
    key2:value
  class: dev
  
	

*/