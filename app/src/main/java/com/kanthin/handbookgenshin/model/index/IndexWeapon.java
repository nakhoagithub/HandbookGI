package com.kanthin.handbookgenshin.model.index;

import com.kanthin.handbookgenshin.model.weapon.ImageWeapon;

public class IndexWeapon {
    private String en;
    private String vi;
    private ImageWeapon images;
    private String rarity;
    private String weapontype;

    public IndexWeapon(String en, String vi, String weapontype) {
        this.en = en;
        this.vi = vi;
        this.weapontype = weapontype;
    }

    public IndexWeapon() {
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

    public ImageWeapon getImages() {
        return images;
    }

    public void setImages(ImageWeapon images) {
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
