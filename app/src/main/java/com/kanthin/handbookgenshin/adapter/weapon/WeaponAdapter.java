package com.kanthin.handbookgenshin.adapter.weapon;

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
import com.kanthin.handbookgenshin.model.weapon.Weapon;
import com.kanthin.handbookgenshin.view.InfoWeaponActivity;

import java.util.ArrayList;

public class WeaponAdapter extends RecyclerView.Adapter<WeaponAdapter.WeaponViewHolder> {
    private final Context context;
    private final ArrayList<Weapon> array;
    private final int layout;
    private boolean showShimmer = true;
    private int lastPosition = -1;

    public WeaponAdapter(Context context, ArrayList<Weapon> array, int layout) {
        this.context = context;
        this.array = array;
        this.layout = layout;
    }

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }

    @NonNull
    @Override
    public WeaponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        return new WeaponViewHolder(v, array, context);
    }

    @Override
    public void onBindViewHolder(@NonNull WeaponViewHolder holder, int position) {
        if (showShimmer) {
            holder.backgroundShimmer.startShimmer();
        } else {
            setAnimation(holder.itemView, position);

            holder.backgroundShimmer.stopShimmer();
            holder.backgroundShimmer.setShimmer(null);
            holder.relative.setBackground(null);
            holder.setShowShimmer(false);

            Weapon weapon = array.get(position);
            if (weapon != null) {
                //background

                if (weapon.getRarity() != null) {
                    switch (weapon.getRarity()) {
                        case "1":
                            holder.relative.setBackgroundResource(R.drawable.rarity_1_background);
                            break;
                        case "2":
                            holder.relative.setBackgroundResource(R.drawable.rarity_2_background);
                            break;
                        case "3":
                            holder.relative.setBackgroundResource(R.drawable.rarity_3_background);
                            break;
                        case "4":
                            holder.relative.setBackgroundResource(R.drawable.rarity_4_background);
                            break;
                        case "5":
                            holder.relative.setBackgroundResource(R.drawable.rarity_5_background);
                            break;
                    }
                }

                if (weapon.getWeapontype() != null) {
                    if (weapon.getWeapontype().contains(context.getResources().getString(R.string.bow))){
                        holder.imageType.setImageResource(R.drawable.bow);
                    }
                    if (weapon.getWeapontype().contains(context.getResources().getString(R.string.sword))){
                        holder.imageType.setImageResource(R.drawable.sword);
                    }
                    if (weapon.getWeapontype().contains(context.getResources().getString(R.string.catalyst))){
                        holder.imageType.setImageResource(R.drawable.catalyst);
                    }
                    if (weapon.getWeapontype().contains(context.getResources().getString(R.string.polearm))){
                        holder.imageType.setImageResource(R.drawable.polearm);
                    }
                    if (weapon.getWeapontype().contains(context.getResources().getString(R.string.claymore))){
                        holder.imageType.setImageResource(R.drawable.claymore);
                    }
                }

                holder.name.setText(weapon.getName());
                Glide.with(context)
                        .load(getLinkImage(weapon))
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_null)
                        .into(holder.image);
            }
        }
    }

    private String getLinkImage(Weapon weapon){
        String link_nameicon = weapon.getImages().getNameicon();
        String link_icon = weapon.getImages().getIcon();
        if (link_icon != null){
            return link_icon;
        } else return "https://res.cloudinary.com/genshin/image/upload/sprites/" + link_nameicon + ".png";
    }

    @Override
    public int getItemCount() {
        if (showShimmer) {
            return 9;
        } else {
            return array != null ? array.size() : 0;
        }
    }

    static class WeaponViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ArrayList<Weapon> array;
        private final Context context;
        private final ShimmerFrameLayout backgroundShimmer;
        private final RelativeLayout relative;
        private final ImageView image;
        private final ImageView imageType;
        private final TextView name;
        private boolean showShimmer = true;

        public void setShowShimmer(boolean showShimmer) {
            this.showShimmer = showShimmer;
        }

        public WeaponViewHolder(@NonNull View itemView, ArrayList<Weapon> array, Context context) {
            super(itemView);
            this.array = array;
            this.context = context;
            backgroundShimmer = itemView.findViewById(R.id.shimmer_item_weapon);
            relative = itemView.findViewById(R.id.relative_item_weapon);
            image = itemView.findViewById(R.id.image_item_weapon);
            imageType = itemView.findViewById(R.id.type_item_weapon);
            name = itemView.findViewById(R.id.name_item_weapon);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (!showShimmer) {
                if (array != null) {
                    Intent i = new Intent(view.getContext(), InfoWeaponActivity.class);
                    i.putExtra("weapon", array.get(getAdapterPosition()));
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
}
