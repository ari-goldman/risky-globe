package me.bumpus.riskyglobetakeover.commands;

import me.bumpus.riskyglobetakeover.SaveFile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import me.bumpus.riskyglobetakeover.RiskyGlobeTakeover;

import java.util.HashMap;
import java.util.UUID;

public class TestSaves implements CommandExecutor {

    RiskyGlobeTakeover plugin;
    HashMap<UUID, SaveFile> playerSaves;

    public TestSaves(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        playerSaves = plugin.getPlayerSaves();
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            if(!playerSaves.containsKey(player.getUniqueId())){
                playerSaves.put(player.getUniqueId(), new SaveFile(plugin, player.getUniqueId().toString()));
            }

            playerSaves.get(player.getUniqueId()).getConfig().set("level", player.getLevel());
            playerSaves.get(player.getUniqueId()).saveFile();

        }
        return false;
    }
}
