package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.character.AttributeCombatAdapter;
import com.kanthin.handbookgenshin.adapter.character.ConstellationAdapter;
import com.kanthin.handbookgenshin.adapter.character.CostsAscendCharacterAdapter;
import com.kanthin.handbookgenshin.adapter.character.CostsTalentAdapter;
import com.kanthin.handbookgenshin.adapter.character.IndexCharacterAdapter;
import com.kanthin.handbookgenshin.model.ItemCostsAscend;
import com.kanthin.handbookgenshin.model.character.Character;
import com.kanthin.handbookgenshin.model.character.Constellation;
import com.kanthin.handbookgenshin.model.character.StatsCharacter;
import com.kanthin.handbookgenshin.model.character.talents.ItemCostsTalent;
import com.kanthin.handbookgenshin.model.character.talents.LabelAndLever;
import com.kanthin.handbookgenshin.presenter.anInterface.InfoCharacterInterface;

import java.util.ArrayList;

public class InfoCharacterHandler {

    private final Context context;
    private final InfoCharacterInterface anInterface;
    private CustomCombat customCombat;

    public InfoCharacterHandler(Context context, InfoCharacterInterface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
        customCombat = new CustomCombat();

    }

    public void loadImage(Character character, ImageView imageItemCharacter) {
        Glide.with(context)
                .load(character.getImages().getIcon())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_null)
                .into(imageItemCharacter);
        anInterface.success();

    }

    public void loadInfo(Character character, TextView nameItem, TextView nameCharacter, ImageView imageRarity,
                         ImageView imageWeapon, ImageView imageVision, RelativeLayout layoutBackgroundItem) {
        nameItem.setText(character.getName());
        nameCharacter.setText(character.getName());
        switch (character.getRarity()) {
            case "4":
                imageRarity.setImageResource(R.drawable.ic_4s);
                layoutBackgroundItem.setBackgroundResource(R.drawable.rarity_4_background);
                break;
            case "5":
                imageRarity.setImageResource(R.drawable.ic_5s);
                layoutBackgroundItem.setBackgroundResource(R.drawable.rarity_5_background);
                break;
        }
        if (character.getWeapontype().contains(context.getResources().getString(R.string.bow)))
            imageWeapon.setImageResource(R.drawable.bow);
        if (character.getWeapontype().contains(context.getResources().getString(R.string.sword)))
            imageWeapon.setImageResource(R.drawable.sword);
        if (character.getWeapontype().contains(context.getResources().getString(R.string.catalyst)))
            imageWeapon.setImageResource(R.drawable.catalyst);
        if (character.getWeapontype().contains(context.getResources().getString(R.string.polearm)))
            imageWeapon.setImageResource(R.drawable.polearm);
        if (character.getWeapontype().contains(context.getResources().getString(R.string.claymore)))
            imageWeapon.setImageResource(R.drawable.claymore);
        if (character.getElement().contains(context.getResources().getString(R.string.geo)))
            imageVision.setImageResource(R.drawable.element_geo);
        if (character.getElement().contains(context.getResources().getString(R.string.pyro)))
            imageVision.setImageResource(R.drawable.element_pyro);
        if (character.getElement().contains(context.getResources().getString(R.string.hydro)))
            imageVision.setImageResource(R.drawable.element_hydro);
        if (character.getElement().contains(context.getResources().getString(R.string.electro)))
            imageVision.setImageResource(R.drawable.element_electro);
        if (character.getElement().contains(context.getResources().getString(R.string.cryo)))
            imageVision.setImageResource(R.drawable.element_cryo);
        if (character.getElement().contains(context.getResources().getString(R.string.anemo)))
            imageVision.setImageResource(R.drawable.element_anemo);
        if (character.getElement().contains(context.getResources().getString(R.string.dendro)))
            imageVision.setImageResource(R.drawable.element_dendro);

    }

    public void loadInfoMore(Character character, TextView subStatCharacter, ImageView imageGender, TextView birthdayCharacter,
                             TextView constellationCharacter, TextView regionCharacter, TextView affiliationCharacter,
                             TextView descriptionCharacter) {
        if (character.getSubstat() != null)
            subStatCharacter.setText(character.getSubstat());

        if (character.getGender() != null) {
            if (character.getGender().contains(context.getString(R.string.male))) {
                imageGender.setImageResource(R.drawable.ic_male);
            }
            if (character.getGender().contains(context.getString(R.string.female))) {
                imageGender.setImageResource(R.drawable.ic_female);
            }
        }
        if (character.getBirthday() != null) birthdayCharacter.setText(character.getBirthday());

        if (character.getConstellation() != null)
            constellationCharacter.setText(character.getConstellation());

        if (character.getRegion() != null) {
            if (character.getRegion().equals("")) {
                regionCharacter.setText("--");
            } else regionCharacter.setText(character.getRegion());
        }

        if (character.getAffiliation() != null)
            if (character.getAffiliation().equals("")) {
                affiliationCharacter.setText("--");
            } else affiliationCharacter.setText(character.getAffiliation());

        if (character.getDescription() != null)
            descriptionCharacter.setText(character.getDescription());
    }

    public void loadCombat1(Character character, ImageView imageCombat1, TextView nameCombat1, TextView infoCombat1) {

        String linkImageCombat1;
        if (character.getTalents().getImages() != null) {
            linkImageCombat1 = character.getTalents().getImages().getCombat1();
        } else {
            linkImageCombat1 = "";
        }
        String stringNameCombat1 = character.getTalents().getCombat1().getName();
        String stringInfoCombat1 = character.getTalents().getCombat1().getInfo();
        Glide.with(context)
                .load("https://res.cloudinary.com/genshin/image/upload/sprites/" + linkImageCombat1 + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.skill_fail)
                .into(imageCombat1);

        if (stringNameCombat1 != null)
            nameCombat1.setText(stringNameCombat1);

        if (stringInfoCombat1 != null) {
            stringInfoCombat1 = new CustomCombat(context).customStringCombat_Constellation(stringInfoCombat1);
            infoCombat1.setText(Html.fromHtml(stringInfoCombat1, Html.FROM_HTML_MODE_LEGACY));
        }
    }

    public void loadAttributeCombat1(Character character) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_attribute_combat);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(false);

            //Recycler view attribute combat
            RecyclerView recyclerLabelAndLevel = dialog.findViewById(R.id.recycler_attribute_combat);
            recyclerLabelAndLevel.setNestedScrollingEnabled(false);
            ArrayList<LabelAndLever> arrayLabelAndLevel = new ArrayList<>();
            AttributeCombatAdapter adapterLabelAndLevel = new AttributeCombatAdapter(context, arrayLabelAndLevel);
            customCombat.getAttributeCombat1(character, arrayLabelAndLevel, adapterLabelAndLevel);
            LinearLayoutManager layoutManagerCostsTalent = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerLabelAndLevel.setLayoutManager(layoutManagerCostsTalent);
            recyclerLabelAndLevel.setAdapter(adapterLabelAndLevel);

            //bt yes
            TextView yes = dialog.findViewById(R.id.bt_yes_dialog);
            yes.setOnClickListener(v1 -> dialog.dismiss());
        }
        dialog.show();
    }


    public void loadCombat2(Character character, ImageView imageCombat2, TextView nameCombat2,
                            TextView infoCombat2, TextView descCombat2) {
        String linkImageCombat2;
        if (character.getTalents().getImages() != null) {
            linkImageCombat2 = character.getTalents().getImages().getCombat2();
        } else {
            linkImageCombat2 = "";
        }
        String stringNameCombat2 = character.getTalents().getCombat2().getName();
        String stringInfoCombat2 = character.getTalents().getCombat2().getInfo();
        String stringDescriptionCombat2 = character.getTalents().getCombat2().getDescription();

        Glide.with(context)
                .load("https://res.cloudinary.com/genshin/image/upload/sprites/" + linkImageCombat2 + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.skill_fail)
                .into(imageCombat2);

        if (stringNameCombat2 != null)
            nameCombat2.setText(stringNameCombat2);

        if (stringInfoCombat2 != null) {
            stringInfoCombat2 = new CustomCombat(context).customStringCombat_Constellation(stringInfoCombat2);
            infoCombat2.setText(Html.fromHtml(stringInfoCombat2, Html.FROM_HTML_MODE_LEGACY));
        }
        if (stringDescriptionCombat2 != null)
            descCombat2.setText(stringDescriptionCombat2);
    }

    public void loadAttributeCombat2(Character character) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_attribute_combat);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(false);

            //Recycler view attribute combat
            RecyclerView recyclerLabelAndLevel = dialog.findViewById(R.id.recycler_attribute_combat);
            recyclerLabelAndLevel.setNestedScrollingEnabled(false);
            ArrayList<LabelAndLever> arrayLabelAndLevel = new ArrayList<>();
            AttributeCombatAdapter adapterLabelAndLevel = new AttributeCombatAdapter(context, arrayLabelAndLevel);
            customCombat.getAttributeCombat2(character, arrayLabelAndLevel, adapterLabelAndLevel);
            LinearLayoutManager layoutManagerCostsTalent = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerLabelAndLevel.setLayoutManager(layoutManagerCostsTalent);
            recyclerLabelAndLevel.setAdapter(adapterLabelAndLevel);

            //bt yes
            TextView yes = dialog.findViewById(R.id.bt_yes_dialog);
            yes.setOnClickListener(v1 -> dialog.dismiss());
        }
        dialog.show();
    }

    public void loadCombat3(Character character, ImageView imageCombat3, TextView nameCombat3,
                            TextView infoCombat3, TextView descCombat3) {
        String linkImageCombat3;
        if (character.getTalents().getImages() != null){
            linkImageCombat3 = character.getTalents().getImages().getCombat3();
        } else {
            linkImageCombat3 = "";
        }
        String stringNameCombat3 = character.getTalents().getCombat3().getName();
        String stringInfoCombat3 = character.getTalents().getCombat3().getInfo();
        String stringDescriptionCombat3 = character.getTalents().getCombat3().getDescription();

        Glide.with(context)
                .load("https://res.cloudinary.com/genshin/image/upload/sprites/" + linkImageCombat3 + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.skill_fail)
                .into(imageCombat3);

        if (stringNameCombat3 != null)
            nameCombat3.setText(stringNameCombat3);

        if (stringInfoCombat3 != null) {
            stringInfoCombat3 = new CustomCombat(context).customStringCombat_Constellation(stringInfoCombat3);
            infoCombat3.setText(Html.fromHtml(stringInfoCombat3, Html.FROM_HTML_MODE_LEGACY));
        }
        if (stringDescriptionCombat3 != null)
            descCombat3.setText(stringDescriptionCombat3);
    }

    public void loadAttributeCombat3(Character character) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_attribute_combat);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(false);

            //Recycler view attribute combat
            RecyclerView recyclerLabelAndLevel = dialog.findViewById(R.id.recycler_attribute_combat);
            recyclerLabelAndLevel.setNestedScrollingEnabled(false);
            ArrayList<LabelAndLever> arrayLabelAndLevel = new ArrayList<>();
            AttributeCombatAdapter adapterLabelAndLevel = new AttributeCombatAdapter(context, arrayLabelAndLevel);
            customCombat.getAttributeCombat3(character, arrayLabelAndLevel, adapterLabelAndLevel);
            LinearLayoutManager layoutManagerCostsTalent = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerLabelAndLevel.setLayoutManager(layoutManagerCostsTalent);
            recyclerLabelAndLevel.setAdapter(adapterLabelAndLevel);

            //bt yes
            TextView yes = dialog.findViewById(R.id.bt_yes_dialog);
            yes.setOnClickListener(v1 -> dialog.dismiss());
        }
        dialog.show();
    }

    public void loadCombatSp(Character character, View viewCombatSp, ImageView imageCombatSp, TextView nameCombatSp,
                             TextView infoCombatSp, TextView descCombatSp) {
        if (character.getTalents().getCombatsp() != null) {
            viewCombatSp.setVisibility(View.VISIBLE);
            String linkImageCombatSp;
            if (character.getTalents().getImages() != null){
                linkImageCombatSp = character.getTalents().getImages().getCombatsp();
            } else {
                linkImageCombatSp = "";
            }
            String stringNameCombatSp = character.getTalents().getCombatsp().getName();
            String stringInfoCombatSp = character.getTalents().getCombatsp().getInfo();
            String stringDescriptionCombatSp = character.getTalents().getCombatsp().getDescription();

            Glide.with(context)
                    .load("https://res.cloudinary.com/genshin/image/upload/sprites/" + linkImageCombatSp + ".png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.skill_fail)
                    .into(imageCombatSp);

            if (stringNameCombatSp != null)
                nameCombatSp.setText(stringNameCombatSp);

            if (stringInfoCombatSp != null) {
                stringInfoCombatSp = new CustomCombat(context).customStringCombat_Constellation(stringInfoCombatSp);
                infoCombatSp.setText(Html.fromHtml(stringInfoCombatSp, Html.FROM_HTML_MODE_LEGACY));
            }
            if (stringDescriptionCombatSp != null)
                descCombatSp.setText(stringDescriptionCombatSp);
        }
    }

    public void loadAttributeCombatSp(Character character) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_attribute_combat);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(false);

            //Recycler view attribute combat
            RecyclerView recyclerLabelAndLevel = dialog.findViewById(R.id.recycler_attribute_combat);
            recyclerLabelAndLevel.setNestedScrollingEnabled(false);
            ArrayList<LabelAndLever> arrayLabelAndLevel = new ArrayList<>();
            AttributeCombatAdapter adapterLabelAndLevel = new AttributeCombatAdapter(context, arrayLabelAndLevel);
            customCombat.getAttributeCombatSp(character, arrayLabelAndLevel, adapterLabelAndLevel);
            LinearLayoutManager layoutManagerCostsTalent = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recyclerLabelAndLevel.setLayoutManager(layoutManagerCostsTalent);
            recyclerLabelAndLevel.setAdapter(adapterLabelAndLevel);

            //bt yes
            TextView yes = dialog.findViewById(R.id.bt_yes_dialog);
            yes.setOnClickListener(v1 -> dialog.dismiss());
        }
        dialog.show();
    }

    public void loadPassive1(Character character, ImageView imagePassive1, TextView namePassive1, TextView infoPassive1) {
        String linkImagePassive1;
        if (character.getTalents().getImages() != null){
            linkImagePassive1  = character.getTalents().getImages().getPassive1();
        } else {
            linkImagePassive1 = "";
        }
        String stringNamePassive1 = character.getTalents().getPassive1().getName();
        String stringInfoPassive1 = character.getTalents().getPassive1().getInfo();

        Glide.with(context)
                .load("https://res.cloudinary.com/genshin/image/upload/sprites/" + linkImagePassive1 + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.skill_fail)
                .into(imagePassive1);

        if (stringNamePassive1 != null)
            namePassive1.setText(stringNamePassive1);

        if (stringInfoPassive1 != null) {
            stringInfoPassive1 = new CustomCombat(context).customStringCombat_Constellation(stringInfoPassive1);
            infoPassive1.setText(Html.fromHtml(stringInfoPassive1, Html.FROM_HTML_MODE_LEGACY));
        }
    }

    public void loadPassive2(Character character, ImageView imagePassive2, TextView namePassive2, TextView infoPassive2) {
        String linkImagePassive2;
        if (character.getTalents().getImages() != null){
            linkImagePassive2 = character.getTalents().getImages().getPassive2();
        } else {
            linkImagePassive2 = "";
        }
        String stringNamePassive2 = character.getTalents().getPassive2().getName();
        String stringInfoPassive2 = character.getTalents().getPassive2().getInfo();
        Glide.with(context)
                .load("https://res.cloudinary.com/genshin/image/upload/sprites/" + linkImagePassive2 + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.skill_fail)
                .into(imagePassive2);

        if (stringNamePassive2 != null)
            namePassive2.setText(stringNamePassive2);

        if (stringInfoPassive2 != null) {
            stringInfoPassive2 = new CustomCombat(context).customStringCombat_Constellation(stringInfoPassive2);
            infoPassive2.setText(Html.fromHtml(stringInfoPassive2, Html.FROM_HTML_MODE_LEGACY));
        }
    }

    public void loadPassive3(Character character, ImageView imagePassive3, TextView namePassive3, TextView infoPassive3) {
        String linkImagePassive3;
        if (character.getTalents().getImages() != null){
            linkImagePassive3 = character.getTalents().getImages().getPassive3();
        } else {
            linkImagePassive3 = "";
        }
        String stringNamePassive3 = character.getTalents().getPassive3().getName();
        String stringInfoPassive3 = character.getTalents().getPassive3().getInfo();
        Glide.with(context)
                .load("https://res.cloudinary.com/genshin/image/upload/sprites/" + linkImagePassive3 + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.skill_fail)
                .into(imagePassive3);

        if (stringNamePassive3 != null)
            namePassive3.setText(stringNamePassive3);

        if (stringInfoPassive3 != null) {
            stringInfoPassive3 = new CustomCombat(context).customStringCombat_Constellation(stringInfoPassive3);
            infoPassive3.setText(Html.fromHtml(stringInfoPassive3, Html.FROM_HTML_MODE_LEGACY));
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadIndexCharacter(Character character, ArrayList<StatsCharacter> arrayIndexCharacter, IndexCharacterAdapter adapterIndexCharacter) {
        String[] arrayLvKey = {"1", "20", "20+", "40", "40+", "50", "50+", "60", "60+", "70", "70+", "80", "80+", "90"};
        if (character.getStats() != null) {
            arrayIndexCharacter.add(new StatsCharacter(character.getSubstat()));
            for (int i = 0; i < character.getStats().size(); i++) {
                int ascension = character.getStats().get(i).getAscension();
                int lv = character.getStats().get(i).getLevel();
                double atk = (double) Math.round(character.getStats().get(i).getAttack());
                double hp = (double) Math.round(character.getStats().get(i).getHp());
                double def = (double) Math.round(character.getStats().get(i).getDefense());
                //xử lý nhân 100 nếu chỉ số k phải tinh thông nguyên tố
                double specialized = new CustomStringLanguage(context).handlerIndexSubStatsCharacter(character, character.getStats().get(i).getSpecialized());

                arrayIndexCharacter.add(new StatsCharacter(ascension, atk, def, hp, lv, specialized, arrayLvKey[i]));
            }
            adapterIndexCharacter.notifyDataSetChanged();
        } else anInterface.fail("INDEX_NULL");
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadCostsTalent(Character character, ArrayList<ItemCostsTalent> arrayCostsTalent, CostsTalentAdapter adapterCostsTalents) {
        arrayCostsTalent.add(new ItemCostsTalent());
        arrayCostsTalent.add(new ItemCostsTalent("2", character.getTalents().getCosts().getLvl2()));
        arrayCostsTalent.add(new ItemCostsTalent("3", character.getTalents().getCosts().getLvl3()));
        arrayCostsTalent.add(new ItemCostsTalent("4", character.getTalents().getCosts().getLvl4()));
        arrayCostsTalent.add(new ItemCostsTalent("5", character.getTalents().getCosts().getLvl5()));
        arrayCostsTalent.add(new ItemCostsTalent("6", character.getTalents().getCosts().getLvl6()));
        arrayCostsTalent.add(new ItemCostsTalent("7", character.getTalents().getCosts().getLvl7()));
        arrayCostsTalent.add(new ItemCostsTalent("8", character.getTalents().getCosts().getLvl8()));
        arrayCostsTalent.add(new ItemCostsTalent("9", character.getTalents().getCosts().getLvl9()));
        arrayCostsTalent.add(new ItemCostsTalent("10", character.getTalents().getCosts().getLvl10()));
        adapterCostsTalents.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadConstellation(Character character, ArrayList<Constellation> arrayConstellation, ConstellationAdapter adapterConstellation) {
        arrayConstellation.add(new Constellation(character.getConstellations().getC1().getName(),
                character.getConstellations().getC1().getEffect(), character.getConstellations().getImages().getC1()));

        arrayConstellation.add(new Constellation(character.getConstellations().getC2().getName(),
                character.getConstellations().getC2().getEffect(), character.getConstellations().getImages().getC2()));

        arrayConstellation.add(new Constellation(character.getConstellations().getC3().getName(),
                character.getConstellations().getC3().getEffect(), character.getConstellations().getImages().getC3()));

        arrayConstellation.add(new Constellation(character.getConstellations().getC4().getName(),
                character.getConstellations().getC4().getEffect(), character.getConstellations().getImages().getC4()));

        arrayConstellation.add(new Constellation(character.getConstellations().getC5().getName(),
                character.getConstellations().getC5().getEffect(), character.getConstellations().getImages().getC5()));

        arrayConstellation.add(new Constellation(character.getConstellations().getC6().getName(),
                character.getConstellations().getC6().getEffect(), character.getConstellations().getImages().getC6()));

        adapterConstellation.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadCostsAscendCharacter(Character character, ArrayList<ItemCostsAscend> arrayCostsCharacter, CostsAscendCharacterAdapter adapterCostsCharacter) {
        arrayCostsCharacter.add(new ItemCostsAscend());
        arrayCostsCharacter.add(new ItemCostsAscend("1", "20", character.getCosts().getAscend1()));
        arrayCostsCharacter.add(new ItemCostsAscend("2", "40", character.getCosts().getAscend2()));
        arrayCostsCharacter.add(new ItemCostsAscend("3", "50", character.getCosts().getAscend3()));
        arrayCostsCharacter.add(new ItemCostsAscend("4", "60", character.getCosts().getAscend4()));
        arrayCostsCharacter.add(new ItemCostsAscend("5", "70", character.getCosts().getAscend5()));
        arrayCostsCharacter.add(new ItemCostsAscend("6", "80", character.getCosts().getAscend6()));
        adapterCostsCharacter.notifyDataSetChanged();
    }
}
