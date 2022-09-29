package me.bumpus.riskyglobetakeover;

import me.bumpus.riskyglobetakeover.other.PColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Territory {
    String name;
    ArrayList<Territory> neighbors;
    ArrayList<String> neighborStrings;
    int[] pos = new int[3];
    int[] absPos = new int[3];
    MapExplorer ground;
    GamePlayer owner;
    UUID identifier;
    String path;
    int troops;
    boolean blizzard;
    Map map;

    public Territory(String name, int dx, int dy, int dz, String path){

        this.path = path + "." + name;
        this.name = name;
        pos[0] = dx;
        pos[1] = dy;
        pos[2] = dz;
        neighbors = new ArrayList<>();
    }

    public int changeTroops(int newTroops){
        troops += newTroops;
        return troops;
    }

    public void writeToFile(FileConfiguration config, String path){
        path += "." + name;
        config.set(path + ".identifier", this.path);
        config.set(path + ".dx", pos[0]);
        config.set(path + ".dy", pos[1]);
        config.set(path + ".dz", pos[2]);

        if(neighbors.isEmpty()) return;

        ArrayList<String> neighborStrings = new ArrayList<>();
        for(Territory terr : neighbors){
            neighborStrings.add(terr.getPath());
        }
        config.set(path + ".neighbors", neighborStrings);
    }

    public void setAbsPos(Map map){
        this.map = map;
        int[] mapPos = map.getPos();
        //Location locat = new Location(GameManager.world, pos[0] + mapPos[0], pos[1], pos[2] + mapPos[0]);
        //locat = locat.getWorld()
        absPos[0] = pos[0] + mapPos[0];
        absPos[1] = pos[1] + mapPos[1];
        absPos[2] = pos[2] + mapPos[2];

        setOnGround();
    }

    public void setOnGround(World world){
        Location l = new Location(world, absPos[0], absPos[1], absPos[2]);
        l = l.getWorld().getHighestBlockAt(l).getLocation();
        absPos[0] = l.getBlockX();
        absPos[1] = l.getBlockY();
        absPos[2] = l.getBlockZ();
    }

    public void setOnGround(){
        setOnGround(GameManager.world);
    }

    public void explore(int distance, Map map){
        setAbsPos(map);
        explore(distance);
    }

    public void explore(int distance){
        ground = new MapExplorer(name,
                new Location(GameManager.world, absPos[0], absPos[1], absPos[2]),
                new Location(GameManager.world, absPos[0], absPos[1], absPos[2]),
                GameManager.plugin);
        ground.explore(distance);
    }

    public void explore(){
        explore(10);
    }

    public void explore(Map map){
        explore(10, map);
    }

    public void showColor(Player p){
        PColor color = owner.getColor();
        BlockData top;
        BlockData bottom;

        switch(color){
            case RED:
                bottom = Bukkit.createBlockData(Material.RED_TERRACOTTA);
                top = Bukkit.createBlockData(Material.RED_STAINED_GLASS);
                break;
            case ORANGE:
                bottom = Bukkit.createBlockData(Material.ORANGE_TERRACOTTA);
                top = Bukkit.createBlockData(Material.ORANGE_STAINED_GLASS);
                break;
            case YELLOW:
                bottom = Bukkit.createBlockData(Material.YELLOW_TERRACOTTA);
                top = Bukkit.createBlockData(Material.YELLOW_STAINED_GLASS);
                break;
            case GREEN:
                bottom = Bukkit.createBlockData(Material.LIME_TERRACOTTA);
                top = Bukkit.createBlockData(Material.LIME_STAINED_GLASS);
                break;
            case BLUE:
                bottom = Bukkit.createBlockData(Material.BLUE_TERRACOTTA);
                top = Bukkit.createBlockData(Material.BLUE_STAINED_GLASS);
                break;
            case PURPLE:
                bottom = Bukkit.createBlockData(Material.PURPLE_TERRACOTTA);
                top = Bukkit.createBlockData(Material.PURPLE_STAINED_GLASS);
                break;
            case BLIZZARD:
                bottom = Bukkit.createBlockData(Material.SNOW_BLOCK);
                top = Bukkit.createBlockData(Material.ICE);
                break;
            default:
                bottom = Bukkit.createBlockData(Material.GRAY_TERRACOTTA);
                top = Bukkit.createBlockData(Material.GRAY_STAINED_GLASS);
        }

        ground.showColor(p, bottom, top);
    }

    public void showBlock(Material mat){
        ground.showBlock(owner.getP(), mat);
    }

    public void showBlock(Player p, Material mat){
        ground.showBlock(p, mat);
    }

    public int[] getAbsPos(){ return absPos; }

    public void addNeighbor(Territory territory){
        neighbors.add(territory);
    }

    public void setBlizzard(boolean blizzard){
        this.blizzard = blizzard;
    }

    public boolean getBlizzard() { return blizzard; }

    public int getTroops(){
        return troops;
    }

    public int[] getPos() {
        return pos;
    }

    public GamePlayer getOwner() {
        return owner;
    }

    public boolean setOwner(GamePlayer player){
        if(player.getUuid().equals(this.owner.getUuid())){
            return false;
        }

        this.owner = player;
        return true;
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
