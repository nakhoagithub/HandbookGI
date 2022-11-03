package com.kanthin.handbookgenshin.model.character;

import java.io.Serializable;

public class StatsCharacter implements Serializable {
    private int ascension;
    private double attack;
    private double defense;
    private double hp;
    private int level;
    private double specialized;
    private String lvKey;
    private String substatKey;

    public StatsCharacter() {
    }

    public StatsCharacter(String substatKey) {
        this.substatKey = substatKey;
    }

    public StatsCharacter(int ascension, double attack, double defense, double hp, int level, double specialized, String lvKey) {
        this.ascension = ascension;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
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

    public double getDefense() {
        return defense;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
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
