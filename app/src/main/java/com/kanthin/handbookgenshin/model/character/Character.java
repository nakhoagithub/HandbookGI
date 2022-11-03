package com.kanthin.handbookgenshin.model.character;

import com.kanthin.handbookgenshin.model.character.talents.Talents;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
    String affiliation;
    String association;
    String birthday;
    String birthdaymmdd;
    String body;
    String constellation;
    Constellations constellations;
    Costs costs;
    CVCharacter cv;
    String description;
    String element;
    String gender;
    ImagesCharacter images;
    String name;
    String rarity;
    String region;
    ArrayList<StatsCharacter> stats;
    String substat;
    Talents talents;
    String title;
    String weapontype;
    UrlCharacter url;

    public Character() {
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthdaymmdd() {
        return birthdaymmdd;
    }

    public void setBirthdaymmdd(String birthdaymmdd) {
        this.birthdaymmdd = birthdaymmdd;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public Constellations getConstellations() {
        return constellations;
    }

    public void setConstellations(Constellations constellations) {
        this.constellations = constellations;
    }

    public Costs getCosts() {
        return costs;
    }

    public void setCosts(Costs costs) {
        this.costs = costs;
    }

    public CVCharacter getCv() {
        return cv;
    }

    public void setCv(CVCharacter cv) {
        this.cv = cv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ImagesCharacter getImages() {
        return images;
    }

    public void setImages(ImagesCharacter images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ArrayList<StatsCharacter> getStats() {
        return stats;
    }

    public void setStats(ArrayList<StatsCharacter> stats) {
        this.stats = stats;
    }

    public String getSubstat() {
        return substat;
    }

    public void setSubstat(String substat) {
        this.substat = substat;
    }

    public Talents getTalents() {
        return talents;
    }

    public void setTalents(Talents talents) {
        this.talents = talents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeapontype() {
        return weapontype;
    }

    public void setWeapontype(String weapontype) {
        this.weapontype = weapontype;
    }

    public UrlCharacter getUrl() {
        return url;
    }

    public void setUrl(UrlCharacter url) {
        this.url = url;
    }
}
