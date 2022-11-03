package com.kanthin.handbookgenshin.model.character.talents;

import java.io.Serializable;
import java.util.ArrayList;

public class CostsTalents implements Serializable {
    private ArrayList<ItemMaterial> lvl2;
    private ArrayList<ItemMaterial> lvl3;
    private ArrayList<ItemMaterial> lvl4;
    private ArrayList<ItemMaterial> lvl5;
    private ArrayList<ItemMaterial> lvl6;
    private ArrayList<ItemMaterial> lvl7;
    private ArrayList<ItemMaterial> lvl8;
    private ArrayList<ItemMaterial> lvl9;
    private ArrayList<ItemMaterial> lvl10;

    public CostsTalents() {
    }

    public ArrayList<ItemMaterial> getLvl2() {
        return lvl2;
    }

    public void setLvl2(ArrayList<ItemMaterial> lvl2) {
        this.lvl2 = lvl2;
    }

    public ArrayList<ItemMaterial> getLvl3() {
        return lvl3;
    }

    public void setLvl3(ArrayList<ItemMaterial> lvl3) {
        this.lvl3 = lvl3;
    }

    public ArrayList<ItemMaterial> getLvl4() {
        return lvl4;
    }

    public void setLvl4(ArrayList<ItemMaterial> lvl4) {
        this.lvl4 = lvl4;
    }

    public ArrayList<ItemMaterial> getLvl5() {
        return lvl5;
    }

    public void setLvl5(ArrayList<ItemMaterial> lvl5) {
        this.lvl5 = lvl5;
    }

    public ArrayList<ItemMaterial> getLvl6() {
        return lvl6;
    }

    public void setLvl6(ArrayList<ItemMaterial> lvl6) {
        this.lvl6 = lvl6;
    }

    public ArrayList<ItemMaterial> getLvl7() {
        return lvl7;
    }

    public void setLvl7(ArrayList<ItemMaterial> lvl7) {
        this.lvl7 = lvl7;
    }

    public ArrayList<ItemMaterial> getLvl8() {
        return lvl8;
    }

    public void setLvl8(ArrayList<ItemMaterial> lvl8) {
        this.lvl8 = lvl8;
    }

    public ArrayList<ItemMaterial> getLvl9() {
        return lvl9;
    }

    public void setLvl9(ArrayList<ItemMaterial> lvl9) {
        this.lvl9 = lvl9;
    }

    public ArrayList<ItemMaterial> getLvl10() {
        return lvl10;
    }

    public void setLvl10(ArrayList<ItemMaterial> lvl10) {
        this.lvl10 = lvl10;
    }
}
