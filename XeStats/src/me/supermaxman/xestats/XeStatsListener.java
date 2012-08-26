package me.supermaxman.xestats;

import me.supermaxman.xestats.utils.ConfigUtils;
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
        ConfigUtils.addDeath(player);
        if (player.getKiller() != null) {
            if (player.getKiller() instanceof Player) {
                Player killer = event.getEntity().getKiller();
                ConfigUtils.addKill(killer);
                ConfigUtils.checkFarKill(killer, killer.getLocation().distance(player.getLocation()));
                System.out.println(killer.getLocation().distance(player.getLocation()));

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
            ConfigUtils.addHit((Player) event.getDamager());
            ConfigUtils.addSwing((Player) event.getDamager());
        }
    }


    @EventHandler
    public void onSwing(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            ConfigUtils.addSwing(event.getPlayer());
        }
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            ConfigUtils.addSwing((Player) event.getEntity());
        }
    }

    @EventHandler
    public void onHitByArrow(EntityDamageEvent event) {
        if (event.getCause() == DamageCause.PROJECTILE) {
            if (((EntityDamageByEntityEvent) event).getDamager() instanceof Arrow) {
                if (((Projectile) ((EntityDamageByEntityEvent) event).getDamager()).getShooter() instanceof Player) {
                    ConfigUtils.addHit((Player) ((Projectile) ((EntityDamageByEntityEvent) event).getDamager()).getShooter());
                }
            }
        }
    }


}
