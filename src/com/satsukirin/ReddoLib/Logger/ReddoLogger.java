package com.satsukirin.ReddoLib.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.entity.Player;

import com.satsukirin.ReddoLib.PluginMain;

import net.md_5.bungee.api.ChatColor;

public class ReddoLogger {
	private static ReddoLogger instance;
	private Logger logger;
	private static String ReddoLibPrefix_Console="[ReddoLib] ";
	private static String ReddoLibPrefix_Player="["+ChatColor.AQUA+"ReddoLib"+ChatColor.WHITE+"] ";
	private static String ReddoLibPrefix_Warning_Player="["+ChatColor.RED+"Warning"+ChatColor.WHITE+"] "+ChatColor.RED;
	private List<Player> loggedPlayer;
	public ReddoLogger() {
		instance=this;
		logger = PluginMain.getInstance().getLogger();
		loggedPlayer=new ArrayList<Player>();
	}
	public static ReddoLogger getInstance() {
		return instance;
	}
	public void info(String msg) {
		logger.info(ReddoLibPrefix_Console+msg);
		boardcast2Player(msg);
	}
	public void warning(String msg) {
		logger.warning(msg);
		boardcast2Player(ReddoLibPrefix_Warning_Player+msg);
	}
	private void boardcast2Player(String msg) {
		for(Player p : loggedPlayer) {
			p.sendMessage(ReddoLibPrefix_Player+msg);
		}
	}
	public void addLogPlayer(Player p) {
		loggedPlayer.add(p);
	}
	public void removeLogPlayer(Player p) {
		loggedPlayer.remove(loggedPlayer.indexOf(p));
	}
	public boolean isLogPlayer(Player p) {
		return loggedPlayer.contains(p);
	}
	public void clearLogPlayer() {
		loggedPlayer.clear();
	}
	
}
