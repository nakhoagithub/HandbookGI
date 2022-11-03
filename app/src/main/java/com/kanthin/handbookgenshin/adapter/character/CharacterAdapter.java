package com.kanthin.handbookgenshin.adapter.character;

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
import com.kanthin.handbookgenshin.model.character.Character;
import com.kanthin.handbookgenshin.view.InfoCharacterActivity;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private final Context context;
    private final ArrayList<Character> array;
    private final int layout;
    private boolean showShimmer = true;
    private int lastPosition = -1;

    public CharacterAdapter(Context context, ArrayList<Character> array, int layout) {
        this.context = context;
        this.array = array;
        this.layout = layout;
    }

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        return new CharacterViewHolder(v, array, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        if (showShimmer) {
            holder.backgroundShimmer.startShimmer();
        } else {
            setAnimation(holder.itemView, position);

            holder.backgroundShimmer.stopShimmer();
            holder.backgroundShimmer.setShimmer(null);
            holder.background.setBackground(null);
            holder.setShowShimmer(false);

            Character character = array.get(position);
            if (character != null) {
                //background
                if (character.getRarity().equals("5")) {
                    holder.background.setBackgroundResource(R.drawable.rarity_5_background);
                } else holder.background.setBackgroundResource(R.drawable.rarity_4_background);

                holder.name.setText(character.getName());

                if (character.getElement() != null) {
                    if (character.getElement().contains(context.getString(R.string.geo))) {
                        holder.element.setImageResource(R.drawable.element_geo);
                    }
                    if (character.getElement().contains(context.getString(R.string.pyro))) {
                        holder.element.setImageResource(R.drawable.element_pyro);
                    }
                    if (character.getElement().contains(context.getString(R.string.hydro))) {
                        holder.element.setImageResource(R.drawable.element_hydro);
                    }
                    if (character.getElement().contains(context.getString(R.string.electro))) {
                        holder.element.setImageResource(R.drawable.element_electro);
                    }
                    if (character.getElement().contains(context.getString(R.string.cryo))) {
                        holder.element.setImageResource(R.drawable.element_cryo);
                    }
                    if (character.getElement().contains(context.getString(R.string.anemo))) {
                        holder.element.setImageResource(R.drawable.element_anemo);
                    }
                    if (character.getElement().contains(context.getString(R.string.dendro))) {
                        holder.element.setImageResource(R.drawable.element_dendro);
                    }
                }
                Glide.with(context)
                        .load(character.getImages().getIcon())
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

    static class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ArrayList<Character> array;
        private final ShimmerFrameLayout backgroundShimmer;
        private final RelativeLayout background;
        private final ImageView element;
        private final ImageView image;
        private final TextView name;
        private boolean showShimmer = true;
        private final Context context;

        public void setShowShimmer(boolean showShimmer) {
            this.showShimmer = showShimmer;
        }

        public CharacterViewHolder(@NonNull View itemView, ArrayList<Character> array, Context context) {
            super(itemView);
            this.array = array;
            this.context = context;
            backgroundShimmer = itemView.findViewById(R.id.shimmer_item_character);
            background = itemView.findViewById(R.id.background_item_character);
            element = itemView.findViewById(R.id.element_item_character);
            image = itemView.findViewById(R.id.image_item_character);
            name = itemView.findViewById(R.id.name_item_character);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (!showShimmer) {
                if (array != null) {
                    Intent i = new Intent(view.getContext(), InfoCharacterActivity.class);
                    i.putExtra("character", array.get(getAdapterPosition()));
                    view.getContext().startActivity(i);
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
