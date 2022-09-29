package me.bumpus.riskyglobetakeover.commands;

import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetWorldCommand implements CommandExecutor {
    RiskyGlobeTakeover plugin;
    public SetWorldCommand(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        plugin.getManager().setWorld(((Player) sender).getWorld());
        return true;
    }
}
