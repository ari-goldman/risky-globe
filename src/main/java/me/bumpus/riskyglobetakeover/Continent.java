package me.bumpus.riskyglobetakeover;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Continent {
    HashMap<String, Territory> territories = new HashMap<>();
    String name;
    String path;

    public Continent(String name, String path){
        this.name = name;
        this.path = path + "." + name;
    }

    public boolean addTerritory(Territory territory){
        if(territories.containsKey(territory.getName())) return false;
        territories.put(territory.getName(), territory);
        return true;
    }

    public HashMap<String, Territory> getTerritories() {
        return territories;
    }

    public boolean removeTerritory(String name){
        if(territories.containsKey(name)){
            territories.remove(name);
            return true;
        }
        return false;
    }

    public boolean checkBonus(){
        UUID player = territories.get(0).getOwner().getP().getUniqueId();
        for(Territory territory : territories.values()){
            if(!player.equals(territory.getOwner().getP().getUniqueId())) return false;
        }
        return true;
    }

    public void writeToFile(FileConfiguration config, String path){
        for(String key : territories.keySet()){
            territories.get(key).writeToFile(config, name);
        }
    }

    public String getName(){
        return name;
    }

}
