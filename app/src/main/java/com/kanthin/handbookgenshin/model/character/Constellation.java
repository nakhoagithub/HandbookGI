package com.kanthin.handbookgenshin.model.character;

import java.io.Serializable;

public class Constellation implements Serializable {

    private String name;
    private String image;
    private String effect;

    public Constellation() {
    }

    public Constellation(String name, String effect, String image) {
        this.name = name;
        this.image = image;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
