package com.kanthin.handbookgenshin.presenter.filter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.character.CharacterAdapter;
import com.kanthin.handbookgenshin.model.character.Character;
import com.kanthin.handbookgenshin.presenter.anInterface.FilterInterface;

import java.util.ArrayList;

public class FilterCharacter {
    private final Context context;
    private final FilterInterface anInterface;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final TextView countItem;

    public FilterCharacter(Context context, FilterInterface anInterface, TextView countItem) {
        this.context = context;
        this.anInterface = anInterface;
        this.countItem = countItem;
        sharedPreferences = context.getSharedPreferences("filter_character", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //filter character
    @SuppressLint("NotifyDataSetChanged")
    public void dialogFilterCharacter(ArrayList<Character> arrayNotChange, ArrayList<Character> array, CharacterAdapter adapter) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_filter_character);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);

            dialog.setCancelable(false);

            TextView yes = dialog.findViewById(R.id.bt_yes_dialog);
            TextView no = dialog.findViewById(R.id.bt_no_dialog);
            TextView reset = dialog.findViewById(R.id.bt_reset_dialog);

            CheckBox cryo = dialog.findViewById(R.id.check_cryo);
            cryo.setChecked(sharedPreferences.getBoolean("cryo", false));

            CheckBox pyro = dialog.findViewById(R.id.check_pyro);
            pyro.setChecked(sharedPreferences.getBoolean("pyro", false));

            CheckBox hydro = dialog.findViewById(R.id.check_hydro);
            hydro.setChecked(sharedPreferences.getBoolean("hydro", false));

            CheckBox electro = dialog.findViewById(R.id.check_electro);
            electro.setChecked(sharedPreferences.getBoolean("electro", false));

            CheckBox geo = dialog.findViewById(R.id.check_geo);
            geo.setChecked(sharedPreferences.getBoolean("geo", false));

            CheckBox anemo = dialog.findViewById(R.id.check_anemo);
            anemo.setChecked(sharedPreferences.getBoolean("anemo", false));

            CheckBox dendro = dialog.findViewById(R.id.check_dendro);
            dendro.setChecked(sharedPreferences.getBoolean("dendro", false));

            CheckBox sword = dialog.findViewById(R.id.check_sword);
            sword.setChecked(sharedPreferences.getBoolean("sword", false));

            CheckBox claymore = dialog.findViewById(R.id.check_claymore);
            claymore.setChecked(sharedPreferences.getBoolean("claymore", false));

            CheckBox bow = dialog.findViewById(R.id.check_bow);
            bow.setChecked(sharedPreferences.getBoolean("bow", false));

            CheckBox polearm = dialog.findViewById(R.id.check_polearm);
            polearm.setChecked(sharedPreferences.getBoolean("polearm", false));

            CheckBox catalyst = dialog.findViewById(R.id.check_catalyst);
            catalyst.setChecked(sharedPreferences.getBoolean("catalyst", false));
            //
            CheckBox check_one_rarity = dialog.findViewById(R.id.check_one_rarity);
            check_one_rarity.setChecked(sharedPreferences.getBoolean("check_one_rarity", false));

            CheckBox rarity4s = dialog.findViewById(R.id.check_rarity_4s);
            rarity4s.setChecked(sharedPreferences.getBoolean("rarity4s", true));

            CheckBox rarity5s = dialog.findViewById(R.id.check_rarity_5s);
            rarity5s.setChecked(sharedPreferences.getBoolean("rarity5s", true));
            handlerCheckRarity(rarity4s, rarity5s);


            RadioButton sortAZ = dialog.findViewById(R.id.radio_az);
            sortAZ.setChecked(sharedPreferences.getBoolean("sortAZ", true));
            RadioButton sortZA = dialog.findViewById(R.id.radio_za);
            sortZA.setChecked(sharedPreferences.getBoolean("sortZA", false));

            yes.setOnClickListener(v1 -> {
                if (!cryo.isChecked() && !pyro.isChecked() && !hydro.isChecked() && !electro.isChecked() && !geo.isChecked()
                        && !anemo.isChecked() && !dendro.isChecked() && !sword.isChecked() &&
                        !claymore.isChecked() && !bow.isChecked() && !polearm.isChecked() &&
                        !catalyst.isChecked()) {
                    Toast.makeText(context, context.getResources().getString(R.string.choose_filter_character), Toast.LENGTH_SHORT).show();
                } else {
                    filterCharacter(arrayNotChange, array, adapter, cryo, pyro, hydro, electro, geo, anemo, dendro,
                            sword, claymore, bow, polearm, catalyst, check_one_rarity, rarity4s, rarity5s, sortAZ, sortZA);
                    dialog.dismiss();
                }
            });

            no.setOnClickListener(v1 -> dialog.dismiss());

            reset.setOnClickListener(v1 -> {
                reset(cryo, pyro, hydro, electro, geo, anemo, dendro,
                        sword, claymore, bow, polearm, catalyst, check_one_rarity, rarity4s, rarity5s, sortAZ, sortZA);
                array.clear();
                array.addAll(arrayNotChange);
                countItem.setText(String.valueOf(array.size()));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            });
        }
        dialog.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filterCharacter(ArrayList<Character> arrayNotChange, ArrayList<Character> array, CharacterAdapter adapter, CheckBox cryo,
                                 CheckBox pyro, CheckBox hydro, CheckBox electro, CheckBox geo, CheckBox anemo,
                                 CheckBox dendro, CheckBox sword, CheckBox claymore, CheckBox bow, CheckBox polearm,
                                 CheckBox catalyst, CheckBox check_one_rarity, CheckBox rarity4s, CheckBox rarity5s, RadioButton sortAZ, RadioButton sortZA) {
        new Handler(Looper.getMainLooper()).post(() -> {
            ArrayList<Character> arrayFilter = new ArrayList<>(arrayNotChange);
            if (cryo.isChecked() || pyro.isChecked() || hydro.isChecked() || electro.isChecked() || geo.isChecked() || anemo.isChecked() || dendro.isChecked()){
                //băng
                if (!cryo.isChecked()) {
                    editor.putBoolean("cryo", false);
                    arrayFilter.removeIf(character -> character.getElement().contains(context.getString(R.string.cryo)));
                } else editor.putBoolean("cryo", true);
                //hỏa
                if (!pyro.isChecked()) {
                    editor.putBoolean("pyro", false);
                    arrayFilter.removeIf(character -> character.getElement().contains(context.getString(R.string.pyro)));
                } else editor.putBoolean("pyro", true);
                //thủy
                if (!hydro.isChecked()) {
                    editor.putBoolean("hydro", false);
                    arrayFilter.removeIf(character -> character.getElement().contains(context.getString(R.string.hydro)));
                } else editor.putBoolean("hydro", true);
                //lôi
                if (!electro.isChecked()) {
                    editor.putBoolean("electro", false);
                    arrayFilter.removeIf(character -> character.getElement().contains(context.getString(R.string.electro)));
                } else editor.putBoolean("electro", true);
                //nham
                if (!geo.isChecked()) {
                    editor.putBoolean("geo", false);
                    arrayFilter.removeIf(character -> character.getElement().contains(context.getString(R.string.geo)));
                } else editor.putBoolean("geo", true);
                //phong
                if (!anemo.isChecked()) {
                    editor.putBoolean("anemo", false);
                    arrayFilter.removeIf(character -> character.getElement().contains(context.getString(R.string.anemo)));
                } else editor.putBoolean("anemo", true);
                //thảo
                if (!dendro.isChecked()) {
                    editor.putBoolean("dendro", false);
                    arrayFilter.removeIf(character -> character.getElement().contains(context.getString(R.string.dendro)));
                } else editor.putBoolean("dendro", true);
            }else {
                resetElement(cryo, pyro, hydro, electro, geo, anemo, dendro);
            }

            if (sword.isChecked() || claymore.isChecked() || bow.isChecked() || polearm.isChecked() || catalyst.isChecked()){
                //kiếm đơn
                if (!sword.isChecked()) {
                    editor.putBoolean("sword", false);
                    arrayFilter.removeIf(character -> character.getWeapontype().contains(context.getString(R.string.sword)));
                } else editor.putBoolean("sword", true);
                //trọng kiếm
                if (!claymore.isChecked()) {
                    editor.putBoolean("claymore", false);
                    arrayFilter.removeIf(character -> character.getWeapontype().contains(context.getString(R.string.claymore)));
                } else editor.putBoolean("claymore", true);
                //cung
                if (!bow.isChecked()) {
                    editor.putBoolean("bow", false);
                    arrayFilter.removeIf(character -> character.getWeapontype().contains(context.getString(R.string.bow)));
                } else editor.putBoolean("bow", true);
                //vũ khí cán dài
                if (!polearm.isChecked()) {
                    editor.putBoolean("polearm", false);
                    arrayFilter.removeIf(character -> character.getWeapontype().contains(context.getString(R.string.polearm)));
                } else editor.putBoolean("polearm", true);
                //pháp khí
                if (!catalyst.isChecked()) {
                    editor.putBoolean("catalyst", false);
                    arrayFilter.removeIf(character -> character.getWeapontype().contains(context.getString(R.string.catalyst)));
                } else editor.putBoolean("catalyst", true);
            } else {
                resetWeapon(sword, claymore, bow, polearm, catalyst);
            }

            if (check_one_rarity.isChecked()) {
                editor.putBoolean("check_one_rarity", true);
                if (rarity5s.isChecked()) {
                    arrayFilter.removeIf(weapon -> weapon.getRarity().contains("1"));
                    arrayFilter.removeIf(weapon -> weapon.getRarity().contains("2"));
                    arrayFilter.removeIf(weapon -> weapon.getRarity().contains("3"));
                    arrayFilter.removeIf(weapon -> weapon.getRarity().contains("4"));
                } else {
                    if (rarity4s.isChecked()) {
                        arrayFilter.removeIf(weapon -> weapon.getRarity().contains("1"));
                        arrayFilter.removeIf(weapon -> weapon.getRarity().contains("2"));
                        arrayFilter.removeIf(weapon -> weapon.getRarity().contains("3"));
                        arrayFilter.removeIf(weapon -> weapon.getRarity().contains("5"));
                    }
                }
            } else editor.putBoolean("check_one_rarity", false);
            //4 sao
            if (!rarity4s.isChecked())
                arrayFilter.removeIf(character -> character.getRarity().contains("4"));
            //5 sao
            if (!rarity5s.isChecked())
                arrayFilter.removeIf(character -> character.getRarity().contains("5"));
            editor.putBoolean("rarity4s", rarity4s.isChecked());
            editor.putBoolean("rarity5s", rarity5s.isChecked());
            //sắp xếp
            if (sortAZ.isChecked()) {
                editor.putBoolean("sortAZ", true);
                arrayFilter.sort((artifact, t1) -> ComparisonChain.start()
                        .compare(artifact.getName(), t1.getName(), Ordering.natural().nullsFirst())
                        .result());
            } else editor.putBoolean("sortAZ", false);
            if (sortZA.isChecked()) {
                editor.putBoolean("sortZA", true);
                arrayFilter.sort((artifact, t1) -> ComparisonChain.start()
                        .compare(t1.getName(), artifact.getName(), Ordering.natural().nullsFirst())
                        .result());
            } else editor.putBoolean("sortZA", false);

            if (arrayFilter.size() == 0) {
                anInterface.nullFilter();
            }
            editor.apply();
            array.clear();
            array.addAll(arrayFilter);
            countItem.setText(String.valueOf(array.size()));
            adapter.notifyDataSetChanged();
        });
    }

    private void reset(CheckBox cryo, CheckBox pyro, CheckBox hydro, CheckBox electro, CheckBox geo, CheckBox anemo,
                       CheckBox dendro, CheckBox sword, CheckBox claymore, CheckBox bow, CheckBox polearm,
                       CheckBox catalyst, CheckBox check_one_rarity, CheckBox rarity4s, CheckBox rarity5s, RadioButton sortAZ, RadioButton sortZA) {
        cryo.setChecked(false);
        editor.putBoolean("cryo", false);
        pyro.setChecked(false);
        editor.putBoolean("pyro", false);
        hydro.setChecked(false);
        editor.putBoolean("hydro", false);
        electro.setChecked(false);
        editor.putBoolean("electro", false);
        geo.setChecked(false);
        editor.putBoolean("geo", false);
        anemo.setChecked(false);
        editor.putBoolean("anemo", false);
        dendro.setChecked(false);
        editor.putBoolean("dendro", false);

        sword.setChecked(false);
        editor.putBoolean("sword", false);
        claymore.setChecked(false);
        editor.putBoolean("claymore", false);
        bow.setChecked(false);
        editor.putBoolean("bow", false);
        polearm.setChecked(false);
        editor.putBoolean("polearm", false);
        catalyst.setChecked(false);
        editor.putBoolean("catalyst", false);

        check_one_rarity.setChecked(false);
        editor.putBoolean("check_one_rarity", false);
        rarity4s.setChecked(true);
        editor.putBoolean("rarity4s", true);
        rarity5s.setChecked(true);
        editor.putBoolean("rarity5s", true);

        sortAZ.setChecked(true);
        editor.putBoolean("sortAZ", true);
        sortZA.setChecked(false);
        editor.putBoolean("sortZA", false);

        editor.apply();
    }

    private void resetElement(CheckBox cryo, CheckBox pyro, CheckBox hydro, CheckBox electro, CheckBox geo, CheckBox anemo,
                              CheckBox dendro){
        cryo.setChecked(false);
        editor.putBoolean("cryo", false);
        pyro.setChecked(false);
        editor.putBoolean("pyro", false);
        hydro.setChecked(false);
        editor.putBoolean("hydro", false);
        electro.setChecked(false);
        editor.putBoolean("electro", false);
        geo.setChecked(false);
        editor.putBoolean("geo", false);
        anemo.setChecked(false);
        editor.putBoolean("anemo", false);
        dendro.setChecked(false);
        editor.putBoolean("dendro", false);
    }

    private void resetWeapon(CheckBox sword, CheckBox claymore, CheckBox bow, CheckBox polearm,
                             CheckBox catalyst){
        sword.setChecked(false);
        editor.putBoolean("sword", false);
        claymore.setChecked(false);
        editor.putBoolean("claymore", false);
        bow.setChecked(false);
        editor.putBoolean("bow", false);
        polearm.setChecked(false);
        editor.putBoolean("polearm", false);
        catalyst.setChecked(false);
        editor.putBoolean("catalyst", false);
    }

    private void handlerCheckRarity(CheckBox check4, CheckBox check5) {
        check4.setOnClickListener(view -> {
            check4.setChecked(true);
            check5.setChecked(false);
        });
        check5.setOnClickListener(view -> {
            check4.setChecked(true);
            check5.setChecked(true);
        });
    }
}
