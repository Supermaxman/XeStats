package me.supermaxman.xestats.executors;

import me.supermaxman.xestats.XeStats;
import me.supermaxman.xestats.utils.MySQLUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class StatsLookupExecutor {
	public static void lookupStats(Player player, String name){
		if(XeStats.XE.getServer().getPlayer(name)!=null){
			MySQLUtils.sendPlayerInfo(player, XeStats.XE.getServer().getPlayer(name));

		}else{
			player.sendMessage(ChatColor.RED+"[XeStats]: No Player Found.");
		}
		
	}
}
