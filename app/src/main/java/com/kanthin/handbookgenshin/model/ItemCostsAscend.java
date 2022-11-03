package com.kanthin.handbookgenshin.model;

import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;

import java.util.ArrayList;

public class ItemCostsAscend {
    private String ascend;
    private String lv;
    private ArrayList<ItemMaterial> array;

    public ItemCostsAscend() {
    }

    public ItemCostsAscend(String ascend, String lv, ArrayList<ItemMaterial> array) {
        this.ascend = ascend;
        this.lv = lv;
        this.array = array;
    }

    public String getAscend() {
        return ascend;
    }

    public void setAscend(String ascend) {
        this.ascend = ascend;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public ArrayList<ItemMaterial> getArray() {
        return array;
    }

    public void setArray(ArrayList<ItemMaterial> array) {
        this.array = array;
    }
}
