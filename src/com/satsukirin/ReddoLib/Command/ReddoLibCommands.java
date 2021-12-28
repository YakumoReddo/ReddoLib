package com.satsukirin.ReddoLib.Command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

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
		if(args[0].equalsIgnoreCase("item")) {
			if(args[1].equalsIgnoreCase("list")&&args.length==2) {
				for(String str : ItemManager.getInstance().getIdSet()) {
					sender.sendMessage(str+" : "+ItemManager.getInstance().getItemBuilder(str).getName());
				}
				return true;
			}
			if(args[1].equalsIgnoreCase("get")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage("only player can use this command!");
					return true;
				}
				Player player = (Player)sender;
				if(args.length==2) {
					player.sendMessage("id cannot be null!");
					return true;
				}
				if(args.length>3) {
					player.sendMessage("too much arguments!");
					return true;
				}
				if(ItemManager.getInstance().containId(args[2])) {
					player.getInventory().addItem(ItemManager.getInstance().getItem(args[2]));
					return true;
				}else {
					player.sendMessage("no such item!");
					return true;
				}
			}
		}
		return false;
	}

}
