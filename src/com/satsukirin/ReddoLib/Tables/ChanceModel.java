package com.satsukirin.ReddoLib.Tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.satsukirin.ReddoLib.Utils.Pair;

public class ChanceModel <T extends Object>{
	private int totalChance;
	private List<Pair<T, Integer>> items;
	private static Random random = new Random();
	public ChanceModel() {
		items=new ArrayList<Pair<T,Integer>>();
		totalChance=0;
		
	}
	public void addItem(T item,int chance) {
		items.add(new Pair<T, Integer>(item, chance));
		totalChance+=chance;
	}
	public T getRandom() {
		int val=random.nextInt(totalChance)+1;
		for(Pair<T, Integer> pp : items) {
			val-=pp.getSecond();
			if(val<=0) {
				return pp.getFirst();
			}
		}
		return null;
	}
}
