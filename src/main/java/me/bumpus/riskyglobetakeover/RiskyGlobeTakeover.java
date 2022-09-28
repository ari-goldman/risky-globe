package me.bumpus.riskyglobetakeover;

import me.bumpus.riskyglobetakeover.commands.*;
import me.bumpus.riskyglobetakeover.commands.tabs.ConnectTab;
import me.bumpus.riskyglobetakeover.commands.tabs.ContinentTab;
import me.bumpus.riskyglobetakeover.commands.tabs.MapTab;
import me.bumpus.riskyglobetakeover.commands.tabs.TerritoryTab;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class RiskyGlobeTakeover extends JavaPlugin {

    HashMap<UUID, SaveFile> playerSaves = new HashMap<>();
    HashMap<String, Map> maps = new HashMap<>();
    HashMap<String, SaveFile> saveMaps = new HashMap<>();

    GameManager manager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Henlo!");
        this.saveDefaultConfig();

        getCommand("testsaves").setExecutor(new TestSaves(this));

        getCommand("map").setExecutor(new MapCommand(this));
        getCommand("map").setTabCompleter(new MapTab());

        getCommand("continent").setExecutor(new ContinentCommand(this));
        getCommand("continent").setTabCompleter(new ContinentTab(this));

        getCommand("territory").setExecutor(new TerritoryCommand(this));
        getCommand("territory").setTabCompleter(new TerritoryTab(this));

        getCommand("connect").setExecutor(new ConnectCommand(this));
        getCommand("connect").setTabCompleter(new ConnectTab(this));

        getCommand("setgameworld").setExecutor(new SetWorldCommand(this));

        getCommand("displayconnects").setExecutor(new DisplayConnectsCommand(this));

        getCommand("savemaps").setExecutor(new SaveMaps(this));

        getCommand("loadmap").setExecutor(new LoadMapCommand(this));

        manager = new GameManager(this);

    }

    public HashMap<UUID, SaveFile> getPlayerSaves() {
        return playerSaves;
    }

    public void saveMaps(){
        for(String key : maps.keySet()){
            saveMaps.put(key, new SaveFile(this, key));
            maps.get(key).writeToFile(saveMaps.get(key).getConfig());
            saveMaps.get(key).saveFile();
        }
    }

    public void loadMap(String name, int x, int y, int z){
        SaveFile file = new SaveFile(this, name);
        FileConfiguration config = file.getConfig();
        Map map = new Map(name, x, y, z);
        Continent currentCont;
        Territory currentTerr;

        for(String continent : config.getKeys(false)){
            currentCont = new Continent(continent, name);
            map.getContinents().put(continent, currentCont);
            for(String territory : config.getConfigurationSection(continent).getKeys(false)){
                currentTerr = new Territory(territory,
                        config.getInt(continent + "." + territory + ".dx"),
                        config.getInt(continent + "." + territory + ".dy"),
                        config.getInt(continent + "." + territory + ".dz"),
                        config.getString(continent + "." + territory + ".identifier"));
                currentTerr.setNeighborStrings((ArrayList<String>) config.getStringList(continent + "." + territory + ".neighbors"));
                currentTerr.setAbsPos(map);
                map.getContinents().get(continent).getTerritories().put(territory, currentTerr);
            }
        }


        // CONNECT TERRITORIES
        String[] paths;
        for(String continent : map.getContinents().keySet()){
            for(String territory : map.getContinents().get(continent).getTerritories().keySet()){
                currentTerr = map.getContinents().get(continent).getTerritories().get(territory);
                for(String neighbor : currentTerr.getNeighborStrings()){
                    paths = neighbor.split("\\.");
                    currentTerr.addNeighbor(map.getContinents().get(paths[1]).getTerritories().get(paths[2]));
                }
            }
        }

        maps.put(name, map);
        manager.loadMap(map);

    }

    public GameManager getManager() {
        return manager;
    }

    public HashMap<String, Map> getMaps() {
        return maps;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveMaps();
    }
}
