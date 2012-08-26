package me.supermaxman.xestats.utils;

import me.supermaxman.xestats.XeStats;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 12/08/12
 * Time: 19:09
 */
public class ColorUtils {

    public static String getColoredName(Player player){
       return ChatColor.translateAlternateColorCodes('&', XeStats.chat.getPlayerPrefix(player)) + player.getName();
    }
    
}
