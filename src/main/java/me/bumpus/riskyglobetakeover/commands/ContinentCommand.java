package me.bumpus.riskyglobetakeover.commands;

import me.bumpus.riskyglobetakeover.Continent;
import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ContinentCommand implements CommandExecutor {
    RiskyGlobeTakeover plugin;
    public ContinentCommand(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 3){
            if(args[0].equalsIgnoreCase("add")){
                plugin.getMaps().get(args[1]).addContinent(new Continent(args[2], args[1]));
                plugin.getLogger().warning("Continent \"" + args[1] + "." + args[2] + "\" created");
            }else if(args[0].equalsIgnoreCase("delete")){
                plugin.getMaps().get(args[1]).getContinents().remove(args[1]);
                plugin.getLogger().warning("Continent \"" + args[1] + "." + args[2] + "\" deleted");
            }
            return true;
        }

        return false;
    }
}
