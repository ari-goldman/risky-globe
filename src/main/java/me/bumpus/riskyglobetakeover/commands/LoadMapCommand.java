package me.bumpus.riskyglobetakeover.commands;

import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LoadMapCommand implements CommandExecutor {

    RiskyGlobeTakeover plugin;
    public LoadMapCommand(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) { return false; }

        Location l = ((Player) sender).getLocation();

        if(args.length == 1){
            plugin.loadMap(args[0], l.getBlockX(), l.getBlockY(), l.getBlockZ());
        }
        return false;
    }
}
