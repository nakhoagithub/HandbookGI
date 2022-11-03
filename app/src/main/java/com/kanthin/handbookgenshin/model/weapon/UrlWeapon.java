package com.kanthin.handbookgenshin.model.weapon;

import java.io.Serializable;

public class UrlWeapon implements Serializable {
    private String fandom;

    public UrlWeapon() {
    }

    public String getFandom() {
        return fandom;
    }

    public void setFandom(String fandom) {
        this.fandom = fandom;
    }
}
