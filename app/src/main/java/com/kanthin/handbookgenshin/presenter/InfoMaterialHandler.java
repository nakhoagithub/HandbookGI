package com.kanthin.handbookgenshin.presenter;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.material.Material;
import com.kanthin.handbookgenshin.presenter.anInterface.InfoMaterialInterface;

public class InfoMaterialHandler {
    private final Context context;
    private final InfoMaterialInterface anInterface;

    public InfoMaterialHandler(Context context, InfoMaterialInterface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    private String getLinkImage(Material material){
        String link_nameicon = material.getImages().getNameicon();
        String link_fandom = material.getImages().getFandom();
        if (link_fandom != null){
            return link_fandom;
        } else return "https://res.cloudinary.com/genshin/image/upload/sprites/" + link_nameicon + ".png";
    }

    public void loadMaterial(Material material, RelativeLayout backgroundItem, TextView nameItem, TextView nameInfo,
                             ImageView imageItem, ImageView imageRarity, TextView type, TextView description) {
        if (material.getName() != null) {
            nameItem.setText(material.getName());
            nameInfo.setText(material.getName());
        }
        Glide.with(context)
                .load(getLinkImage(material))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_null)
                .into(imageItem);


        if (material.getRarity() != null) {
            switch (material.getRarity()) {
                case "1":
                    backgroundItem.setBackgroundResource(R.drawable.rarity_1_background);
                    imageRarity.setImageResource(R.drawable.ic_1s);
                    break;
                case "2":
                    backgroundItem.setBackgroundResource(R.drawable.rarity_2_background);
                    imageRarity.setImageResource(R.drawable.ic_2s);
                    break;
                case "3":
                    backgroundItem.setBackgroundResource(R.drawable.rarity_3_background);
                    imageRarity.setImageResource(R.drawable.ic_3s);
                    break;
                case "4":
                    backgroundItem.setBackgroundResource(R.drawable.rarity_4_background);
                    imageRarity.setImageResource(R.drawable.ic_4s);
                    break;
                case "5":
                    backgroundItem.setBackgroundResource(R.drawable.rarity_5_background);
                    imageRarity.setImageResource(R.drawable.ic_5s);
                    break;
            }
        } else {
            backgroundItem.setBackgroundResource(R.drawable.rarity_1_background);
            imageRarity.setImageResource(R.drawable.ic_question);
        }
        if (material.getMaterialtype() != null) {
            type.setText(material.getMaterialtype());
        }
        if (material.getDescription() != null) {
            if (!material.getDescription().equals("")) {
                String sDescription = new CustomCombat(context).customStringDescription(material.getDescription());
                description.setText(Html.fromHtml(sDescription, Html.FROM_HTML_MODE_LEGACY));
            } else {
                description.setText(context.getResources().getString(R.string.not));
            }
        } else description.setText(context.getResources().getString(R.string.not));


        anInterface.success();
    }

    public void loadSource(Material material, TextView dropdomain, TextView dayOpenDungeno, TextView source) {
        if (material.getDropdomain() != null){
            dropdomain.setText(material.getDropdomain());
        } else dropdomain.setText(context.getResources().getString(R.string.not));

        if (material.getDaysofweek() != null){
            StringBuilder sDaysofweek = new StringBuilder();
            for (int i = 0; i < material.getDaysofweek().size(); i++) {
                if (i != material.getDaysofweek().size() - 1) {
                    sDaysofweek.append(material.getDaysofweek().get(i)).append("<br>");
                } else {
                    sDaysofweek.append(material.getDaysofweek().get(i));
                }
            }
            dayOpenDungeno.setText(Html.fromHtml(String.valueOf(sDaysofweek), Html.FROM_HTML_MODE_LEGACY));
        } else dayOpenDungeno.setText(context.getResources().getString(R.string.not));

        if (material.getSource() != null){
            StringBuilder sSource = new StringBuilder();
            for (int i = 0; i < material.getSource().size(); i++) {
                if (i != material.getSource().size() - 1) {
                    sSource.append(material.getSource().get(i)).append("<br>");
                } else {
                    sSource.append(material.getSource().get(i));
                }
            }
            source.setText(Html.fromHtml(String.valueOf(sSource), Html.FROM_HTML_MODE_LEGACY));
        } else source.setText(context.getResources().getString(R.string.not));
    }
}
