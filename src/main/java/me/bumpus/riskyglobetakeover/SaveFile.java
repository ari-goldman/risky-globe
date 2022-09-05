package me.bumpus.riskyglobetakeover;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class SaveFile {

    private File file;
    private FileConfiguration fileConfiguration;
    Plugin plugin;
    String name;
    public SaveFile(Plugin plugin, String name){

        this.plugin = plugin;
        this.name = name;

        file = new File(plugin.getDataFolder(), name + ".yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            file.getParentFile().mkdirs();
//            plugin.saveResource(name + ".yml", false);
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);

    }

    public void saveFile(){
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileConfiguration getConfig(){
        return fileConfiguration;
    }

    public void clear(){
        file.delete();
        file = new File(plugin.getDataFolder(), name + ".yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            file.getParentFile().mkdirs();
//            plugin.saveResource(name + ".yml", false);
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
}
