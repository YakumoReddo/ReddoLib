package com.satsukirin.ReddoLib.Logger;

import java.util.logging.Logger;

import com.satsukirin.ReddoLib.PluginMain;

public class ReddoLogger {
	private Logger logger;
	public ReddoLogger() {
		logger = PluginMain.getInstance().getLogger();
	}
	
}
