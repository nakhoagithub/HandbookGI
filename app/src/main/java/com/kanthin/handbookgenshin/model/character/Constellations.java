package com.kanthin.handbookgenshin.model.character;

import java.io.Serializable;

public class Constellations implements Serializable {
    private Constellation c1;
    private Constellation c2;
    private Constellation c3;
    private Constellation c4;
    private Constellation c5;
    private Constellation c6;
    private ImageConstellation images;
    private String name;

    public Constellations() {
    }

    public Constellation getC1() {
        return c1;
    }

    public void setC1(Constellation c1) {
        this.c1 = c1;
    }

    public Constellation getC2() {
        return c2;
    }

    public void setC2(Constellation c2) {
        this.c2 = c2;
    }

    public Constellation getC3() {
        return c3;
    }

    public void setC3(Constellation c3) {
        this.c3 = c3;
    }

    public Constellation getC4() {
        return c4;
    }

    public void setC4(Constellation c4) {
        this.c4 = c4;
    }

    public Constellation getC5() {
        return c5;
    }

    public void setC5(Constellation c5) {
        this.c5 = c5;
    }

    public Constellation getC6() {
        return c6;
    }

    public void setC6(Constellation c6) {
        this.c6 = c6;
    }

    public ImageConstellation getImages() {
        return images;
    }

    public void setImages(ImageConstellation images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
