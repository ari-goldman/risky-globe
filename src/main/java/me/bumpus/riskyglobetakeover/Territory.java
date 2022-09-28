package me.bumpus.riskyglobetakeover;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.UUID;

public class Territory {
    String name;
    private ArrayList<Territory> neighbors;
    ArrayList<String> neighborStrings;
    int[] pos = new int[3];
    int[] absPos = new int[3];
    UUID owner;
    UUID identifier;
    String path;
    int troops;
    boolean blizzard;

    public Territory(String name, int dx, int dy, int dz, String path){


        this.path = path + "." + name;
        this.name = name;
        pos[0] = dx;
        pos[1] = dy;
        pos[2] = dz;
        neighbors = new ArrayList<>();
    }

    public boolean newOwner(UUID newOwner){
        if(newOwner.equals(owner)){
            return false;
        }

        owner = newOwner;
        return true;
    }

    public int changeTroops(int newTroops){
        troops += newTroops;
        return troops;
    }

    public int getTroops(){
        return troops;
    }

    public int[] getPos() {
        return pos;
    }

    public void writeToFile(FileConfiguration config, String path){
        path = path + "." + name;
        config.set(path + ".identifier", this.path);
        config.set(path + ".dx", pos[0]);
        config.set(path + ".dy", pos[1]);
        config.set(path + ".dz", pos[2]);

        if(neighbors.isEmpty()) return;

        ArrayList<String> neighborStrings = new ArrayList<>();
        for(int i = 0; i < neighbors.size(); i++){
            neighborStrings.add(neighbors.get(i).getPath());
            System.out.println(neighbors.get(i).getPath());
        }
        config.set(path + ".neighbors", neighborStrings);
    }

    public void setAbsPos(Map map){
        int[] mapPos = map.getPos();
        absPos[0] = pos[0] + mapPos[0];
        absPos[1] = pos[1] + mapPos[1];
        absPos[2] = pos[2] + mapPos[2];

    }

    public int[] getAbsPos(){ return absPos; }

    public void addNeighbor(Territory territory){
        neighbors.add(territory);
    }

    public void setBlizzard(boolean blizzard){
        this.blizzard = blizzard;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Territory> getNeighbors() {
        return neighbors;
    }

    public ArrayList<String> getNeighborStrings() {
        return neighborStrings;
    }

    public void setNeighborStrings(ArrayList<String> neighborStrings) {
        this.neighborStrings = neighborStrings;
    }

    public String getPath(){
        return path;
    }
}
