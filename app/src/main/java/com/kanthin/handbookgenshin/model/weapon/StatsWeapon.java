package com.kanthin.handbookgenshin.model.weapon;

import java.io.Serializable;

public class StatsWeapon implements Serializable {
    private int ascension;
    private double attack;
    private int level;
    private double specialized;
    private String lvKey;
    private String substatKey;

    public StatsWeapon() {
    }

    public StatsWeapon(String substatKey) {
        this.substatKey = substatKey;
    }

    public StatsWeapon(int ascension, double attack, int level, double specialized, String lvKey) {
        this.ascension = ascension;
        this.attack = attack;
        this.level = level;
        this.specialized = specialized;
        this.lvKey = lvKey;
    }

    public int getAscension() {
        return ascension;
    }

    public void setAscension(int ascension) {
        this.ascension = ascension;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getSpecialized() {
        return specialized;
    }

    public void setSpecialized(double specialized) {
        this.specialized = specialized;
    }

    public String getLvKey() {
        return lvKey;
    }

    public void setLvKey(String lvKey) {
        this.lvKey = lvKey;
    }

    public String getSubstatKey() {
        return substatKey;
    }

    public void setSubstatKey(String substatKey) {
        this.substatKey = substatKey;
    }
}
