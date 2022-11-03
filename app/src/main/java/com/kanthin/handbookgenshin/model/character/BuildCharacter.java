package com.kanthin.handbookgenshin.model.character;

public class BuildCharacter {
    private String name;
    private String weapon;
    private String set2First;
    private String set2Second;
    private String set4;
    private String sand;
    private String goblet;
    private String circlet;
    private String description;

    public BuildCharacter() {
    }

    public BuildCharacter(String name, String weapon, String set2First, String set2Second, String sand, String goblet, String circlet, String description) {
        this.name = name;
        this.weapon = weapon;
        this.set2First = set2First;
        this.set2Second = set2Second;
        this.sand = sand;
        this.goblet = goblet;
        this.circlet = circlet;
        this.description = description;
    }

    public BuildCharacter(String name, String weapon, String set4, String sand, String goblet, String circlet, String description) {
        this.name = name;
        this.weapon = weapon;
        this.set4 = set4;
        this.sand = sand;
        this.goblet = goblet;
        this.circlet = circlet;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getSet2First() {
        return set2First;
    }

    public void setSet2First(String set2First) {
        this.set2First = set2First;
    }

    public String getSet2Second() {
        return set2Second;
    }

    public void setSet2Second(String set2Second) {
        this.set2Second = set2Second;
    }

    public String getSet4() {
        return set4;
    }

    public void setSet4(String set4) {
        this.set4 = set4;
    }

    public String getSand() {
        return sand;
    }

    public void setSand(String sand) {
        this.sand = sand;
    }

    public String getGoblet() {
        return goblet;
    }

    public void setGoblet(String goblet) {
        this.goblet = goblet;
    }

    public String getCirclet() {
        return circlet;
    }

    public void setCirclet(String circlet) {
        this.circlet = circlet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
