package com.kanthin.handbookgenshin.model.domain;

import java.util.ArrayList;

public class Domain {
    private String name;
    private ArrayList<String> daysofweek;
    private String description;
    private String domainentrance;
    private String domaintype;
    private String region;
    private ArrayList<LevelDomain> domainLvs;
    private ArrayList<String> monsterlist;

    public Domain() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDaysofweek() {
        return daysofweek;
    }

    public void setDaysofweek(ArrayList<String> daysofweek) {
        this.daysofweek = daysofweek;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomainentrance() {
        return domainentrance;
    }

    public void setDomainentrance(String domainentrance) {
        this.domainentrance = domainentrance;
    }

    public String getDomaintype() {
        return domaintype;
    }

    public void setDomaintype(String domaintype) {
        this.domaintype = domaintype;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ArrayList<LevelDomain> getDomainLvs() {
        return domainLvs;
    }

    public void setDomainLvs(ArrayList<LevelDomain> domainLvs) {
        this.domainLvs = domainLvs;
    }

    public ArrayList<String> getMonsterlist() {
        return monsterlist;
    }

    public void setMonsterlist(ArrayList<String> monsterlist) {
        this.monsterlist = monsterlist;
    }
}
