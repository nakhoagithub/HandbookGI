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
import com.kanthin.handbookgenshin.adapter.material.MaterialAdapter;
import com.kanthin.handbookgenshin.model.material.Material;
import com.kanthin.handbookgenshin.presenter.anInterface.FilterInterface;

import java.util.ArrayList;

public class FilterMaterial {
    private final Context context;
    private final FilterInterface anInterface;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final TextView countItem;

    public FilterMaterial(Context context, FilterInterface anInterface, TextView countItem) {
        this.context = context;
        this.anInterface = anInterface;
        this.countItem = countItem;
        sharedPreferences = context.getSharedPreferences("filter_material", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void dialogFilterMaterial(ArrayList<Material> arrayNotChange, ArrayList<Material> array, MaterialAdapter adapter) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_filter_material);

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

            CheckBox material = dialog.findViewById(R.id.material);
            material.setChecked(sharedPreferences.getBoolean("material", false));

            CheckBox refinement_material = dialog.findViewById(R.id.refinement_material);
            refinement_material.setChecked(sharedPreferences.getBoolean("refinement_material", false));

            CheckBox material_weapon = dialog.findViewById(R.id.material_weapon);
            material_weapon.setChecked(sharedPreferences.getBoolean("material_weapon", false));

            CheckBox material_talent = dialog.findViewById(R.id.material_talent);
            material_talent.setChecked(sharedPreferences.getBoolean("material_talent", false));

            CheckBox material_character = dialog.findViewById(R.id.material_character);
            material_character.setChecked(sharedPreferences.getBoolean("material_character", false));

            CheckBox material_monstadt = dialog.findViewById(R.id.material_monstadt);
            material_monstadt.setChecked(sharedPreferences.getBoolean("material_monstadt", false));

            CheckBox material_liyue = dialog.findViewById(R.id.material_liyue);
            material_liyue.setChecked(sharedPreferences.getBoolean("material_liyue", false));

            CheckBox material_inazuma = dialog.findViewById(R.id.material_inazuma);
            material_inazuma.setChecked(sharedPreferences.getBoolean("material_inazuma", false));

            CheckBox forging_ore = dialog.findViewById(R.id.forging_ore);
            forging_ore.setChecked(sharedPreferences.getBoolean("forging_ore", false));

            CheckBox food = dialog.findViewById(R.id.food);
            food.setChecked(sharedPreferences.getBoolean("food", false));

            CheckBox material_food = dialog.findViewById(R.id.material_food);
            material_food.setChecked(sharedPreferences.getBoolean("material_food", false));

            CheckBox fish = dialog.findViewById(R.id.fish);
            fish.setChecked(sharedPreferences.getBoolean("fish", false));

            CheckBox material_other = dialog.findViewById(R.id.material_other);
            material_other.setChecked(sharedPreferences.getBoolean("material_other", false));
            //
            CheckBox currency = dialog.findViewById(R.id.currency);
            currency.setChecked(sharedPreferences.getBoolean("currency", false));

            CheckBox check_one_rarity = dialog.findViewById(R.id.check_one_rarity);
            check_one_rarity.setChecked(sharedPreferences.getBoolean("check_one_rarity", false));

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

            RadioButton sortAZ = dialog.findViewById(R.id.radio_az);
            sortAZ.setChecked(sharedPreferences.getBoolean("sortAZ", true));
            RadioButton sortZA = dialog.findViewById(R.id.radio_za);
            sortZA.setChecked(sharedPreferences.getBoolean("sortZA", false));

            yes.setOnClickListener(v1 -> {
                if (!material.isChecked() && !refinement_material.isChecked() && !material_weapon.isChecked()
                        && !material_talent.isChecked() && !material_character.isChecked() && !material_monstadt.isChecked()
                        && !material_liyue.isChecked() && !material_inazuma.isChecked() && !forging_ore.isChecked() && !food.isChecked()
                        && !material_food.isChecked() && !fish.isChecked() && !material_other.isChecked() && !currency.isChecked()) {
                    Toast.makeText(context, context.getResources().getString(R.string.choose_filter_material), Toast.LENGTH_SHORT).show();
                } else {
                    filterMaterial(arrayNotChange, array, adapter, material, refinement_material, material_weapon,
                            material_talent, material_character, material_monstadt, material_liyue, material_inazuma,
                            forging_ore, food, material_food, fish, material_other, currency, check_one_rarity,
                            rarity1s, rarity2s, rarity3s, rarity4s, rarity5s, sortAZ, sortZA);
                    dialog.dismiss();
                }
            });

            no.setOnClickListener(v1 -> dialog.dismiss());

            reset.setOnClickListener(v1 -> {
                reset(material, refinement_material, material_weapon,
                        material_talent, material_character, material_monstadt, material_liyue, material_inazuma,
                        forging_ore, food, material_food, fish, material_other, currency, check_one_rarity, rarity1s, rarity2s,
                        rarity3s, rarity4s, rarity5s, sortAZ, sortZA);
                array.clear();
                array.addAll(arrayNotChange);
                array.sort((material1, t1) -> ComparisonChain.start()
                        .compare(material1.getSortorder(), t1.getSortorder(), Ordering.natural().nullsFirst())
                        .result());
                countItem.setText(String.valueOf(array.size()));
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            });
        }
        dialog.show();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filterMaterial(ArrayList<Material> arrayNotChange, ArrayList<Material> array, MaterialAdapter adapter,
                                CheckBox material, CheckBox refinement_material, CheckBox material_weapon,
                                CheckBox material_talent, CheckBox material_character, CheckBox material_monstadt,
                                CheckBox material_liyue, CheckBox material_inazuma, CheckBox forging_ore, CheckBox food,
                                CheckBox material_food, CheckBox fish, CheckBox material_other, CheckBox currency,
                                CheckBox check_one_rarity, CheckBox rarity1s, CheckBox rarity2s, CheckBox rarity3s,
                                CheckBox rarity4s, CheckBox rarity5s, RadioButton sortAZ, RadioButton sortZA) {
        new Handler(Looper.getMainLooper()).post(() -> {
            ArrayList<Material> arrayFilter = new ArrayList<>(arrayNotChange);
            if (!material.isChecked()) {
                editor.putBoolean("material", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.material_game).trim()));
            } else editor.putBoolean("material", true);

            if (!refinement_material.isChecked()) {
                editor.putBoolean("refinement_material", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.refinement_material).trim()));
            } else editor.putBoolean("refinement_material", true);

            if (!material_weapon.isChecked()) {
                editor.putBoolean("material_weapon", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.material_weapon).trim()));
            } else editor.putBoolean("material_weapon", true);

            if (!material_talent.isChecked()) {
                editor.putBoolean("material_talent", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.material_talent).trim()));
            } else editor.putBoolean("material_talent", true);

            if (!material_character.isChecked()) {
                editor.putBoolean("material_character", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.material_character).trim()));
            } else editor.putBoolean("material_character", true);

            if (!material_monstadt.isChecked()) {
                editor.putBoolean("material_monstadt", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.material_monstadt).trim()));
            } else editor.putBoolean("material_monstadt", true);

            if (!material_liyue.isChecked()) {
                editor.putBoolean("material_liyue", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.material_liyue).trim()));
            } else editor.putBoolean("material_liyue", true);

            if (!material_inazuma.isChecked()) {
                editor.putBoolean("material_inazuma", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.material_inazuma).trim()));
            } else editor.putBoolean("material_inazuma", true);

            if (!forging_ore.isChecked()) {
                editor.putBoolean("forging_ore", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.forging_ore).trim()));
            } else editor.putBoolean("forging_ore", true);

            if (!food.isChecked()) {
                editor.putBoolean("food", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.food).trim()));
            } else editor.putBoolean("food", true);

            if (!material_food.isChecked()) {
                editor.putBoolean("material_food", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.material_food).trim()));
            } else editor.putBoolean("material_food", true);

            if (!fish.isChecked()) {
                editor.putBoolean("fish", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().equals(context.getString(R.string.fish).trim()));
            } else editor.putBoolean("fish", true);

            if (!currency.isChecked()) {
                editor.putBoolean("currency", false);
                arrayFilter.removeIf(material1 -> material1.getMaterialtype().contains(context.getString(R.string.currency).trim()));
            } else editor.putBoolean("currency", true);

            if (!material_other.isChecked()) {
                editor.putBoolean("material_other", false);
                arrayFilter.removeIf(material1 -> !material1.getMaterialtype().equals(context.getString(R.string.material_game).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.refinement_material).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.material_weapon).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.material_talent).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.material_character).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.material_monstadt).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.material_liyue).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.material_inazuma).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.forging_ore).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.food).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.material_food).trim())
                        && !material1.getMaterialtype().equals(context.getString(R.string.fish).trim())
                        && !material1.getMaterialtype().contains(context.getString(R.string.currency).trim()));

            } else editor.putBoolean("material_other", true);

            if (check_one_rarity.isChecked()) {
                editor.putBoolean("check_one_rarity", true);
                if (rarity5s.isChecked()) {
                    arrayFilter.removeIf(material1 -> material1.getRarity() == null);
                    arrayFilter.removeIf(material1 -> material1.getRarity().contains("1"));
                    arrayFilter.removeIf(material1 -> material1.getRarity().contains("2"));
                    arrayFilter.removeIf(material1 -> material1.getRarity().contains("3"));
                    arrayFilter.removeIf(material1 -> material1.getRarity().contains("4"));
                } else {
                    if (rarity4s.isChecked()) {
                        arrayFilter.removeIf(material1 -> material1.getRarity() == null);
                        arrayFilter.removeIf(material1 -> material1.getRarity().contains("1"));
                        arrayFilter.removeIf(material1 -> material1.getRarity().contains("2"));
                        arrayFilter.removeIf(material1 -> material1.getRarity().contains("3"));
                        arrayFilter.removeIf(material1 -> material1.getRarity().contains("5"));
                    } else {
                        if (rarity3s.isChecked()) {
                            arrayFilter.removeIf(material1 -> material1.getRarity() == null);
                            arrayFilter.removeIf(material1 -> material1.getRarity().contains("1"));
                            arrayFilter.removeIf(material1 -> material1.getRarity().contains("2"));
                            arrayFilter.removeIf(material1 -> material1.getRarity().contains("4"));
                            arrayFilter.removeIf(material1 -> material1.getRarity().contains("5"));
                        } else {
                            if (rarity2s.isChecked()) {
                                arrayFilter.removeIf(material1 -> material1.getRarity() == null);
                                arrayFilter.removeIf(material1 -> material1.getRarity().contains("1"));
                                arrayFilter.removeIf(material1 -> material1.getRarity().contains("3"));
                                arrayFilter.removeIf(material1 -> material1.getRarity().contains("4"));
                                arrayFilter.removeIf(material1 -> material1.getRarity().contains("5"));
                            } else {
                                if (rarity1s.isChecked()) {
                                    arrayFilter.removeIf(material1 -> material1.getRarity() == null);
                                    arrayFilter.removeIf(material1 -> material1.getRarity().contains("2"));
                                    arrayFilter.removeIf(material1 -> material1.getRarity().contains("3"));
                                    arrayFilter.removeIf(material1 -> material1.getRarity().contains("4"));
                                    arrayFilter.removeIf(material1 -> material1.getRarity().contains("5"));
                                }
                            }
                        }
                    }
                }
            } else editor.putBoolean("check_one_rarity", false);

            if (!rarity1s.isChecked())
                arrayFilter.removeIf(material1 -> !(material1.getRarity() == null));

            if (!rarity2s.isChecked())
                arrayFilter.removeIf(material1 -> {
                    if (material1.getRarity() != null) {
                        return material1.getRarity().contains("2");
                    } else {
                        return false;
                    }
                });
            if (!rarity3s.isChecked())
                arrayFilter.removeIf(material1 -> {
                    if (material1.getRarity() != null) {
                        return material1.getRarity().contains("3");
                    } else {
                        return false;
                    }
                });
            if (!rarity4s.isChecked())
                arrayFilter.removeIf(material1 -> {
                    if (material1.getRarity() != null) {
                        return material1.getRarity().contains("4");
                    } else {
                        return false;
                    }
                });
            if (!rarity5s.isChecked())
                arrayFilter.removeIf(material1 -> {
                    if (material1.getRarity() != null) {
                        return material1.getRarity().contains("5");
                    } else {
                        return false;
                    }
                });

            editor.putBoolean("rarity1s", rarity1s.isChecked());
            editor.putBoolean("rarity2s", rarity2s.isChecked());
            editor.putBoolean("rarity3s", rarity3s.isChecked());
            editor.putBoolean("rarity4s", rarity4s.isChecked());
            editor.putBoolean("rarity5s", rarity5s.isChecked());

            //sắp xếp
            if (sortAZ.isChecked()) {
                editor.putBoolean("sortAZ", true);
                arrayFilter.sort((material1, t1) -> ComparisonChain.start()
                        .compare(material1.getMaterialtype(), t1.getMaterialtype(), Ordering.natural().nullsFirst())
                        .compare(t1.getRarity(), material1.getRarity(), Ordering.natural().nullsFirst())
                        .result());
            } else editor.putBoolean("sortAZ", false);
            if (sortZA.isChecked()) {
                editor.putBoolean("sortAZ", true);
                arrayFilter.sort((material1, t1) -> ComparisonChain.start()
                        .compare(material1.getMaterialtype(), t1.getMaterialtype(), Ordering.natural().nullsFirst())
                        .compare(material1.getRarity(), t1.getRarity(), Ordering.natural().nullsFirst())
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

    private void reset(CheckBox material, CheckBox refinement_material, CheckBox material_weapon, CheckBox material_talent,
                       CheckBox material_character, CheckBox material_monstadt, CheckBox material_liyue,
                       CheckBox material_inazuma, CheckBox forging_ore, CheckBox food, CheckBox material_food, CheckBox fish,
                       CheckBox material_other, CheckBox currency, CheckBox check_one_rarity, CheckBox rarity1s, CheckBox rarity2s,
                       CheckBox rarity3s, CheckBox rarity4s, CheckBox rarity5s, RadioButton sortAZ, RadioButton sortZA) {
        material.setChecked(false);
        editor.putBoolean("material", false);
        refinement_material.setChecked(false);
        editor.putBoolean("refinement_material", false);
        material_weapon.setChecked(false);
        editor.putBoolean("material_weapon", false);
        material_talent.setChecked(false);
        editor.putBoolean("material_talent", false);
        material_character.setChecked(false);
        editor.putBoolean("material_character", false);
        material_monstadt.setChecked(false);
        editor.putBoolean("material_monstadt", false);
        material_liyue.setChecked(false);
        editor.putBoolean("material_liyue", false);
        material_inazuma.setChecked(false);
        editor.putBoolean("material_inazuma", false);
        forging_ore.setChecked(false);
        editor.putBoolean("forging_ore", false);
        food.setChecked(false);
        editor.putBoolean("food", false);
        material_food.setChecked(false);
        editor.putBoolean("material_food", false);
        fish.setChecked(false);
        editor.putBoolean("fish", false);
        material_other.setChecked(false);
        editor.putBoolean("material_other", false);
        currency.setChecked(false);
        editor.putBoolean("currency", false);

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

    private void handlerCheckRarity(CheckBox check1, CheckBox check2, CheckBox check3, CheckBox check4, CheckBox check5) {
        check1.setOnClickListener(view -> {
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
