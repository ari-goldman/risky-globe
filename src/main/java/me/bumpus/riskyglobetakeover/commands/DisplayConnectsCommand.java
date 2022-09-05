package me.bumpus.riskyglobetakeover.commands;

import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DisplayConnectsCommand implements CommandExecutor {

    RiskyGlobeTakeover plugin;
    public DisplayConnectsCommand(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        plugin.getManager().displayConnects();
        return true;
    }
}
