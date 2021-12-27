package com.satsukirin.ReddoLib.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import com.satsukirin.ReddoLib.Items.ItemManager;

public class ReddoLibCommands implements TabExecutor {
	
	public ReddoLibCommands() {
		
		
		
	}
	

	private static String[] l0cmd = {"item","log","reload"};
	private static String[] l1itemcmd= {"get","give","list","info"};
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

		if(args.length==1) {
			return Arrays.stream(l0cmd).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
		}
		if(args[0].equalsIgnoreCase("item")) {
			if(args.length==2) {
				return Arrays.stream(l1itemcmd).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
			}
			if(!args[1].equalsIgnoreCase("list") && args.length==3) {
				return Arrays.stream((String[])ItemManager.getInstance().getIdSet().toArray()).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
			}
		}
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}

}
