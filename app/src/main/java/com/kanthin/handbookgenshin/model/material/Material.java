package com.kanthin.handbookgenshin.model.material;

import java.io.Serializable;
import java.util.ArrayList;

public class Material implements Serializable {
    private String category;
    private ArrayList<String> daysofweek;
    private String description;
    private String dropdomain;
    private ImageMaterial images;
    private String materialtype;
    private String name;
    private String rarity;
    private ArrayList<String> source;
    private int sortorder;

    public Material() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getDropdomain() {
        return dropdomain;
    }

    public void setDropdomain(String dropdomain) {
        this.dropdomain = dropdomain;
    }

    public ImageMaterial getImages() {
        return images;
    }

    public void setImages(ImageMaterial images) {
        this.images = images;
    }

    public String getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(String materialtype) {
        this.materialtype = materialtype;
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

    public ArrayList<String> getSource() {
        return source;
    }

    public void setSource(ArrayList<String> source) {
        this.source = source;
    }

    public int getSortorder() {
        return sortorder;
    }

    public void setSortorder(int sortorder) {
        this.sortorder = sortorder;
    }
}
