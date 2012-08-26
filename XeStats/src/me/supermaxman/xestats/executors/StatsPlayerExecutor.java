package me.supermaxman.xestats.executors;

import me.supermaxman.xestats.utils.ConfigUtils;

import org.bukkit.entity.Player;

public class StatsPlayerExecutor {
	public static void personalStats(Player player){
		ConfigUtils.configInfo(player);
		
	}
}
