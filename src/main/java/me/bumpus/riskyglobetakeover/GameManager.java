package me.bumpus.riskyglobetakeover;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {
    boolean blizzard;
    boolean fog;

    RiskyGlobeTakeover plugin;
    World world;
    Map map;

    public GameManager(RiskyGlobeTakeover plugin){
        this.plugin = plugin;
    }

    public void setWorld(World world) {
        this.world = world;
        plugin.getLogger().info("World set to " + this.world.getName());
    }



    public void loadMap(Map map){
        this.map = map;
        plugin.getLogger().info("Map set to " + map.getName());
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
            double[] pos = lerpedPosition(i, one.getPos(), two.getPos());
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
