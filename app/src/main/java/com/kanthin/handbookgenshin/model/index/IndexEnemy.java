package com.kanthin.handbookgenshin.model.index;

import com.kanthin.handbookgenshin.model.enemy.ImageEnemy;

public class IndexEnemy {
    private String vi;
    private String en;
    private ImageEnemy images;

    public IndexEnemy() {
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public ImageEnemy getImages() {
        return images;
    }

    public void setImages(ImageEnemy images) {
        this.images = images;
    }
}
