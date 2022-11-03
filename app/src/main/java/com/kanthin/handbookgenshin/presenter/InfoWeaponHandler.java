package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.weapon.CostsAscendWeaponAdapter;
import com.kanthin.handbookgenshin.adapter.weapon.IndexWeaponAdapter;
import com.kanthin.handbookgenshin.model.ItemCostsAscend;
import com.kanthin.handbookgenshin.model.weapon.StatsWeapon;
import com.kanthin.handbookgenshin.model.weapon.Weapon;
import com.kanthin.handbookgenshin.presenter.anInterface.InfoWeaponInterface;

import java.util.ArrayList;

public class InfoWeaponHandler {
    private final Context context;
    private final InfoWeaponInterface anInterface;

    public InfoWeaponHandler(Context context, InfoWeaponInterface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    private String getLinkImage(Weapon weapon){
        String link_nameicon = weapon.getImages().getNameicon();
        String link_icon = weapon.getImages().getIcon();
        if (link_icon != null){
            return link_icon;
        } else return "https://res.cloudinary.com/genshin/image/upload/sprites/" + link_nameicon + ".png";
    }

    public void loadImage(Weapon weapon, ImageView imageItemWeapon) {
        Glide.with(context)
                .load(getLinkImage(weapon))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_null)
                .into(imageItemWeapon);
        anInterface.success();
    }

    public void loadInfo(Weapon weapon, TextView nameItem, TextView nameWeapon, TextView attack, ImageView imageRarity,
                         TextView typeWeapon, TextView subStatWeapon, RelativeLayout layoutBackgroundItem) {
        nameItem.setText(weapon.getName());
        nameWeapon.setText(weapon.getName());
        attack.setText(String.valueOf(weapon.getBaseatk()));
        switch (weapon.getRarity()) {
            case "1":
                imageRarity.setImageResource(R.drawable.ic_1s);
                layoutBackgroundItem.setBackgroundResource(R.drawable.rarity_1_background);
                break;
            case "2":
                imageRarity.setImageResource(R.drawable.ic_2s);
                layoutBackgroundItem.setBackgroundResource(R.drawable.rarity_2_background);
                break;
            case "3":
                imageRarity.setImageResource(R.drawable.ic_3s);
                layoutBackgroundItem.setBackgroundResource(R.drawable.rarity_3_background);
                break;
            case "4":
                imageRarity.setImageResource(R.drawable.ic_4s);
                layoutBackgroundItem.setBackgroundResource(R.drawable.rarity_4_background);
                break;
            case "5":
                imageRarity.setImageResource(R.drawable.ic_5s);
                layoutBackgroundItem.setBackgroundResource(R.drawable.rarity_5_background);
                break;
        }
        typeWeapon.setText(weapon.getWeapontype());
        if (!weapon.getSubstat().equals("")) {
            if (weapon.getSubstat() != null)
                subStatWeapon.setText(weapon.getSubstat());
        } else subStatWeapon.setText(context.getResources().getString(R.string.not));

    }

    public void loadInfoMore(Weapon weapon, TextView effectNameWeapon, TextView descriptionWeapon) {
        if (!weapon.getEffectname().equals("")) {
            if (weapon.getEffectname() != null)
                effectNameWeapon.setText(weapon.getEffectname());
        } else effectNameWeapon.setText(context.getResources().getString(R.string.not));
        if (!weapon.getDescription().equals("")) {
            if (weapon.getDescription() != null)
                descriptionWeapon.setText(weapon.getDescription());
        } else descriptionWeapon.setText(context.getResources().getString(R.string.not));
    }

    @SuppressLint("SetTextI18n")
    public void loadEffectWeapon(Weapon weapon, TextView effectR1, TextView effectR2, TextView effectR3, TextView effectR4, TextView effectR5) {
        if (weapon.getR1() != null && weapon.getR2() != null && weapon.getR3() != null && weapon.getR4() != null &&
                weapon.getR5() != null) {
            String effect1 = weapon.getEffect();
            String effect2 = weapon.getEffect();
            String effect3 = weapon.getEffect();
            String effect4 = weapon.getEffect();
            String effect5 = weapon.getEffect();
            for (int j = 0; j < weapon.getR1().size(); j++) {
                if (effect1.contains("{" + j + "}")) {
                    effect1 = effect1.replace("{" + j + "}", "<font color=\"#FF9800\"><b>" + weapon.getR1().get(j) + "</b></font>");
                }
                if (effect2.contains("{" + j + "}")) {
                    effect2 = effect2.replace("{" + j + "}", "<font color=\"#FF9800\"><b>" + weapon.getR2().get(j) + "</b></font>");
                }
                if (effect3.contains("{" + j + "}")) {
                    effect3 = effect3.replace("{" + j + "}", "<font color=\"#FF9800\"><b>" + weapon.getR3().get(j) + "</b></font>");
                }
                if (effect4.contains("{" + j + "}")) {
                    effect4 = effect4.replace("{" + j + "}", "<font color=\"#FF9800\"><b>" + weapon.getR4().get(j) + "</b></font>");
                }
                if (effect5.contains("{" + j + "}")) {
                    effect5 = effect5.replace("{" + j + "}", "<font color=\"#FF9800\"><b>" + weapon.getR5().get(j) + "</b></font>");
                }
            }
            effectR1.setText(Html.fromHtml(context.getResources().getString(R.string.effect1) + ": " + effect1, Html.FROM_HTML_MODE_LEGACY));
            effectR2.setText(Html.fromHtml(context.getResources().getString(R.string.effect2) + ": " + effect2, Html.FROM_HTML_MODE_LEGACY));
            effectR3.setText(Html.fromHtml(context.getResources().getString(R.string.effect3) + ": " + effect3, Html.FROM_HTML_MODE_LEGACY));
            effectR4.setText(Html.fromHtml(context.getResources().getString(R.string.effect4) + ": " + effect4, Html.FROM_HTML_MODE_LEGACY));
            effectR5.setText(Html.fromHtml(context.getResources().getString(R.string.effect5) + ": " + effect5, Html.FROM_HTML_MODE_LEGACY));
        } else {
            if (weapon.getR1() != null && weapon.getR2() == null){
                String effect1 = weapon.getEffect();
                for (int j = 0; j < weapon.getR1().size(); j++) {
                    if (effect1.contains("{" + j + "}")) {
                        effect1 = effect1.replace("\\n", "<br>");
                        effect1 = effect1.replace("{" + j + "}", "<font color=\"#FF9800\"><b>" + weapon.getR1().get(j) + "</b></font>");
                    }
                }
                effectR1.setText(Html.fromHtml(context.getResources().getString(R.string.effect1) + ": " + effect1, Html.FROM_HTML_MODE_LEGACY));

            } else {
                effectR1.setText(weapon.getEffect());
            }
            effectR2.setText(context.getResources().getString(R.string.not));
            effectR3.setText(context.getResources().getString(R.string.not));
            effectR4.setText(context.getResources().getString(R.string.not));
            effectR5.setText(context.getResources().getString(R.string.not));
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadIndexWeapon(Weapon weapon, ArrayList<StatsWeapon> arrayIndexWeapon, IndexWeaponAdapter adapterIndexWeapon) {
        String[] arrayLvKey = {"1", "5", "10", "15", "20", "20+", "25", "30", "35", "40", "40+", "45", "50", "50+",
                "55", "60", "60+", "65", "70", "70+", "75", "80", "80+", "85", "90"};
        if (weapon.getStats() != null) {
            arrayIndexWeapon.add(new StatsWeapon(weapon.getSubstat()));
            for (int i = 0; i < weapon.getStats().size(); i++) {
                int ascension = weapon.getStats().get(i).getAscension();
                int lv = weapon.getStats().get(i).getLevel();
                double atk = (double) Math.round(weapon.getStats().get(i).getAttack());
                //xử lý nhân 100 nếu chỉ số k phải tinh thông nguyên tố
                double specialized = new CustomStringLanguage(context).handlerIndexSubStatsWeapon(weapon, weapon.getStats().get(i).getSpecialized());

                arrayIndexWeapon.add(new StatsWeapon(ascension, atk, lv, specialized, arrayLvKey[i]));
            }
            adapterIndexWeapon.notifyDataSetChanged();
        } else anInterface.fail("INDEX_NULL");
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadCostAscendWeapon(Weapon weapon, ArrayList<ItemCostsAscend> arrayCostsAscend, CostsAscendWeaponAdapter adapterCostsAscend) {
        arrayCostsAscend.add(new ItemCostsAscend());
        arrayCostsAscend.add(new ItemCostsAscend("1", "20", weapon.getCosts().getAscend1()));
        arrayCostsAscend.add(new ItemCostsAscend("2", "40", weapon.getCosts().getAscend2()));
        arrayCostsAscend.add(new ItemCostsAscend("3", "50", weapon.getCosts().getAscend3()));
        arrayCostsAscend.add(new ItemCostsAscend("4", "60", weapon.getCosts().getAscend4()));
        if (weapon.getCosts().getAscend5() != null) {
            arrayCostsAscend.add(new ItemCostsAscend("5", "70", weapon.getCosts().getAscend5()));
            arrayCostsAscend.add(new ItemCostsAscend("6", "80", weapon.getCosts().getAscend6()));
        }
        adapterCostsAscend.notifyDataSetChanged();
    }
}
