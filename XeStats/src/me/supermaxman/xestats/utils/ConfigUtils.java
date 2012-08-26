package me.supermaxman.xestats.utils;

import me.supermaxman.xestats.XeStats;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ConfigUtils {

    public static void configInfo(Player p) {
        if (p != null) {
            FileConfiguration config = XeStats.conf;
            setupPlayerConfig(p);

            ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());
            sendPlayerInfo(p, cs);
        }
    }

    public static void configInfo(Player p, Player lookup) {
        if (p != null && lookup != null) {
            FileConfiguration config = XeStats.conf;
            setupPlayerConfig(lookup);

            ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + lookup.getName());

            sendPlayerInfo(p, lookup, cs);
        } else {
            p.sendMessage(ChatColor.RED + "[XeStats]: This Player Does Not Exist Or Isnt Online.");
        }
    }

    public static String configCheck(Player p, String check) {
        FileConfiguration config = XeStats.conf;
        setupPlayerConfig(p);

        ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());

        return cs.getString(check);

    }

    public static void configSet(Player p, String path, String set) {
        FileConfiguration config = XeStats.conf;
        setupPlayerConfig(p);

        ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());

        cs.set(path, set);
        calculateKDR(p, cs);
        XeStats.XE.saveConfig();
    }


    public static void addKill(Player p) {
        FileConfiguration config = XeStats.conf;
        setupPlayerConfig(p);

        ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());

        cs.set("kills", Integer.toString(Integer.parseInt(cs.getString("kills")) + 1));
        calculateKDR(p, cs);
        XeStats.XE.saveConfig();
    }

    public static void addDeath(Player p) {
        FileConfiguration config = XeStats.conf;
        setupPlayerConfig(p);

        ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());
        cs.set("deaths", Integer.toString(Integer.parseInt(cs.getString("deaths")) + 1));
        calculateKDR(p, cs);
        XeStats.XE.saveConfig();
    }

    public static void addHit(Player p) {
        FileConfiguration config = XeStats.conf;
        setupPlayerConfig(p);

        ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());

        cs.set(".hitmiss.hits", Integer.toString(Integer.parseInt(cs.getString(".hitmiss.hits")) + 1));
        calculateHitMiss(p, cs);
        XeStats.XE.saveConfig();
    }

    public static void addSwing(Player p) {
        FileConfiguration config = XeStats.conf;
        setupPlayerConfig(p);
        ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());

        cs.set(".hitmiss.swings", Integer.toString(Integer.parseInt(cs.getString(".hitmiss.swings")) + 1));
        calculateHitMiss(p, cs);
        XeStats.XE.saveConfig();
    }

    public static void addStat(Player p, String stat) {
        FileConfiguration config = XeStats.conf;
        setupPlayerConfig(p);

        ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());

        cs.set(stat, Integer.toString(Integer.parseInt(cs.getString(stat)) + 1));
        calculateKDR(p, cs);
        calculateHitMiss(p, cs);
        XeStats.XE.saveConfig();
    }

    public static void setFarKill(Player p, double d) {
        FileConfiguration config = XeStats.conf;
        setupPlayerConfig(p);

        ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());

        cs.set("farthestkill", Double.toString(d).substring(0, cs.getString("deaths").length() - 3));
        XeStats.XE.saveConfig();
    }


    public static void checkFarKill(Player p, double d) {
        FileConfiguration config = XeStats.conf;
        setupPlayerConfig(p);

        ConfigurationSection cs = config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName());
        if (Double.parseDouble((String) cs.get("farthestkill")) < d) {
            cs.set("farthestkill", Integer.toString((int) d));
        }
        XeStats.XE.saveConfig();
    }

    protected static void setupPlayerConfig(Player p) {
        FileConfiguration config = XeStats.conf;
        if (config.getConfigurationSection("players." + p.getWorld().getName() + "." + p.getName()) == null) {
            config.set("players." + p.getWorld().getName() + "." + p.getName() + ".kills", "0");
            config.set("players." + p.getWorld().getName() + "." + p.getName() + ".deaths", "0");
            config.set("players." + p.getWorld().getName() + "." + p.getName() + ".kdr", "0");
            config.set("players." + p.getWorld().getName() + "." + p.getName() + ".hitmiss.hits", "0");
            config.set("players." + p.getWorld().getName() + "." + p.getName() + ".hitmiss.swings", "0");
            config.set("players." + p.getWorld().getName() + "." + p.getName() + ".accuracy", "0%");
            config.set("players." + p.getWorld().getName() + "." + p.getName() + ".farthestkill", "0");
            XeStats.XE.saveConfig();
        }
    }

    protected static void calculateKDR(Player p, ConfigurationSection cs) {
        if (Integer.parseInt(cs.getString("deaths")) != 0) {
            cs.set("kdr", Double.toString(Double.parseDouble(cs.getString("kills")) / Double.parseDouble(cs.getString("deaths"))));
        } else {
            cs.set("kdr", Double.toString(Integer.parseInt(cs.getString("kills"))));
        }
        XeStats.XE.saveConfig();
    }

    protected static void calculateHitMiss(Player p, ConfigurationSection cs) {

        if (Integer.parseInt(cs.getString(".hitmiss.swings")) != 0) {
            double hits = Integer.parseInt(cs.getString(".hitmiss.hits"));
            double swings = Integer.parseInt(cs.getString(".hitmiss.swings"));
            double d = hits / swings;
            int percent = (int) (d * 100);
            cs.set("accuracy", percent + "%");
        } else {
            cs.set("accuracy", "100%");
        }
        XeStats.XE.saveConfig();
    }

    protected static void sendPlayerInfo(Player p, ConfigurationSection cs) {
        calculateKDR(p, cs);
        calculateHitMiss(p, cs);
        int i = 0;
        String s[] = new String[100];
        cs.getKeys(false).toArray(s);
        p.sendMessage(ChatColor.AQUA + "[" + ChatColor.GOLD + "XeStats" + ChatColor.AQUA + "]" + ChatColor.AQUA + ": " + ColorUtils.getColoredName(p) + ChatColor.AQUA + "-" + ChatColor.GOLD + p.getWorld().getName() + ChatColor.AQUA + ChatColor.AQUA + ":");
        for (Object k : cs.getValues(false).values()) {
            if (!s[i].equals("hitmiss")) {
                if (s[i].equals("farthestkill")) {
                    p.sendMessage(ChatColor.AQUA + " - " + ChatColor.GOLD + s[i] + ChatColor.AQUA + ": " + ChatColor.GOLD + (k.toString() + " blocks"));
                } else {
                    p.sendMessage(ChatColor.AQUA + " - " + ChatColor.GOLD + s[i] + ChatColor.AQUA + ": " + ChatColor.GOLD + (k.toString()));
                }
            }
            i++;
        }
    }

    protected static void sendPlayerInfo(Player p, Player lookup, ConfigurationSection cs) {
        calculateKDR(lookup, cs);
        calculateHitMiss(lookup, cs);
        int i = 0;

        String s[] = new String[100];
        cs.getKeys(false).toArray(s);

        p.sendMessage(ChatColor.AQUA + "[" + ChatColor.GOLD + "XeStats" + ChatColor.AQUA + "]" + ChatColor.AQUA + ": " + ColorUtils.getColoredName(lookup) + ChatColor.AQUA + "-" + ChatColor.GOLD + lookup.getWorld().getName() + ChatColor.AQUA + ChatColor.AQUA + ":");
        for (Object k : cs.getValues(false).values()) {
            if (!s[i].equals("hitmiss")) {
                if (s[i].equals("farthestkill")) {
                    p.sendMessage(ChatColor.AQUA + " - " + ChatColor.GOLD + s[i] + ChatColor.AQUA + ": " + ChatColor.GOLD + (k.toString() + " blocks"));
                } else {
                    p.sendMessage(ChatColor.AQUA + " - " + ChatColor.GOLD + s[i] + ChatColor.AQUA + ": " + ChatColor.GOLD + (k.toString()));
                }
            }
            i++;
        }
    }
}
