package me.bumpus.riskyglobetakeover.commands.tabs;

import me.bumpus.riskyglobetakeover.Continent;
import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConnectTab implements TabCompleter {
    RiskyGlobeTakeover plugin;
    public ConnectTab(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        ArrayList<String> out = new ArrayList<>();

        if(args.length == 1){
            out.addAll(plugin.getMaps().keySet());
        }

        if(args.length == 2 || args.length == 3){
            for(String continent : plugin.getMaps().get(args[0]).getContinents().keySet()){
                out.addAll(plugin.getMaps().get(args[0]).getContinents().get(continent).getTerritories().keySet());
            }
            if(args.length == 3){
                out.remove(args[1]);
            }
        }

        return out;
    }
}
