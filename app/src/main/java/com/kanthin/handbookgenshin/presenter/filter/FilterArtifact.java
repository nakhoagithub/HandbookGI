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

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.artifact.ArtifactAdapter;
import com.kanthin.handbookgenshin.model.artifact.Artifact;
import com.kanthin.handbookgenshin.presenter.anInterface.FilterInterface;

import java.util.ArrayList;

public class FilterArtifact {
    private final Context context;
    private final FilterInterface anInterface;
    private final TextView countItem;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public FilterArtifact(Context context, FilterInterface anInterface, TextView countItem) {
        this.context = context;
        this.anInterface = anInterface;
        this.countItem = countItem;
        sharedPreferences = context.getSharedPreferences("filter_artifact", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void dialogFilterArtifact(ArrayList<Artifact> arrayNotChange, ArrayList<Artifact> array, ArtifactAdapter adapter) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_filter_artifact);

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

            RadioButton sortAZ = dialog.findViewById(R.id.radio_az);
            sortAZ.setChecked(sharedPreferences.getBoolean("sortAZ", true));
            RadioButton sortZA = dialog.findViewById(R.id.radio_za);
            sortZA.setChecked(sharedPreferences.getBoolean("sortZA", false));

            yes.setOnClickListener(v1 -> {
                filterArtifact(arrayNotChange, array, adapter, check_one_rarity,
                        rarity1s, rarity2s, rarity3s, rarity4s, rarity5s, sortAZ, sortZA);
                dialog.dismiss();

            });

            no.setOnClickListener(v1 -> dialog.dismiss());

            reset.setOnClickListener(v1 -> {
                reset(check_one_rarity,
                        rarity1s, rarity2s, rarity3s, rarity4s, rarity5s, sortAZ, sortZA);
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
    private void filterArtifact(ArrayList<Artifact> arrayNotChange, ArrayList<Artifact> array,
                                ArtifactAdapter adapter, CheckBox check_one_rarity, CheckBox rarity1s,
                                CheckBox rarity2s, CheckBox rarity3s, CheckBox rarity4s, CheckBox rarity5s,
                                RadioButton sortAZ, RadioButton sortZA) {
        new Handler(Looper.getMainLooper()).post(() -> {
            ArrayList<Artifact> arrayFilter = new ArrayList<>(arrayNotChange);
            //lọc theo 1 độ hiếm
            if (check_one_rarity.isChecked()) {
                editor.putBoolean("check_one_rarity", true);
                if (rarity5s.isChecked()) {
                    arrayFilter.removeIf(artifact -> {
                        for (int i = 0; i < artifact.getRarity().size(); i++) {
                            if (artifact.getRarity().get(i).contains("5")) return false;
                        }
                        return true;
                    });
                } else {
                    if (rarity4s.isChecked()) {
                        arrayFilter.removeIf(artifact -> {
                            for (int i = 0; i < artifact.getRarity().size(); i++) {
                                if (artifact.getRarity().get(i).contains("4")) return false;
                            }
                            return true;
                        });
                    } else {
                        if (rarity3s.isChecked()) {
                            arrayFilter.removeIf(artifact -> {
                                for (int i = 0; i < artifact.getRarity().size(); i++) {
                                    if (artifact.getRarity().get(i).contains("3")) return false;
                                }
                                return true;
                            });
                        } else {
                            if (rarity2s.isChecked()) {
                                arrayFilter.removeIf(artifact -> {
                                    for (int i = 0; i < artifact.getRarity().size(); i++) {
                                        if (artifact.getRarity().get(i).contains("2")) return false;
                                    }
                                    return true;
                                });
                            } else {
                                if (rarity1s.isChecked()) {
                                    arrayFilter.removeIf(artifact -> {
                                        for (int i = 0; i < artifact.getRarity().size(); i++) {
                                            if (artifact.getRarity().get(i).contains("1"))
                                                return false;
                                        }
                                        return true;
                                    });
                                }
                            }
                        }
                    }
                }
            } else editor.putBoolean("check_one_rarity", false);
            if (!rarity5s.isChecked()) {
                arrayFilter.removeIf(artifact -> {
                    for (int i = 0; i < artifact.getRarity().size(); i++) {
                        if (artifact.getRarity().get(i).contains("5")) return true;
                    }
                    return false;
                });
            }
            if (!rarity4s.isChecked()) {
                arrayFilter.removeIf(artifact -> {
                    for (int i = 0; i < artifact.getRarity().size(); i++) {
                        if (artifact.getRarity().get(i).contains("4")) return true;
                    }
                    return false;
                });
            }
            if (!rarity3s.isChecked()) {
                arrayFilter.removeIf(artifact -> {
                    for (int i = 0; i < artifact.getRarity().size(); i++) {
                        if (artifact.getRarity().get(i).contains("3")) return true;
                    }
                    return false;
                });
            }
            if (!rarity2s.isChecked()) {
                arrayFilter.removeIf(artifact -> {
                    for (int i = 0; i < artifact.getRarity().size(); i++) {
                        if (artifact.getRarity().get(i).contains("2")) return true;
                    }
                    return false;
                });
            }

            editor.putBoolean("rarity1s", rarity1s.isChecked());
            editor.putBoolean("rarity2s", rarity2s.isChecked());
            editor.putBoolean("rarity3s", rarity3s.isChecked());
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

            array.sort((artifact, t1) -> ComparisonChain.start()
                    .compare(t1.getRarity().get(t1.getRarity().size()-1), artifact.getRarity().get(artifact.getRarity().size()-1), Ordering.natural().nullsFirst())
                    .result());

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

    private void reset(CheckBox check_one_rarity, CheckBox rarity1s, CheckBox
            rarity2s,
                       CheckBox rarity3s, CheckBox rarity4s, CheckBox rarity5s, RadioButton
                               sortAZ,
                       RadioButton sortZA) {
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

    private void handlerCheckRarity(CheckBox check1, CheckBox check2, CheckBox
            check3, CheckBox check4, CheckBox check5) {
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
