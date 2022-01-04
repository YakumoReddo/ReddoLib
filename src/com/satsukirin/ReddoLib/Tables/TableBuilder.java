package com.satsukirin.ReddoLib.Tables;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import com.satsukirin.ReddoLib.Items.ItemManager;


//this is a custom item class
public class TableBuilder {
	//item id default "UnnamedItem%s"%HHmmss(Time)
	private String id;
	private ChanceModel<String> chanceModel;
	
	
	
	
	//default item builder, generate a default item
	public TableBuilder() {
		id="UnnamedTable"+(new SimpleDateFormat("HHmmss")).format(new Date());
		chanceModel = new ChanceModel<String>();
	}
	
	public TableBuilder(ConfigurationSection section) {
		id=section.getName();
		chanceModel = new ChanceModel<String>();
		ConfigurationSection itemSection = section.getConfigurationSection("items");
		for(String key : itemSection.getKeys(false)) {
			ConfigurationSection items = itemSection.getConfigurationSection(key);
			String iid = items.getString("id");
			//id = id+(%02d)%amount    e.g.: testitem001  one testitem0
			if(items.contains("amount")) {
				int amount=items.getInt("amount");
				iid+=(amount/10)+""+(amount%10);
			}else {
				iid+="01";
			}
			chanceModel.addItem(iid,items.getInt("chance"));
			
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
		ItemStack item = ItemManager.getInstance().getItem(iid.substring(0,iid.length()-2));
		item.setAmount(Integer.parseInt(iid.substring(iid.length()-2)));
		return item;
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