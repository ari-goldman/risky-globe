package me.bumpus.riskyglobetakeover;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class MapExplorer {
    String name;
    Location l, origin;
    RiskyGlobeTakeover plugin;
    MapExplorer[] family;

    public MapExplorer(String name, Location l, Location origin, RiskyGlobeTakeover plugin){
        this.name = name;
        this.l = l.getWorld().getHighestBlockAt(l).getLocation();
        this.origin = origin.getWorld().getHighestBlockAt(origin).getLocation();
        this.plugin = plugin;
        family = new MapExplorer[4];
        l.getBlock().setType(Material.GRAY_CONCRETE);
    }

    public void explore(int maxDistance){
        int distX = l.getBlockX() - origin.getBlockX();
        int distZ = l.getBlockZ() - origin.getBlockZ();

        if(Math.sqrt((distX * distX) + distZ * distZ) > maxDistance){ plugin.getLogger().log(Level.SEVERE, l.getBlockX() + " " + l.getBlockZ() + " TOO FAR"); return; }



        MapExplorer nextExplorer;
        Location nextLocation;
        //int direction = (int)(Math.random() * 4); // 0-posX 1-posZ 2-negX 4-negZ
        for(int direction = 0; direction < 4; direction++) {
            nextLocation = l.clone();

            if(direction == 0){ nextLocation.add(1,0,0); }
            else if(direction == 1){ nextLocation.add(-1,0,0); }
            else if(direction == 2){ nextLocation.add(0,0,1); }
            else{ nextLocation.add(0,0,-1); }

            nextLocation = nextLocation.getWorld().getHighestBlockAt(nextLocation).getLocation();
            Material nextMaterial = nextLocation.getBlock().getBlockData().getMaterial();
            if (nextMaterial == Material.BLACK_CONCRETE_POWDER || nextMaterial == Material.GRAY_CONCRETE || nextMaterial.isAir()) {
                continue;
            }

            nextExplorer = new MapExplorer(name, nextLocation, origin, plugin);
            family[direction] = nextExplorer;
            nextExplorer.explore(maxDistance);
        }
    }

    public void showBlock(Player p, Material mat){
        p.sendBlockChange(l, Bukkit.createBlockData(mat));
        for(int i = 0; i < 4; i++){
            if(family[i] != null){
                family[i].showBlock(p, mat);
            }
        }
    }

    public void showColor(Player p, BlockData bottom, BlockData top){
        p.sendBlockChange(l, bottom);
        p.sendBlockChange(l.clone().add(0,1,0), top);
        for(int i = 0; i < 4; i++){
            if(family[i] != null){
                family[i].showColor(p, bottom, top);
            }
        }
    }


}
