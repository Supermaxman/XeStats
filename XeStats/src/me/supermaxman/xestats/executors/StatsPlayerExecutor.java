package me.supermaxman.xestats.executors;

import me.supermaxman.xestats.utils.MySQLUtils;

import org.bukkit.entity.Player;

public class StatsPlayerExecutor {
	public static void personalStats(Player player){
		MySQLUtils.sendPlayerInfo(player);
	}
}
