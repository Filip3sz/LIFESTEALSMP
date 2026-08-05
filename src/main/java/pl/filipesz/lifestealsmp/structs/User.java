package pl.filipesz.lifestealsmp.structs;

import org.bukkit.entity.Player;

import java.util.UUID;

public class User {
    private UUID uuid;
    private double hearts;
    private double cash;

    private int kills;
    private int deaths;

    public User(Player p, double hearts, double cash, int kills, int deaths) {
        this.uuid = p.getUniqueId();
        this.hearts = hearts;
        this.cash = cash;

        this.kills = kills;
        this.deaths = deaths;
    }

    public double getHearts() {
        return hearts;
    }

    public double getCash() {
        return cash;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }
}
