package com.kanthin.handbookgenshin.model.weapon;

import com.kanthin.handbookgenshin.model.character.Costs;

import java.io.Serializable;
import java.util.ArrayList;

public class Weapon implements Serializable {
    int baseatk;
    Costs costs;
    String description;
    String effect;
    String effectname;
    ImageWeapon images;
    String name;
    ArrayList<String> r1;
    ArrayList<String> r2;
    ArrayList<String> r3;
    ArrayList<String> r4;
    ArrayList<String> r5;
    String rarity;
    ArrayList<StatsWeapon> stats;
    String substat;
    String subvalue;
    String weapontype;
    private UrlWeapon url;

    public Weapon() {
    }

    public long getBaseatk() {
        return baseatk;
    }

    public void setBaseatk(int baseatk) {
        this.baseatk = baseatk;
    }

    public Costs getCosts() {
        return costs;
    }

    public void setCosts(Costs costs) {
        this.costs = costs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getEffectname() {
        return effectname;
    }

    public void setEffectname(String effectname) {
        this.effectname = effectname;
    }

    public ImageWeapon getImages() {
        return images;
    }

    public void setImages(ImageWeapon images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getR1() {
        return r1;
    }

    public void setR1(ArrayList<String> r1) {
        this.r1 = r1;
    }

    public ArrayList<String> getR2() {
        return r2;
    }

    public void setR2(ArrayList<String> r2) {
        this.r2 = r2;
    }

    public ArrayList<String> getR3() {
        return r3;
    }

    public void setR3(ArrayList<String> r3) {
        this.r3 = r3;
    }

    public ArrayList<String> getR4() {
        return r4;
    }

    public void setR4(ArrayList<String> r4) {
        this.r4 = r4;
    }

    public ArrayList<String> getR5() {
        return r5;
    }

    public void setR5(ArrayList<String> r5) {
        this.r5 = r5;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public ArrayList<StatsWeapon> getStats() {
        return stats;
    }

    public void setStats(ArrayList<StatsWeapon> stats) {
        this.stats = stats;
    }

    public String getSubstat() {
        return substat;
    }

    public void setSubstat(String substat) {
        this.substat = substat;
    }

    public String getSubvalue() {
        return subvalue;
    }

    public void setSubvalue(String subvalue) {
        this.subvalue = subvalue;
    }

    public String getWeapontype() {
        return weapontype;
    }

    public void setWeapontype(String weapontype) {
        this.weapontype = weapontype;
    }

    public UrlWeapon getUrl() {
        return url;
    }

    public void setUrl(UrlWeapon url) {
        this.url = url;
    }
}
