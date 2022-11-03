package com.kanthin.handbookgenshin.model.artifact;

import java.io.Serializable;

public class Sands implements Serializable {
    private String description;
    private String name;
    private String relictype;

    public Sands() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelictype() {
        return relictype;
    }

    public void setRelictype(String relictype) {
        this.relictype = relictype;
    }
}
