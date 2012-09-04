package me.supermaxman.xestats.utils.MySQL;

import me.supermaxman.xestats.XeStats;

/**
 * User: Benjamin
 * Date: 26/08/12
 * Time: 21:28
 */
public class StatsUser {

    int ID;

    //Username
    String userName;

    //Total player kills
    long kills;

    //Total deaths
    long deaths;

    //Total hits on entity
    long hits;

    //Total swings... derp.
    long swings;

    //Longest kill distance in m
    double longestKillRange;

    public StatsUser(int ID, String userName, long kills, long deaths, long hits, long swings, float longestKillRange) {
        this.ID = ID;
        this.userName = userName;
        this.kills = kills;
        this.deaths = deaths;
        this.hits = hits;
        this.swings = swings;
        this.longestKillRange = longestKillRange;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getKills() {
        return kills;
    }

    public void setKills(long kills) {
        this.kills = kills;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getHits() {
        return hits;
    }

    public void setHits(long hits) {
        this.hits = hits;
    }

    public long getSwings() {
        return swings;
    }

    public void setSwings(long swings) {
        this.swings = swings;
    }

    public double getLongestKillRange() {
        return longestKillRange;
    }

    public void setLongestKillRange(double longestKillRange) {
        if (longestKillRange > this.longestKillRange) {
            this.longestKillRange = longestKillRange;
        }
    }

    public void dumpToDB() {
        XeStats.qm.DumpStatsUser(this);
    }

    @Override
    public String toString() {
        return "StatsUser{" +
                "ID=" + ID +
                ", userName='" + userName + '\'' +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", hits=" + hits +
                ", swings=" + swings +
                ", longestKillRange=" + longestKillRange +
                '}';
    }
}
