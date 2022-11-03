package com.kanthin.handbookgenshin.model.domain;

import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;

import java.util.ArrayList;

public class LevelDomain {
    private ArrayList<String> disorder;
    private ImageDomain images;
    private String name;
    private ArrayList<String> recommendedelements;
    private int recommendedlevel;
    private ArrayList<ItemMaterial> rewardpreview;
    private int unlockrank;

    public LevelDomain() {
    }

    public ArrayList<String> getDisorder() {
        return disorder;
    }

    public void setDisorder(ArrayList<String> disorder) {
        this.disorder = disorder;
    }

    public ImageDomain getImages() {
        return images;
    }

    public void setImages(ImageDomain images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getRecommendedelements() {
        return recommendedelements;
    }

    public void setRecommendedelements(ArrayList<String> recommendedelements) {
        this.recommendedelements = recommendedelements;
    }

    public int getRecommendedlevel() {
        return recommendedlevel;
    }

    public void setRecommendedlevel(int recommendedlevel) {
        this.recommendedlevel = recommendedlevel;
    }

    public ArrayList<ItemMaterial> getRewardpreview() {
        return rewardpreview;
    }

    public void setRewardpreview(ArrayList<ItemMaterial> rewardpreview) {
        this.rewardpreview = rewardpreview;
    }

    public int getUnlockrank() {
        return unlockrank;
    }

    public void setUnlockrank(int unlockrank) {
        this.unlockrank = unlockrank;
    }
}
