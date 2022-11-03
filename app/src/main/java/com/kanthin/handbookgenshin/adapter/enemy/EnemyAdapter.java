package com.kanthin.handbookgenshin.adapter.enemy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.kanthin.handbookgenshin.model.index.IndexEnemy;
import com.kanthin.handbookgenshin.view.InfoEnemyActivity;

import java.util.ArrayList;

public class EnemyAdapter extends RecyclerView.Adapter<EnemyAdapter.EnemyViewHolder> {

    private final Context context;
    private final ArrayList<IndexEnemy> array;
    private final String language;
    private boolean showShimmer = true;
    private int lastPosition = -1;

    public EnemyAdapter(Context context, ArrayList<IndexEnemy> array) {
        this.context = context;
        this.array = array;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        language = sharedPreferences.getString("language", "en");
    }

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }

    @NonNull
    @Override
    public EnemyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_enemy_grid_view, parent, false);
        return new EnemyViewHolder(v, array, language);
    }

    @Override
    public void onBindViewHolder(@NonNull EnemyViewHolder holder, int position) {
        if (showShimmer) {
            holder.backgroundShimmer.startShimmer();
        } else {
            setAnimation(holder.itemView, position);

            holder.backgroundShimmer.stopShimmer();
            holder.backgroundShimmer.setShimmer(null);
            holder.background.setBackground(null);
            holder.setShowShimmer(false);

            IndexEnemy indexEnemy = array.get(position);
            if (indexEnemy != null){
                if (language.equals("vi")) holder.name.setText(indexEnemy.getVi());
                else holder.name.setText(indexEnemy.getEn());

                Glide.with(context.getApplicationContext())
                        .load(getLinkImage(indexEnemy))
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.image);
                holder.background.setBackgroundResource(R.drawable.rarity_1_background);
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

    static class EnemyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ShimmerFrameLayout backgroundShimmer;
        private final ImageView image;
        private final TextView name;
        private final RelativeLayout background;

        private final ArrayList<IndexEnemy> array;
        private final String lang;
        private boolean showShimmer = true;
        private final Context context;

        public void setShowShimmer(boolean showShimmer) {
            this.showShimmer = showShimmer;
        }

        public EnemyViewHolder(@NonNull View itemView, ArrayList<IndexEnemy> array, String lang) {
            super(itemView);
            this.array = array;
            this.lang = lang;
            this.context = itemView.getContext();
            image = itemView.findViewById(R.id.image_item_enemy);
            name = itemView.findViewById(R.id.name_item_enemy);
            backgroundShimmer = itemView.findViewById(R.id.shimmer_item_enemy);
            background = itemView.findViewById(R.id.background_item_enemy);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (!showShimmer) {
                if (array != null) {
                    Intent i = new Intent(context, InfoEnemyActivity.class);
                    if (lang.equals("vi"))
                        i.putExtra("name", array.get(getAdapterPosition()).getVi());
                    else i.putExtra("name", array.get(getAdapterPosition()).getEn());
                    context.startActivity(i);
                    ((Activity) context).overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
                }
            }
        }
    }

    private String getLinkImage(IndexEnemy enemy){
        String link_nameicon = enemy.getImages().getNameicon();
        return "https://res.cloudinary.com/genshin/image/upload/sprites/" + link_nameicon + ".png";
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.down_to_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
