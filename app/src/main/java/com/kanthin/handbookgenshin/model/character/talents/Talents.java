package com.kanthin.handbookgenshin.model.character.talents;

import java.io.Serializable;

public class Talents implements Serializable {
    private Skill combat1;
    private Skill combat2;
    private Skill combat3;
    private Skill combatsp;
    private CostsTalents costs;
    private ImageSkill images;
    private Skill passive1;
    private Skill passive2;
    private Skill passive3;
    private String name;

    public Talents() {
    }

    public Skill getCombat1() {
        return combat1;
    }

    public void setCombat1(Skill combat1) {
        this.combat1 = combat1;
    }

    public Skill getCombat2() {
        return combat2;
    }

    public void setCombat2(Skill combat2) {
        this.combat2 = combat2;
    }

    public Skill getCombat3() {
        return combat3;
    }

    public void setCombat3(Skill combat3) {
        this.combat3 = combat3;
    }

    public Skill getCombatsp() {
        return combatsp;
    }

    public void setCombatsp(Skill combatsp) {
        this.combatsp = combatsp;
    }

    public CostsTalents getCosts() {
        return costs;
    }

    public void setCosts(CostsTalents costsTalents) {
        this.costs = costsTalents;
    }

    public ImageSkill getImages() {
        return images;
    }

    public void setImages(ImageSkill images) {
        this.images = images;
    }

    public Skill getPassive1() {
        return passive1;
    }

    public void setPassive1(Skill passive1) {
        this.passive1 = passive1;
    }

    public Skill getPassive2() {
        return passive2;
    }

    public void setPassive2(Skill passive2) {
        this.passive2 = passive2;
    }

    public Skill getPassive3() {
        return passive3;
    }

    public void setPassive3(Skill passive3) {
        this.passive3 = passive3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
