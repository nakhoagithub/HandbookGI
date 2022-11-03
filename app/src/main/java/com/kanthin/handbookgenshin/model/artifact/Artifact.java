package com.kanthin.handbookgenshin.model.artifact;

import java.io.Serializable;
import java.util.ArrayList;

public class Artifact implements Serializable {

    private Circlet circlet;
    private Flower flower;
    private Goblet goblet;
    private Plume plume;
    private Sands sands;
    private ImageArtifact images;
    private String name;
    private ArrayList<String> rarity;
    private String set1;
    private String set2;
    private String set4;

    public Artifact() {
    }

    public Circlet getCirclet() {
        return circlet;
    }

    public void setCirclet(Circlet circlet) {
        this.circlet = circlet;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Goblet getGoblet() {
        return goblet;
    }

    public void setGoblet(Goblet goblet) {
        this.goblet = goblet;
    }

    public Plume getPlume() {
        return plume;
    }

    public void setPlume(Plume plume) {
        this.plume = plume;
    }

    public Sands getSands() {
        return sands;
    }

    public void setSands(Sands sands) {
        this.sands = sands;
    }

    public ImageArtifact getImages() {
        return images;
    }

    public void setImages(ImageArtifact images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getRarity() {
        return rarity;
    }

    public void setRarity(ArrayList<String> rarity) {
        this.rarity = rarity;
    }

    public String getSet1() {
        return set1;
    }

    public void setSet1(String set1) {
        this.set1 = set1;
    }

    public String getSet2() {
        return set2;
    }

    public void setSet2(String set2) {
        this.set2 = set2;
    }

    public String getSet4() {
        return set4;
    }

    public void setSet4(String set4) {
        this.set4 = set4;
    }
}
