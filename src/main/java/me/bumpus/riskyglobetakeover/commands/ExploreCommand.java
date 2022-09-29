package me.bumpus.riskyglobetakeover.commands;

import me.bumpus.riskyglobetakeover.GameManager;
import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ExploreCommand implements CommandExecutor {

    RiskyGlobeTakeover plugin;
    public ExploreCommand(RiskyGlobeTakeover plugin){ this.plugin = plugin; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0){
            plugin.exploreTerritories();
        }else{
            try{
                int dist = Integer.parseInt(args[0]);
                plugin.exploreTerritories(dist);
            }catch(Exception e){
                return false;
            }
        }
        return true;
    }
}
