package com.satsukirin.ReddoLib.Tables;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import com.satsukirin.ReddoLib.PluginMain;
import com.satsukirin.ReddoLib.Items.ItemManager;
import com.satsukirin.ReddoLib.Items.RLItemStack;


//this is a custom item class
public class TableBuilder {
	//item id default "UnnamedItem%s"%HHmmss(Time)
	private String id;
	private ChanceModel<String> chanceModel;
	///TODO change chanceModel's String id into table's object id, restore extend and other infos in table's object
	private Map<String , RLItemStack> rliMap;
	private String name;
	
	//default item builder, generate a default item
	public TableBuilder() {
		id="UnnamedTable"+(new SimpleDateFormat("HHmmss")).format(new Date());
		chanceModel = new ChanceModel<String>();
		rliMap=new HashMap<String, RLItemStack>();
	}
	
	public TableBuilder(ConfigurationSection section) {
		id=section.getName();
		chanceModel = new ChanceModel<String>();
		if(section.contains("name")) {
			name=section.getString("name");
		}
		ConfigurationSection itemSection = section.getConfigurationSection("items");
		for(String key : itemSection.getKeys(false)) {
			ConfigurationSection items = itemSection.getConfigurationSection(key);
			String iid = items.getString("id");
			if(!ItemManager.getInstance().containId(iid)) {
				PluginMain.getReddoLogger().warning("No ReddoLib's item id '"+iid+"' while process table '"+id+"'.");
				continue;
			}
			RLItemStack rli = new RLItemStack(iid);
			
			if(items.contains("amount")) {
				rli.setAmount(items.getInt("amount"));
			}
			chanceModel.addItem(items.getName(),items.getInt("chance"));
			rliMap.put(items.getName(), rli);
			
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	public ItemStack getRandItem() {
		String iid = chanceModel.getRandom();
		RLItemStack rli = rliMap.get(iid);
		return rli.getItemStack();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

/*
table1:
  items:
    bound1:
      item: id
      chance: 5
    bound2:
      item: id
      amount: 5
      chance: 5
    bound3:
      item: id
      amount: 1
          

*/