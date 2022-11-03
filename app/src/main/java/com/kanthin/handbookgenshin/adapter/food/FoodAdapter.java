package com.kanthin.handbookgenshin.adapter.food;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.index.IndexFood;
import com.kanthin.handbookgenshin.view.InfoFoodActivity;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final Context context;
    private final ArrayList<IndexFood> array;
    private final int layout;
    private boolean showShimmer = true;
    private int lastPosition = -1;

    private final String language;

    public FoodAdapter(Context context, ArrayList<IndexFood> array, int layout) {
        this.context = context;
        this.array = array;
        this.layout = layout;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        language = sharedPreferences.getString("language", "en");
    }

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        return new FoodViewHolder(v, array, language);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        if (showShimmer) {
            holder.backgroundShimmer.startShimmer();
        } else {
            setAnimation(holder.itemView, position);

            holder.backgroundShimmer.stopShimmer();
            holder.backgroundShimmer.setShimmer(null);
            holder.relative.setBackground(null);
            holder.setShowShimmer(false);

            IndexFood indexFood = array.get(position);
            if (indexFood != null) {
                if (indexFood.getRarity() != null) {
                    switch (indexFood.getRarity()) {
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

                if (language.equals("vi")){
                    holder.name.setText(indexFood.getVi());
                } else holder.name.setText(indexFood.getEn());

                getImageFood(indexFood.getVi(), holder.image);
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

    static class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ArrayList<IndexFood> array;
        private final Context context;
        private final ShimmerFrameLayout backgroundShimmer;
        private final RelativeLayout relative;
        private final ImageView image;
        private final ImageView imageRarity;
        private final TextView name;
        private boolean showShimmer = true;
        private final String lang;

        public void setShowShimmer(boolean showShimmer) {
            this.showShimmer = showShimmer;
        }

        public FoodViewHolder(@NonNull View itemView, ArrayList<IndexFood> array, String lang) {
            super(itemView);
            this.array = array;
            this.lang = lang;
            this.context = itemView.getContext();
            backgroundShimmer = itemView.findViewById(R.id.shimmer_item_food);
            relative = itemView.findViewById(R.id.background_item_food);
            image = itemView.findViewById(R.id.image_item_food);
            imageRarity = itemView.findViewById(R.id.rarity_item_food);
            name = itemView.findViewById(R.id.name_item_food);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (!showShimmer) {
                if (array != null) {
                    Intent i = new Intent(view.getContext(), InfoFoodActivity.class);
                    if (lang.equals("vi"))
                        i.putExtra("name", array.get(getAdapterPosition()).getVi());
                    else i.putExtra("name", array.get(getAdapterPosition()).getEn());
                    context.startActivity(i);
                    ((Activity) context).overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
                }
            }
        }
    }

    private void getImageFood(String name, ImageView image) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("images").child("foods").child(name).child("icon").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Glide.with(context)
                        .load(snapshot.getValue(String.class))
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_null)
                        .into(image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.down_to_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
