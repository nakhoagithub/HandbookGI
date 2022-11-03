package com.kanthin.handbookgenshin.model.character.talents;

import java.io.Serializable;

public class ItemMaterial implements Serializable {
    private String name;
    private int count;
    private String rarity;

    public ItemMaterial() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
