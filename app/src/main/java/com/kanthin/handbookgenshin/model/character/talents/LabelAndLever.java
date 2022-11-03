package com.kanthin.handbookgenshin.model.character.talents;

import java.util.ArrayList;

public class LabelAndLever {
    private String label;
    private ArrayList<String> param;

    public LabelAndLever(String label, ArrayList<String> param) {
        this.label = label;
        this.param = param;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<String> getParam() {
        return param;
    }

    public void setParam(ArrayList<String> param) {
        this.param = param;
    }
}
