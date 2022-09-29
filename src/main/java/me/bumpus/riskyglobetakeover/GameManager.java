package me.bumpus.riskyglobetakeover;

import me.bumpus.riskyglobetakeover.other.PColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GameManager {
    boolean blizzard;
    boolean fog;

    static RiskyGlobeTakeover plugin;
    ArrayList<GamePlayer> players;
    static World world;
    Map map;

    public GameManager(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
        players = new ArrayList<>();
    }

//    public void createPhysicalMap(){
//        for(Continent continent : map.getContinents()){
//            for()
//        }
//    }

    public void assignColors(){
        ArrayList<PColor> colors = new ArrayList<>();
        colors.add(PColor.RED);
        colors.add(PColor.ORANGE);
        colors.add(PColor.YELLOW);
        colors.add(PColor.GREEN);
        colors.add(PColor.BLUE);
        colors.add(PColor.PURPLE);
        Collections.shuffle(colors);;

        for(int i = 0; i < players.size(); i++){
            players.get(i).setColor(colors.get(i));
        }
    }

    public void setBlizzards(int amt){

        for(String contString : map.getContinents().keySet()){
            for(String terrString : map.getContinents().get(contString).getTerritories().keySet()){
                map.getContinents().get(contString).getTerritories().get(terrString).setBlizzard(false);
            }
        }

        while(amt > 0){
            Continent continent = map.getContinents()
                    .get(new ArrayList<String>(map
                            .getContinents()
                            .keySet())
                            .get((int)(Math.random()*map.getContinents().keySet().size())));
            Territory territory = continent.getTerritories()
                    .get(new ArrayList<String>(continent
                            .getTerritories()
                            .keySet())
                            .get((int)(Math.random()*continent.getTerritories().keySet().size())));
            if(!territory.getBlizzard()){
                territory.setBlizzard(true);
                amt--;
            }


        }
    }

    public void setWorld(World world) {
        this.world = world;
        plugin.getLogger().info("World set to " + this.world.getName());
    }

    public void loadMap(Map map){
        this.map = map;
        plugin.getLogger().info("Map set to " + map.getName());
    }

    public void exploreTerritories(int distance){
        HashMap<String, Continent> continents = map.getContinents();
        HashMap<String, Territory> territories;
        for(String contKey : continents.keySet()){
            territories = continents.get(contKey).getTerritories();
            for(String terrKey : territories.keySet()){
                territories.get(terrKey).explore(distance, map);
            }
        }
    }

    public void exploreTerritories(){
        exploreTerritories(25);
    }

    public void displayConnects(){

        //plugin.getLogger().info("Attempting to display connections...");

        Territory current;

        for(String continent : map.getContinents().keySet()){
            for(String territory : map.getContinents().get(continent).getTerritories().keySet()){
                current = map.getContinents().get(continent).getTerritories().get(territory);
                for(Territory neighbor : current.getNeighbors()){
                    particleBetween(current, neighbor);
                }
            }
        }

        //plugin.getLogger().info("Connections displayed!\n");

    }

    public void particleBetween(Territory one, Territory two){
        for(float i = 0; i < 1; i += 0.09){
            double[] pos = lerpedPosition(i, one.getAbsPos(), two.getAbsPos());
            world.spawnParticle(Particle.CLOUD, pos[0], pos[1], pos[2], 4, 0, 0, 0, 0);
        }
    }

    public double lerp(double amt, double a, double b){
        amt = Math.max(0, Math.min(1, amt));
        return (a * amt) + (b * (1 - amt));
    }

    public double[] lerpedPosition(double amt, int[] pos1, int[] pos2){
        double[] out = new double[3];
        out[0] = lerp(amt,(double) pos1[0], (double) pos2[0]);
        out[1] = lerp(amt,(double) pos1[1], (double) pos2[1]) + (Math.sin(Math.PI * amt) * (10 / Math.sqrt(Math.pow(pos1[0] - pos2[0], 2) + Math.pow(pos1[1] - pos2[1], 2))));
        out[2] = lerp(amt,(double) pos1[2], (double) pos2[2]);
        return out;
    }



}
