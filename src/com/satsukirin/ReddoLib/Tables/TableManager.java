package com.satsukirin.ReddoLib.Tables;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class TableManager {
	
	private static TableManager instance;
	
	
	private Map<String, TableBuilder> tableMap;
	private Set<String> idSet;
	
	public TableManager() {
		tableMap=new HashMap<String, TableBuilder>();
		idSet=new TreeSet<String>();
		instance=this;
	}
	public void addTableBuilder(TableBuilder item) {
		idSet.add(item.getId());
		tableMap.put(item.getId(),item);
	}
	public boolean containId(String id) {
		return tableMap.containsKey(id);
	}
	public TableBuilder getTableBuilder(String id) {
		return tableMap.get(id);
	}
	public void removeTableBuilder(String id) {
		idSet.remove(id);
		tableMap.remove(id);
	}
	public static TableManager getInstance() {
		return instance;
	}
	public Set<String> getIdSet(){
		return idSet;
	}

	
}
