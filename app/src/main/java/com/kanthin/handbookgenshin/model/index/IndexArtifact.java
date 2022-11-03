package com.kanthin.handbookgenshin.model.index;

import com.kanthin.handbookgenshin.model.artifact.ImageArtifact;

import java.util.ArrayList;

public class IndexArtifact {
    private String en;
    private String vi;
    private ImageArtifact images;
    private ArrayList<String> rarity;

    public IndexArtifact() {
    }

    public IndexArtifact(String en, String vi) {
        this.en = en;
        this.vi = vi;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }

    public ImageArtifact getImages() {
        return images;
    }

    public void setImages(ImageArtifact images) {
        this.images = images;
    }

    public ArrayList<String> getRarity() {
        return rarity;
    }

    public void setRarity(ArrayList<String> rarity) {
        this.rarity = rarity;
    }
}
