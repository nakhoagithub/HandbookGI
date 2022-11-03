package com.kanthin.handbookgenshin.model.material;

import java.io.Serializable;

public class ImageMaterial implements Serializable {
    private String fandom;
    private String redirect;
    private String nameicon;

    public ImageMaterial() {
    }

    public String getFandom() {
        return fandom;
    }

    public void setFandom(String fandom) {
        this.fandom = fandom;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getNameicon() {
        return nameicon;
    }

    public void setNameicon(String nameicon) {
        this.nameicon = nameicon;
    }
}
