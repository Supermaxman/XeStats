package me.supermaxman.xestats.executors;

import me.supermaxman.xestats.XeStats;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

abstract class BaseExecutor implements CommandExecutor {
    @SuppressWarnings("unused")
    private static XeStats XE;
    // Permission permission = xEssentials.permission;

    BaseExecutor(XeStats XE) {
        BaseExecutor.XE = XE;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        final String commandName = command.getName().toLowerCase();
        Player player;
        final boolean isPlayer = (sender instanceof Player);

        if (isPlayer) {
            player = (Player) sender;
        } else {
            return false;
        }

        this.run(player, args);

        return true;
    }

    protected abstract void run(Player player, String[] args);

}