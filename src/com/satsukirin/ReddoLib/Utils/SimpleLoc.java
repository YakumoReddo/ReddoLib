package com.satsukirin.ReddoLib.Utils;

import org.bukkit.Location;

public class SimpleLoc {

	private Location loc;
	
	public SimpleLoc(Location loc) {
		this.loc=loc;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Location) {
			Location l = (Location)o;
			if(l.getWorld().equals(loc.getWorld())&&l.getBlockX()==loc.getBlockX()&&
					l.getBlockY()==loc.getBlockY()&&l.getBlockZ()==loc.getBlockZ())return true;
			else return false;
		}else if (o instanceof SimpleLoc) {
			SimpleLoc sl = (SimpleLoc)o;
			Location l = sl.getLocation();
			if(l.getWorld().equals(loc.getWorld())&&l.getBlockX()==loc.getBlockX()&&
					l.getBlockY()==loc.getBlockY()&&l.getBlockZ()==loc.getBlockZ())return true;
			else return false;
		}else {
			return false;
		}
		
	}
	public double get3DMahatonDistance(SimpleLoc sloc) {
		return Math.abs(sloc.getLocation().getBlockX()-loc.getBlockX())
				+Math.abs(sloc.getLocation().getBlockY()-loc.getBlockY())
				+Math.abs(sloc.getLocation().getBlockZ()-loc.getBlockZ());
	}
	public double get2DMahatonDistance(SimpleLoc sloc) {
		return Math.abs(sloc.getLocation().getBlockX()-loc.getBlockX())
				+Math.abs(sloc.getLocation().getBlockZ()-loc.getBlockZ());
	}
	public Location getLocation() {
		return loc;
	}
		
}
