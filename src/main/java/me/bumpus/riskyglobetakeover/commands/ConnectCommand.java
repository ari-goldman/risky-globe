package me.bumpus.riskyglobetakeover.commands;

import me.bumpus.riskyglobetakeover.Map;
import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import me.bumpus.riskyglobetakeover.Territory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ConnectCommand implements CommandExecutor {

    RiskyGlobeTakeover plugin;
    public ConnectCommand(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Map map;
        Territory territoryOne = null;
        Territory territoryTwo = null;
        if(args.length == 3){
            map = plugin.getMaps().get(args[0]);
            for(String continent : map.getContinents().keySet()){
                for(String territory : map.getContinents().get(continent).getTerritories().keySet()){
                    if(territory.equals(args[1]) || territory.equals(args[2])) {
                        if (territoryOne == null) {
                            territoryOne = map.getContinents().get(continent).getTerritories().get(territory);
                            plugin.getLogger().warning(territoryOne.getName() + " - " + territoryOne.getPath().toString());
                        } else if (territoryTwo == null) {
                            territoryTwo = map.getContinents().get(continent).getTerritories().get(territory);
                            plugin.getLogger().warning(territoryTwo.getName() + " - " + territoryTwo.getPath().toString());
                        }
                    }
                }
            }

            territoryOne.addNeighbor(territoryTwo);
            territoryTwo.addNeighbor(territoryOne);

            return true;
        }

        return false;
    }
}
