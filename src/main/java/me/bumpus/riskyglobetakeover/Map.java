package me.bumpus.riskyglobetakeover;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;

public class Map {
    String name;
    HashMap<String, Continent> continents = new HashMap<>();

    public Map(String name){
        this.name = name;
    }

    public boolean addContinent(Continent continent){
        if(continents.containsKey(continent.getName())) return false;
        continents.put(continent.getName(), continent);
        return true;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Continent> getContinents() {
        return continents;
    }

    public void writeToFile(FileConfiguration config){
        for(String key : continents.keySet()){
            continents.get(key).writeToFile(config, name);
        }
    }
}
