package com.satsukirin.ReddoLib.Items;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.satsukirin.ReddoLib.PluginMain;

public class ItemHelper {
	public static String getItemID(ItemStack item) {
		return item.getItemMeta().getPersistentDataContainer()
				.get(PluginMain.getNamespacedKey("id"), PersistentDataType.STRING);
	}
	public static String getExtendData(ItemStack item,String extend,String key) {
		return item.getItemMeta().getPersistentDataContainer()
				.get(PluginMain.getNamespacedKey("data"), PersistentDataType.TAG_CONTAINER)
				.get(PluginMain.getNamespacedKey(extend), PersistentDataType.TAG_CONTAINER)
				.get(PluginMain.getNamespacedKey(key), PersistentDataType.STRING);
	}
}
