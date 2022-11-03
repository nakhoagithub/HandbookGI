package com.kanthin.handbookgenshin.presenter;

import android.content.Context;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.character.Character;
import com.kanthin.handbookgenshin.model.weapon.Weapon;

public class CustomStringLanguage {

    private final Context context;

    public CustomStringLanguage(Context context) {
        this.context = context;
    }

    //xử lý chỉ số của character
    public double handlerIndexSubStatsCharacter(Character character, double index) {
        if (character.getSubstat().equals(context.getResources().getString(R.string.elemental_mastery))) {
            index = (double) Math.round(index * 10) / 10;
        } else {
            index = (double) Math.round(index*100 * 10) / 10; // nếu cái substat != tinh thông nguyên tố thì nhân 100 để được %
        }
        return index;
    }

    public double handlerIndexSubStatsWeapon(Weapon weapon, double index) {
        if (weapon.getSubstat().equals(context.getResources().getString(R.string.elemental_mastery))) {
            index = (double) Math.round(index * 10) / 10;
        } else {
            index = (double) Math.round(index*100 * 10) / 10; // nếu cái substat != tinh thông nguyên tố thì nhân 100 để được %
        }
        return index;
    }

    //xử lý substat character
    public String handlerStringSubStats(String substats){
        if (substats.equals("")){
            return context.getResources().getString(R.string.not);
        }
        if (substats.equals(context.getResources().getString(R.string.elemental_mastery))){
            substats = substats + "";
        } else {
            substats = substats + " (%)";
        }
        return substats;
    }
}
