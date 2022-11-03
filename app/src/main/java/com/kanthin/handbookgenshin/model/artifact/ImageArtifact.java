package com.kanthin.handbookgenshin.model.artifact;

import java.io.Serializable;

public class ImageArtifact implements Serializable {
    private String circlet;
    private String flower;
    private String goblet;
    private String plume;
    private String sands;

    public ImageArtifact() {
    }

    public String getCirclet() {
        return circlet;
    }

    public void setCirclet(String circlet) {
        this.circlet = circlet;
    }

    public String getFlower() {
        return flower;
    }

    public void setFlower(String flower) {
        this.flower = flower;
    }

    public String getGoblet() {
        return goblet;
    }

    public void setGoblet(String goblet) {
        this.goblet = goblet;
    }

    public String getPlume() {
        return plume;
    }

    public void setPlume(String plume) {
        this.plume = plume;
    }

    public String getSands() {
        return sands;
    }

    public void setSands(String sands) {
        this.sands = sands;
    }
}
