package com.kanthin.handbookgenshin.model.index;

import com.kanthin.handbookgenshin.model.character.ImagesCharacter;

public class IndexCharacter {
    private String name;
    private ImagesCharacter images;
    private String rarity;
    private String weapontype;

    public IndexCharacter(String name) {
        this.name = name;
    }

    public IndexCharacter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImagesCharacter getImages() {
        return images;
    }

    public void setImages(ImagesCharacter images) {
        this.images = images;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getWeapontype() {
        return weapontype;
    }

    public void setWeapontype(String weapontype) {
        this.weapontype = weapontype;
    }
}
