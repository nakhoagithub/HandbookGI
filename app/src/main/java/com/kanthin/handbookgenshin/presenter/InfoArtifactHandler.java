package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.artifact.Artifact;
import com.kanthin.handbookgenshin.presenter.anInterface.InfoArtifactInterface;

public class InfoArtifactHandler {
    private final Context context;
    private final InfoArtifactInterface anInterface;

    public InfoArtifactHandler(Context context, InfoArtifactInterface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    @SuppressLint("SetTextI18n")
    public void loadInfo(Artifact artifact, TextView nameArtifact, TextView attribute1, TextView attribute2) {
        if (artifact.getName() != null)
            nameArtifact.setText(artifact.getName());
        // attribute
        if (artifact.getSet2() != null) {
            attribute1.setText(context.getResources().getString(R.string.set2) + ": " + artifact.getSet2());
            attribute2.setVisibility(View.VISIBLE);
            attribute2.setText(context.getResources().getString(R.string.set4) + ": " + artifact.getSet4());
        } else {
            attribute1.setText(context.getResources().getString(R.string.set1) + ": " + artifact.getSet1());
            attribute2.setVisibility(View.GONE);
        }
    }

    public void loadRarity(Artifact artifact, TextView rarity) {
        StringBuilder sRarity = new StringBuilder();
        for (int i = 0; i < artifact.getRarity().size(); i++) {
            if (i != artifact.getRarity().size() - 1) {
                sRarity.append(artifact.getRarity().get(i)).append(" ").append(context.getResources().getString(R.string.star)).append(", ");
            } else {
                sRarity.append(artifact.getRarity().get(i)).append(" ").append(context.getResources().getString(R.string.star)).append(".");
            }
        }
        rarity.setText(sRarity);
    }

    public void loadInfoSet(Artifact artifact, TextView name1, TextView name2, TextView name3, TextView name4, TextView name5, ImageView image1, ImageView image2, ImageView image3, ImageView image4, ImageView image5, TextView relic1, TextView relic2, TextView relic3, TextView relic4, TextView relic5, TextView description1, TextView description2, TextView description3, TextView description4, TextView description5, CardView card1, CardView card2, CardView card3, CardView card4, CardView card5) {
        Glide.with(context)
                .load(artifact.getImages().getFlower())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_null)
                .into(image1);
        Glide.with(context)
                .load(artifact.getImages().getPlume())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_null)
                .into(image2);
        Glide.with(context)
                .load(artifact.getImages().getSands())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_null)
                .into(image3);
        Glide.with(context)
                .load(artifact.getImages().getGoblet())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_null)
                .into(image4);
        Glide.with(context)
                .load(artifact.getImages().getCirclet())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_null)
                .into(image5);

        if (artifact.getFlower() != null) {
            name1.setText(artifact.getFlower().getName());
            relic1.setText(artifact.getFlower().getRelictype());
            description1.setText(artifact.getFlower().getDescription());
        } else {
            card1.setVisibility(View.GONE);
        }

        if (artifact.getPlume() != null) {
            name2.setText(artifact.getPlume().getName());
            relic2.setText(artifact.getPlume().getRelictype());
            description2.setText(artifact.getPlume().getDescription());
        } else {
            card2.setVisibility(View.GONE);
        }

        if (artifact.getSands() != null) {
            name3.setText(artifact.getSands().getName());
            relic3.setText(artifact.getSands().getRelictype());
            description3.setText(artifact.getSands().getDescription());
        } else {
            card3.setVisibility(View.GONE);
        }

        if (artifact.getGoblet() != null) {
            name4.setText(artifact.getGoblet().getName());
            relic4.setText(artifact.getGoblet().getRelictype());
            description4.setText(artifact.getGoblet().getDescription());
        } else {
            card4.setVisibility(View.GONE);
        }

        if (artifact.getCirclet() != null) {
            name5.setText(artifact.getCirclet().getName());
            relic5.setText(artifact.getCirclet().getRelictype());
            description5.setText(artifact.getCirclet().getDescription());
        } else {
            card5.setVisibility(View.GONE);
        }
    }

}
