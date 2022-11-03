package com.kanthin.handbookgenshin.adapter.material;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.material.Material;
import com.kanthin.handbookgenshin.view.InfoMaterialActivity;

import java.util.ArrayList;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {

    private final Context context;
    private final ArrayList<Material> array;
    private final int layout;
    private boolean showShimmer = true;
    private int lastPosition = -1;

    public MaterialAdapter(Context context, ArrayList<Material> array, int layout) {
        this.context = context;
        this.array = array;
        this.layout = layout;
    }

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        return new MaterialViewHolder(v, array);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        if (showShimmer) {
            holder.backgroundShimmer.startShimmer();
        } else {
            setAnimation(holder.itemView, position);

            holder.backgroundShimmer.stopShimmer();
            holder.backgroundShimmer.setShimmer(null);
            holder.relative.setBackground(null);
            holder.setShowShimmer(false);

            Material material = array.get(position);
            if (material != null) {
                if (material.getRarity() != null) {
                    switch (material.getRarity()) {
                        case "1":
                            holder.relative.setBackgroundResource(R.drawable.rarity_1_background);
                            holder.imageRarity.setImageResource(R.drawable.ic_1s);
                            break;
                        case "2":
                            holder.relative.setBackgroundResource(R.drawable.rarity_2_background);
                            holder.imageRarity.setImageResource(R.drawable.ic_2s);
                            break;
                        case "3":
                            holder.relative.setBackgroundResource(R.drawable.rarity_3_background);
                            holder.imageRarity.setImageResource(R.drawable.ic_3s);
                            break;
                        case "4":
                            holder.relative.setBackgroundResource(R.drawable.rarity_4_background);
                            holder.imageRarity.setImageResource(R.drawable.ic_4s);
                            break;
                        case "5":
                            holder.relative.setBackgroundResource(R.drawable.rarity_5_background);
                            holder.imageRarity.setImageResource(R.drawable.ic_5s);
                            break;
                    }
                } else{
                    holder.relative.setBackgroundResource(R.drawable.rarity_1_background);
                    holder.imageRarity.setImageDrawable(null);
                }

                holder.name.setText(material.getName());
                Glide.with(context)
                        .load(getLinkImage(material))
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_null)
                        .into(holder.image);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (showShimmer) {
            return 9;
        } else {
            return array != null ? array.size() : 0;
        }
    }

    static class MaterialViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ArrayList<Material> array;
        private final Context context;
        private final ShimmerFrameLayout backgroundShimmer;
        private final RelativeLayout relative;
        private final ImageView image;
        private final ImageView imageRarity;
        private final TextView name;
        private boolean showShimmer = true;

        public void setShowShimmer(boolean showShimmer) {
            this.showShimmer = showShimmer;
        }

        public MaterialViewHolder(@NonNull View itemView, ArrayList<Material> array) {
            super(itemView);
            this.array = array;
            this.context = itemView.getContext();
            backgroundShimmer = itemView.findViewById(R.id.shimmer_item_material);
            relative = itemView.findViewById(R.id.background_item_material);
            image = itemView.findViewById(R.id.image_item_material);
            imageRarity = itemView.findViewById(R.id.rarity_item_material);
            name = itemView.findViewById(R.id.name_item_material);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (!showShimmer) {
                if (array != null) {
                    Intent i = new Intent(view.getContext(), InfoMaterialActivity.class);
                    i.putExtra("material", array.get(getAdapterPosition()));
                    context.startActivity(i);
                    ((Activity) context).overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
                }
            }
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.down_to_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    private String getLinkImage(Material material){
        String link_nameicon = material.getImages().getNameicon();
        String link_fandom = material.getImages().getFandom();
        if (link_fandom != null){
            return link_fandom;
        } else return "https://res.cloudinary.com/genshin/image/upload/sprites/" + link_nameicon + ".png";
    }
}
