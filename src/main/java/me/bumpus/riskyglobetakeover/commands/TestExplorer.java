package me.bumpus.riskyglobetakeover.commands;

import me.bumpus.riskyglobetakeover.MapExplorer;
import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestExplorer implements CommandExecutor {

    RiskyGlobeTakeover plugin;

    public TestExplorer(RiskyGlobeTakeover plugin){ this.plugin = plugin; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;

        if(args.length == 0){ return false; }

        MapExplorer explorer = new MapExplorer("test", p.getLocation().getWorld().getHighestBlockAt(p.getLocation()).getLocation(), p.getLocation().getWorld().getHighestBlockAt(p.getLocation()).getLocation(), plugin);
        explorer.explore(Integer.parseInt(args[0]));
        return true;
    }
}
