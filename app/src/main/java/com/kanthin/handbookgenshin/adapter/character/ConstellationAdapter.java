package com.kanthin.handbookgenshin.adapter.character;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.presenter.CustomCombat;
import com.kanthin.handbookgenshin.model.character.Constellation;

import java.util.ArrayList;

public class ConstellationAdapter extends RecyclerView.Adapter<ConstellationAdapter.ConstellationViewHolder> {

    private final Context context;
    private final ArrayList<Constellation> array;

    public ConstellationAdapter(Context context, ArrayList<Constellation> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public ConstellationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_constellation_character, parent, false);
        return new ConstellationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConstellationViewHolder holder, int position) {
        Constellation constellation = array.get(position);
        if (constellation != null) {
            if (constellation.getName() != null)
                holder.name.setText(constellation.getName());
            if (constellation.getImage() != null) {
                Glide.with(context)
                        .load(constellation.getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .error(R.drawable.skill_fail)
                        .into(holder.image);
            }
            if (constellation.getEffect() != null){
                String customEffect = new CustomCombat(context).customStringCombat_Constellation(constellation.getEffect());
                holder.effect.setText(Html.fromHtml(customEffect, Html.FROM_HTML_MODE_LEGACY));
            }
        }
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }

    static class ConstellationViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;
        private final TextView effect;

        public ConstellationViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_constellation_character);
            name = itemView.findViewById(R.id.name_constellation_character);
            effect = itemView.findViewById(R.id.effect_constellation_character);
        }
    }
}
