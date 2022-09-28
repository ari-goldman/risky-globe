package me.bumpus.riskyglobetakeover.commands;

import me.bumpus.riskyglobetakeover.Continent;
import me.bumpus.riskyglobetakeover.Map;
import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MapCommand implements CommandExecutor {

    RiskyGlobeTakeover plugin;
    public MapCommand(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) { return true; }

        Location l = ((Player) sender).getLocation();

        if(args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                plugin.getMaps().put(args[1], new Map(args[1], l.getBlockX(), l.getBlockY(), l.getBlockZ()));
                plugin.getManager().loadMap(plugin.getMaps().get(args[1]));
                plugin.getLogger().info("Map \"" + args[1] + "\" created!");
            }
            return true;
        }
        return false;
    }
}
