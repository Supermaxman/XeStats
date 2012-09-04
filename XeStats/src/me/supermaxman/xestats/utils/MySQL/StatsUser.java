package me.supermaxman.xestats.utils.MySQL;

import me.supermaxman.xestats.XeStats;

/**
 * User: Benjamin
 * Date: 26/08/12
 * Time: 21:28
 */
public class StatsUser {

    private int ID;

    //Username
    private String userName;

    //Total player kills
    private long kills;

    //Total deaths
    private long deaths;

    //Total hits on entity
    private long hits;

    //Total swings... derp.
    private long swings;

    //Longest kill distance in m
    private double longestKillRange;

    //User needs to be dumped to db
    private boolean isDirty = false;

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

    public String getUserName() {
        return userName;
    }

    public long getKills() {
        return kills;
    }

    public void setKills(long kills) {
        this.kills = kills;
        setDirty(true);
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
        setDirty(true);
    }

    public long getHits() {
        return hits;
    }

    public void setHits(long hits) {
        this.hits = hits;
        setDirty(true);
    }

    public long getSwings() {
        return swings;
    }

    public void setSwings(long swings) {
        this.swings = swings;
        setDirty(true);
    }

    public double getLongestKillRange() {
        return longestKillRange;
    }

    public void setLongestKillRange(double longestKillRange) {
        if (longestKillRange > this.longestKillRange) {
            this.longestKillRange = longestKillRange;
            setDirty(true);
        }
    }

    public void dumpToDB() {
        XeStats.qm.DumpStatsUser(this);
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
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
                ", isDirty=" + isDirty +
                '}';
    }
}
