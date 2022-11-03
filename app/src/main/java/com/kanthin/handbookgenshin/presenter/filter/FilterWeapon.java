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
import com.kanthin.handbookgenshin.adapter.weapon.WeaponAdapter;
import com.kanthin.handbookgenshin.model.weapon.Weapon;
import com.kanthin.handbookgenshin.presenter.anInterface.FilterInterface;

import java.util.ArrayList;

public class FilterWeapon {
    private final Context context;
    private final FilterInterface anInterface;
    private TextView countItem;

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public FilterWeapon(Context context, FilterInterface anInterface, TextView countItem) {
        this.context = context;
        this.anInterface = anInterface;
        this.countItem = countItem;
        sharedPreferences = context.getSharedPreferences("filter_weapon", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void dialogFilterWeapon(ArrayList<Weapon> arrayNotChange, ArrayList<Weapon> array, WeaponAdapter adapter) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_filter_weapon);

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
            CheckBox not_substat = dialog.findViewById(R.id.check_not_substat);
            not_substat.setChecked(sharedPreferences.getBoolean("not_substat", false));

            CheckBox attack = dialog.findViewById(R.id.check_attack);
            attack.setChecked(sharedPreferences.getBoolean("attack", false));

            CheckBox elemental_mastery = dialog.findViewById(R.id.check_elemental_mastery);
            elemental_mastery.setChecked(sharedPreferences.getBoolean("elemental_mastery", false));

            CheckBox crit_rate = dialog.findViewById(R.id.check_crit_rate);
            crit_rate.setChecked(sharedPreferences.getBoolean("crit_rate", false));

            CheckBox crit_dame = dialog.findViewById(R.id.check_crit_dame);
            crit_dame.setChecked(sharedPreferences.getBoolean("crit_dame", false));

            CheckBox energy_recharge = dialog.findViewById(R.id.check_energy_recharge);
            energy_recharge.setChecked(sharedPreferences.getBoolean("energy_recharge", false));

            CheckBox dame_physical = dialog.findViewById(R.id.check_physical_DMG_bonus);
            dame_physical.setChecked(sharedPreferences.getBoolean("dame_physical", false));

            CheckBox hp = dialog.findViewById(R.id.check_hp);
            hp.setChecked(sharedPreferences.getBoolean("hp", false));

            CheckBox def = dialog.findViewById(R.id.check_def);
            def.setChecked(sharedPreferences.getBoolean("def", false));

            CheckBox check_one_rarity = dialog.findViewById(R.id.check_one_rarity);
            check_one_rarity.setChecked(sharedPreferences.getBoolean("check_one_rarity", false));
            //
            CheckBox rarity1s = dialog.findViewById(R.id.check_rarity_1s);
            rarity1s.setChecked(sharedPreferences.getBoolean("rarity1s", true));

            CheckBox rarity2s = dialog.findViewById(R.id.check_rarity_2s);
            rarity2s.setChecked(sharedPreferences.getBoolean("rarity2s", true));

            CheckBox rarity3s = dialog.findViewById(R.id.check_rarity_3s);
            rarity3s.setChecked(sharedPreferences.getBoolean("rarity3s", true));

            CheckBox rarity4s = dialog.findViewById(R.id.check_rarity_4s);
            rarity4s.setChecked(sharedPreferences.getBoolean("rarity4s", true));

            CheckBox rarity5s = dialog.findViewById(R.id.check_rarity_5s);
            rarity5s.setChecked(sharedPreferences.getBoolean("rarity5s", true));

            handlerCheckRarity(rarity1s, rarity2s, rarity3s, rarity4s, rarity5s);
            //
            RadioButton sortAZ = dialog.findViewById(R.id.radio_az);
            sortAZ.setChecked(sharedPreferences.getBoolean("sortAZ", true));
            RadioButton sortZA = dialog.findViewById(R.id.radio_za);
            sortZA.setChecked(sharedPreferences.getBoolean("sortZA", false));

            yes.setOnClickListener(v1 -> {
                if (!sword.isChecked() && !claymore.isChecked() && !bow.isChecked() && !polearm.isChecked() && !catalyst.isChecked()
                && !not_substat.isChecked() && !attack.isChecked() && !elemental_mastery.isChecked() && !crit_rate.isChecked() && !crit_dame.isChecked()
                        && !energy_recharge.isChecked() && !dame_physical.isChecked() && !hp.isChecked() && !def.isChecked()) {
                    Toast.makeText(context, context.getString(R.string.choose_filter_weapon), Toast.LENGTH_SHORT).show();
                } else {
                    filterWeapon(arrayNotChange, array, adapter, sword, claymore, bow, polearm, catalyst,
                            not_substat, attack, elemental_mastery, crit_rate, crit_dame, energy_recharge, dame_physical, hp, def,
                            check_one_rarity, rarity1s, rarity2s, rarity3s, rarity4s, rarity5s, sortAZ, sortZA);
                    dialog.dismiss();
                }
            });

            no.setOnClickListener(v1 -> dialog.dismiss());

            reset.setOnClickListener(v1 -> {
                reset(sword, claymore, bow, polearm, catalyst,
                        not_substat, attack, elemental_mastery, crit_rate, crit_dame, energy_recharge, dame_physical, hp, def,
                        check_one_rarity, rarity1s, rarity2s, rarity3s, rarity4s, rarity5s, sortAZ, sortZA);
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
    private void filterWeapon(ArrayList<Weapon> arrayNotChange, ArrayList<Weapon> array, WeaponAdapter adapter,
                              CheckBox sword, CheckBox claymore, CheckBox bow, CheckBox polearm, CheckBox catalyst,
                              CheckBox not_substat, CheckBox attack, CheckBox elemental_mastery, CheckBox crit_rate, CheckBox crit_dame,
                              CheckBox energy_recharge, CheckBox dame_physical, CheckBox hp, CheckBox def, CheckBox check_one_rarity,
                              CheckBox rarity1s, CheckBox rarity2s, CheckBox rarity3s, CheckBox rarity4s,
                              CheckBox rarity5s, RadioButton sortAZ, RadioButton sortZA) {
        new Handler(Looper.getMainLooper()).post(() -> {
            ArrayList<Weapon> arrayFilter = new ArrayList<>(arrayNotChange);
            if (sword.isChecked() || claymore.isChecked() || bow.isChecked() || polearm.isChecked() || catalyst.isChecked()){
                //kiếm đơn
                if (!sword.isChecked()) {
                    editor.putBoolean("sword", false);
                    arrayFilter.removeIf(weapon -> weapon.getWeapontype().contains(context.getString(R.string.sword)));
                } else editor.putBoolean("sword", true);
                //trọng kiếm
                if (!claymore.isChecked()) {
                    editor.putBoolean("claymore", false);
                    arrayFilter.removeIf(weapon -> weapon.getWeapontype().contains(context.getString(R.string.claymore)));
                } else editor.putBoolean("claymore", true);
                //cung
                if (!bow.isChecked()) {
                    editor.putBoolean("bow", false);
                    arrayFilter.removeIf(weapon -> weapon.getWeapontype().contains(context.getString(R.string.bow)));
                } else editor.putBoolean("bow", true);
                //vũ khí cán dài
                if (!polearm.isChecked()) {
                    editor.putBoolean("polearm", false);
                    arrayFilter.removeIf(weapon -> weapon.getWeapontype().contains(context.getString(R.string.polearm)));
                } else editor.putBoolean("polearm", true);
                //pháp khí
                if (!catalyst.isChecked()) {
                    editor.putBoolean("catalyst", false);
                    arrayFilter.removeIf(weapon -> weapon.getWeapontype().contains(context.getString(R.string.catalyst)));
                } else editor.putBoolean("catalyst", true);
            } else resetWeapon(sword, claymore, bow, polearm, catalyst);

            //=======================================================================================
            if (not_substat.isChecked() || attack.isChecked() || elemental_mastery.isChecked() || crit_rate.isChecked() ||
            crit_dame.isChecked() || energy_recharge.isChecked() || dame_physical.isChecked() || hp.isChecked() || def.isChecked()){
                //ko dòng phụ
                if (!not_substat.isChecked()) {
                    editor.putBoolean("not_substat", false);
                    arrayFilter.removeIf(weapon -> weapon.getSubstat().equals(""));
                } else editor.putBoolean("not_substat", true);
                //tấn công
                if (!attack.isChecked()) {
                    editor.putBoolean("attack", false);
                    arrayFilter.removeIf(weapon -> weapon.getSubstat().contains(context.getString(R.string.attack_percent)));
                } else editor.putBoolean("attack", true);
                //tinh thông
                if (!elemental_mastery.isChecked()) {
                    editor.putBoolean("elemental_mastery", false);
                    arrayFilter.removeIf(weapon -> weapon.getSubstat().contains(context.getString(R.string.elemental_mastery)));
                } else editor.putBoolean("elemental_mastery", true);
                //tỉ lệ bạo
                if (!crit_rate.isChecked()) {
                    editor.putBoolean("crit_rate", false);
                    arrayFilter.removeIf(weapon -> weapon.getSubstat().contains(context.getString(R.string.crit_rate)));
                } else editor.putBoolean("crit_rate", true);
                //sát thương bạo
                if (!crit_dame.isChecked()) {
                    editor.putBoolean("crit_dame", false);
                    arrayFilter.removeIf(weapon -> weapon.getSubstat().contains(context.getString(R.string.crit_dame)));
                } else editor.putBoolean("crit_dame", true);
                //hiệu quả nạp
                if (!energy_recharge.isChecked()) {
                    editor.putBoolean("energy_recharge", false);
                    arrayFilter.removeIf(weapon -> weapon.getSubstat().contains(context.getString(R.string.energy_recharge)));
                } else editor.putBoolean("energy_recharge", true);
                //st vật lý
                if (!dame_physical.isChecked()) {
                    editor.putBoolean("dame_physical", false);
                    arrayFilter.removeIf(weapon -> weapon.getSubstat().contains(context.getString(R.string.dame_physical)));
                } else editor.putBoolean("dame_physical", true);
                //hp
                if (!hp.isChecked()) {
                    editor.putBoolean("hp", false);
                    arrayFilter.removeIf(weapon -> weapon.getSubstat().contains(context.getString(R.string.hp)));
                } else editor.putBoolean("hp", true);
                //def
                if (!def.isChecked()) {
                    editor.putBoolean("def", false);
                    arrayFilter.removeIf(weapon -> weapon.getSubstat().contains(context.getString(R.string.def)));
                } else editor.putBoolean("def", true);
            } else resetSubstat(not_substat, attack, elemental_mastery, crit_rate, crit_dame, energy_recharge, dame_physical, hp, def);


            //lọc theo 1 độ hiếm
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
                    } else {
                        if (rarity3s.isChecked()) {
                            arrayFilter.removeIf(weapon -> weapon.getRarity().contains("1"));
                            arrayFilter.removeIf(weapon -> weapon.getRarity().contains("2"));
                            arrayFilter.removeIf(weapon -> weapon.getRarity().contains("4"));
                            arrayFilter.removeIf(weapon -> weapon.getRarity().contains("5"));
                        } else {
                            if (rarity2s.isChecked()) {
                                arrayFilter.removeIf(weapon -> weapon.getRarity().contains("1"));
                                arrayFilter.removeIf(weapon -> weapon.getRarity().contains("3"));
                                arrayFilter.removeIf(weapon -> weapon.getRarity().contains("4"));
                                arrayFilter.removeIf(weapon -> weapon.getRarity().contains("5"));
                            } else {
                                if (rarity1s.isChecked()) {
                                    arrayFilter.removeIf(weapon -> weapon.getRarity().contains("2"));
                                    arrayFilter.removeIf(weapon -> weapon.getRarity().contains("3"));
                                    arrayFilter.removeIf(weapon -> weapon.getRarity().contains("4"));
                                    arrayFilter.removeIf(weapon -> weapon.getRarity().contains("5"));
                                }
                            }
                        }
                    }
                }
            } else editor.putBoolean("check_one_rarity", false);

            if (!rarity1s.isChecked())
                arrayFilter.removeIf(weapon -> weapon.getRarity().contains("1"));
            if (!rarity2s.isChecked())
                arrayFilter.removeIf(weapon -> weapon.getRarity().contains("2"));
            if (!rarity3s.isChecked())
                arrayFilter.removeIf(weapon -> weapon.getRarity().contains("3"));
            if (!rarity4s.isChecked())
                arrayFilter.removeIf(weapon -> weapon.getRarity().contains("4"));
            if (!rarity5s.isChecked())
                arrayFilter.removeIf(weapon -> weapon.getRarity().contains("5"));

            editor.putBoolean("rarity1s", rarity1s.isChecked());
            editor.putBoolean("rarity2s", rarity2s.isChecked());
            editor.putBoolean("rarity3s", rarity3s.isChecked());
            editor.putBoolean("rarity4s", rarity4s.isChecked());
            editor.putBoolean("rarity5s", rarity5s.isChecked());
            //sắp xếp
            if (sortAZ.isChecked()) {
                editor.putBoolean("sortAZ", true);
                arrayFilter.sort((artifact, t1) -> ComparisonChain.start()
                        .compare(t1.getRarity(), artifact.getRarity(), Ordering.natural().nullsFirst())
                        .compare(artifact.getName(), t1.getName(), Ordering.natural().nullsFirst())
                        .result());
            } else editor.putBoolean("sortAZ", false);
            if (sortZA.isChecked()) {
                editor.putBoolean("sortZA", true);
                arrayFilter.sort((artifact, t1) -> ComparisonChain.start()
                        .compare(t1.getRarity(), artifact.getRarity(), Ordering.natural().nullsFirst())
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

    private void reset(CheckBox sword, CheckBox claymore, CheckBox bow, CheckBox polearm, CheckBox catalyst,
                       CheckBox not_substat, CheckBox attack, CheckBox elemental_mastery, CheckBox crit_rate, CheckBox crit_dame,
                       CheckBox energy_recharge, CheckBox dame_physical, CheckBox hp, CheckBox def,
                       CheckBox check_one_rarity, CheckBox rarity1s, CheckBox rarity2s, CheckBox rarity3s, CheckBox rarity4s,
                       CheckBox rarity5s, RadioButton sortAZ, RadioButton sortZA) {
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

        not_substat.setChecked(false);
        editor.putBoolean("not_substat", false);
        attack.setChecked(false);
        editor.putBoolean("attack", false);
        elemental_mastery.setChecked(false);
        editor.putBoolean("elemental_mastery", false);
        crit_rate.setChecked(false);
        editor.putBoolean("crit_rate", false);
        crit_dame.setChecked(false);
        editor.putBoolean("crit_dame", false);
        energy_recharge.setChecked(false);
        editor.putBoolean("energy_recharge", false);
        dame_physical.setChecked(false);
        editor.putBoolean("dame_physical", false);
        hp.setChecked(false);
        editor.putBoolean("hp", false);
        def.setChecked(false);
        editor.putBoolean("def", false);

        check_one_rarity.setChecked(false);
        editor.putBoolean("check_one_rarity", false);
        rarity1s.setChecked(true);
        editor.putBoolean("rarity1s", true);
        rarity2s.setChecked(true);
        editor.putBoolean("rarity2s", true);
        rarity3s.setChecked(true);
        editor.putBoolean("rarity3s", true);
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

    private void resetWeapon(CheckBox sword, CheckBox claymore, CheckBox bow, CheckBox polearm, CheckBox catalyst) {
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

    private void resetSubstat(CheckBox not_substat, CheckBox attack, CheckBox elemental_mastery, CheckBox crit_rate, CheckBox crit_dame, CheckBox energy_recharge, CheckBox dame_physical, CheckBox hp, CheckBox def) {
        not_substat.setChecked(false);
        editor.putBoolean("not_substat", false);
        attack.setChecked(false);
        editor.putBoolean("attack", false);
        elemental_mastery.setChecked(false);
        editor.putBoolean("elemental_mastery", false);
        crit_rate.setChecked(false);
        editor.putBoolean("crit_rate", false);
        crit_dame.setChecked(false);
        editor.putBoolean("crit_dame", false);
        energy_recharge.setChecked(false);
        editor.putBoolean("energy_recharge", false);
        dame_physical.setChecked(false);
        editor.putBoolean("dame_physical", false);
        hp.setChecked(false);
        editor.putBoolean("hp", false);
        def.setChecked(false);
        editor.putBoolean("def", false);
    }

    private void handlerCheckRarity(CheckBox check1, CheckBox check2, CheckBox check3, CheckBox check4, CheckBox check5) {
        check1.setOnClickListener(view -> {
            check1.setChecked(true);
            check2.setChecked(false);
            check3.setChecked(false);
            check4.setChecked(false);
            check5.setChecked(false);
        });
        check2.setOnClickListener(view -> {
            check1.setChecked(true);
            check2.setChecked(true);
            check3.setChecked(false);
            check4.setChecked(false);
            check5.setChecked(false);
        });
        check3.setOnClickListener(view -> {
            check1.setChecked(true);
            check2.setChecked(true);
            check3.setChecked(true);
            check4.setChecked(false);
            check5.setChecked(false);
        });
        check4.setOnClickListener(view -> {
            check1.setChecked(true);
            check2.setChecked(true);
            check3.setChecked(true);
            check4.setChecked(true);
            check5.setChecked(false);
        });
        check5.setOnClickListener(view -> {
            check1.setChecked(true);
            check2.setChecked(true);
            check3.setChecked(true);
            check4.setChecked(true);
            check5.setChecked(true);
        });
    }
}
