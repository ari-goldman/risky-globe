package me.bumpus.riskyglobetakeover.commands.tabs;

import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContinentTab implements TabCompleter {

    RiskyGlobeTakeover plugin;
    public ContinentTab(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        ArrayList<String> out = new ArrayList<>();

        if(args.length == 1){
            out.add("add");
            out.add("delete");
        }

        if(args.length == 2){
            for(String mapName : plugin.getMaps().keySet()){
                out.add(mapName);
            }
        }

        return out;
    }
}
