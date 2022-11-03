package com.kanthin.handbookgenshin.model.character;

import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;

import java.io.Serializable;
import java.util.ArrayList;

public class Costs implements Serializable {
    private ArrayList<ItemMaterial> ascend1;
    private ArrayList<ItemMaterial> ascend2;
    private ArrayList<ItemMaterial> ascend3;
    private ArrayList<ItemMaterial> ascend4;
    private ArrayList<ItemMaterial> ascend5;
    private ArrayList<ItemMaterial> ascend6;
    private ImageConstellation images;

    public Costs() {
    }

    public ArrayList<ItemMaterial> getAscend1() {
        return ascend1;
    }

    public void setAscend1(ArrayList<ItemMaterial> ascend1) {
        this.ascend1 = ascend1;
    }

    public ArrayList<ItemMaterial> getAscend2() {
        return ascend2;
    }

    public void setAscend2(ArrayList<ItemMaterial> ascend2) {
        this.ascend2 = ascend2;
    }

    public ArrayList<ItemMaterial> getAscend3() {
        return ascend3;
    }

    public void setAscend3(ArrayList<ItemMaterial> ascend3) {
        this.ascend3 = ascend3;
    }

    public ArrayList<ItemMaterial> getAscend4() {
        return ascend4;
    }

    public void setAscend4(ArrayList<ItemMaterial> ascend4) {
        this.ascend4 = ascend4;
    }

    public ArrayList<ItemMaterial> getAscend5() {
        return ascend5;
    }

    public void setAscend5(ArrayList<ItemMaterial> ascend5) {
        this.ascend5 = ascend5;
    }

    public ArrayList<ItemMaterial> getAscend6() {
        return ascend6;
    }

    public void setAscend6(ArrayList<ItemMaterial> ascend6) {
        this.ascend6 = ascend6;
    }

    public ImageConstellation getImages() {
        return images;
    }

    public void setImages(ImageConstellation images) {
        this.images = images;
    }
}
