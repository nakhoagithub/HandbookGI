package com.kanthin.handbookgenshin.model.character.talents;

import java.io.Serializable;
import java.util.ArrayList;

public class Attributes implements Serializable {
    private ArrayList<String> labels;
    private Parameters parameters;

    public Attributes() {
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
