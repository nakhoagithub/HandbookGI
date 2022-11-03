package com.kanthin.handbookgenshin.model.character;

import java.io.Serializable;

public class UrlCharacter implements Serializable {
    private String fandom;

    public UrlCharacter() {
    }

    public String getFandom() {
        return fandom;
    }

    public void setFandom(String fandom) {
        this.fandom = fandom;
    }
}
