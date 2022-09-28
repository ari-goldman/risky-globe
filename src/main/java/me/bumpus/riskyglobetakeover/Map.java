package me.bumpus.riskyglobetakeover;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;

public class Map {
    String name;
    int[] pos = new int[3];
    HashMap<String, Continent> continents = new HashMap<>();

    public Map(String name, int x, int y, int z){
        this.name = name;
        pos[0] = x;
        pos[1] = y;
        pos[2] = z;
    }

    public boolean addContinent(Continent continent){
        if(continents.containsKey(continent.getName())) return false;
        continents.put(continent.getName(), continent);
        return true;
    }

    public String getName() {
        return name;
    }

    public void writeToFile(FileConfiguration config){
        for(String key : continents.keySet()){
            continents.get(key).writeToFile(config, name);
        }
    }

    public int getX(){ return pos[0]; }
    public int getY(){ return pos[1]; }
    public int getZ(){ return pos[2]; }
    public int[] getPos() { return pos; }
    public HashMap<String, Continent> getContinents() {
        return continents;
    }
}
