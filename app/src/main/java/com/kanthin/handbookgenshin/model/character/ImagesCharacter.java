package com.kanthin.handbookgenshin.model.character;

import java.io.Serializable;

public class ImagesCharacter implements Serializable {
    private String card;
    private String cover1;
    private String cover2;
    private String icon;
    private String image;
    private String portrait;
    private String nameicon;
    private String sideicon;
    private String namesideicon;
    private String namegachaslice;
    private String namegachasplash;
    private String nameiconcard;

    public ImagesCharacter() {
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCover1() {
        return cover1;
    }

    public void setCover1(String cover1) {
        this.cover1 = cover1;
    }

    public String getCover2() {
        return cover2;
    }

    public void setCover2(String cover2) {
        this.cover2 = cover2;
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

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNameicon() {
        return nameicon;
    }

    public void setNameicon(String nameicon) {
        this.nameicon = nameicon;
    }

    public String getSideicon() {
        return sideicon;
    }

    public void setSideicon(String sideicon) {
        this.sideicon = sideicon;
    }

    public String getNamesideicon() {
        return namesideicon;
    }

    public void setNamesideicon(String namesideicon) {
        this.namesideicon = namesideicon;
    }

    public String getNamegachaslice() {
        return namegachaslice;
    }

    public void setNamegachaslice(String namegachaslice) {
        this.namegachaslice = namegachaslice;
    }

    public String getNamegachasplash() {
        return namegachasplash;
    }

    public void setNamegachasplash(String namegachasplash) {
        this.namegachasplash = namegachasplash;
    }

    public String getNameiconcard() {
        return nameiconcard;
    }

    public void setNameiconcard(String nameiconcard) {
        this.nameiconcard = nameiconcard;
    }
}
