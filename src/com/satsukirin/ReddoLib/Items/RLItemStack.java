package com.satsukirin.ReddoLib.Items;

import org.bukkit.inventory.ItemStack;

public class RLItemStack {
	private String id;
	private int amount;
	private ItemStack itemStack;
	private boolean changed=true;
	
	public RLItemStack(String tid) {
		this.id=tid;
		amount=1;
	}
	public void rebuildItem() {
		itemStack=ItemManager.getInstance().getItem(id);
		itemStack.setAmount(amount);
		changed=false;
	}
	public ItemStack getItemStack() {
		if(changed) {
			rebuildItem();
		}
		return this.itemStack;
	}
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
