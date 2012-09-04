package me.supermaxman.xestats;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class XeStatsListener implements Listener {
    final XeStats plugin;

    public XeStatsListener(XeStats plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        XeStats.qm.handleJoin(event.getPlayer());
    }

    @EventHandler
    public void onDie(PlayerDeathEvent event) {
        Player player = event.getEntity();
        XeStats.statsUserMap.get(player.getName()).setDeaths(XeStats.statsUserMap.get(player.getName()).getDeaths() + 1);
        if (player.getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            XeStats.statsUserMap.get(killer.getName()).setKills(XeStats.statsUserMap.get(killer.getName()).getKills() + 1);
            double oldrange = XeStats.statsUserMap.get(killer.getName()).getLongestKillRange();
            double newRange = killer.getLocation().distance(player.getLocation());
            if (newRange > oldrange) {
                XeStats.statsUserMap.get(killer.getName()).setLongestKillRange(newRange);
            }

        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        XeStats.qm.handleQuit(player);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            XeStats.statsUserMap.get(player.getName()).setHits(XeStats.statsUserMap.get(player.getName()).getHits() + 1);
            XeStats.statsUserMap.get(player.getName()).setSwings(XeStats.statsUserMap.get(player.getName()).getSwings() + 1);
        }
    }


    @EventHandler
    public void onSwing(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            Player player = event.getPlayer();
            XeStats.statsUserMap.get(player.getName()).setSwings(XeStats.statsUserMap.get(player.getName()).getSwings() + 1);
        }
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            XeStats.statsUserMap.get(player.getName()).setSwings(XeStats.statsUserMap.get(player.getName()).getSwings() + 1);
        }
    }

    @EventHandler
    public void onHitByArrow(EntityDamageEvent event) {
        if (event.getCause() == DamageCause.PROJECTILE) {
            if (((EntityDamageByEntityEvent) event).getDamager() instanceof Arrow) {
                if (((Projectile) ((EntityDamageByEntityEvent) event).getDamager()).getShooter() instanceof Player) {
                    Player player = (Player) ((Projectile) ((EntityDamageByEntityEvent) event).getDamager()).getShooter();
                    XeStats.statsUserMap.get(player.getName()).setHits(XeStats.statsUserMap.get(player.getName()).getHits() + 1);
                }
            }
        }
    }


}
