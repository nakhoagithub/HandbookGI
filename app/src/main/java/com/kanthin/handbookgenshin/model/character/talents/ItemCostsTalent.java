package com.kanthin.handbookgenshin.model.character.talents;

import java.util.ArrayList;

public class ItemCostsTalent {
    private String lv;
    private ArrayList<ItemMaterial> array;

    public ItemCostsTalent() {
    }

    //class biểu thị mỗi lv có các nguyên liệu up talent character
    public ItemCostsTalent(String lv, ArrayList<ItemMaterial> array) {
        this.lv = lv;
        this.array = array;
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
