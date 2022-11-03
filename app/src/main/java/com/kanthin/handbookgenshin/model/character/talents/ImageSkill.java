package com.kanthin.handbookgenshin.model.character.talents;

import java.io.Serializable;

public class ImageSkill implements Serializable {
    private String combat1;
    private String combat2;
    private String combat3;
    private String combatsp;
    private String passive1;
    private String passive2;
    private String passive3;

    public ImageSkill() {
    }

    public String getCombat1() {
        return combat1;
    }

    public void setCombat1(String combat1) {
        this.combat1 = combat1;
    }

    public String getCombat2() {
        return combat2;
    }

    public void setCombat2(String combat2) {
        this.combat2 = combat2;
    }

    public String getCombat3() {
        return combat3;
    }

    public void setCombat3(String combat3) {
        this.combat3 = combat3;
    }

    public String getCombatsp() {
        return combatsp;
    }

    public void setCombatsp(String combatsp) {
        this.combatsp = combatsp;
    }

    public String getPassive1() {
        return passive1;
    }

    public void setPassive1(String passive1) {
        this.passive1 = passive1;
    }

    public String getPassive2() {
        return passive2;
    }

    public void setPassive2(String passive2) {
        this.passive2 = passive2;
    }

    public String getPassive3() {
        return passive3;
    }

    public void setPassive3(String passive3) {
        this.passive3 = passive3;
    }
}
