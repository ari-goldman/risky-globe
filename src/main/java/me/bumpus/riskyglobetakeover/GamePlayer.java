package me.bumpus.riskyglobetakeover;

import me.bumpus.riskyglobetakeover.other.PColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GamePlayer {

    PColor color;
    Player p;
    UUID uuid;

    public GamePlayer(Player p, PColor color){
        this.p = p;
        uuid = p.getUniqueId();
        this.color = color;
    }

    public PColor getColor() {
        return color;
    }

    public void setColor(PColor color) {
        this.color = color;
    }

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}
