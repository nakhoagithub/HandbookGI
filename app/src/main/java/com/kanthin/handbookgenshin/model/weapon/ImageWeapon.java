package com.kanthin.handbookgenshin.model.weapon;

import java.io.Serializable;

public class ImageWeapon implements Serializable {
    private String awakenicon;
    private String icon;
    private String image;
    private String nameawakenicon;
    private String namegacha;
    private String nameicon;

    public ImageWeapon() {
    }

    public String getAwakenicon() {
        return awakenicon;
    }

    public void setAwakenicon(String awakenicon) {
        this.awakenicon = awakenicon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameawakenicon() {
        return nameawakenicon;
    }

    public void setNameawakenicon(String nameawakenicon) {
        this.nameawakenicon = nameawakenicon;
    }

    public String getNamegacha() {
        return namegacha;
    }

    public void setNamegacha(String namegacha) {
        this.namegacha = namegacha;
    }

    public String getNameicon() {
        return nameicon;
    }

    public void setNameicon(String nameicon) {
        this.nameicon = nameicon;
    }
}
