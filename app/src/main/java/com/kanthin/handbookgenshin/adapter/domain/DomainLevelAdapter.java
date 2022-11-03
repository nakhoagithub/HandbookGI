package com.kanthin.handbookgenshin.adapter.domain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;
import com.kanthin.handbookgenshin.model.domain.LevelDomain;

import java.util.ArrayList;
import java.util.Collections;

public class DomainLevelAdapter extends RecyclerView.Adapter<DomainLevelAdapter.LevelViewHolder> {

    private final Context context;
    private final ArrayList<LevelDomain> array;
    private int lastPosition = -1;

    public DomainLevelAdapter(Context context, ArrayList<LevelDomain> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_level_domain, parent, false);
        return new LevelViewHolder(v);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        LevelDomain levelDomain = array.get(position);
        if (levelDomain != null) {
            setAnimation(holder.itemView, position);

            holder.name.setText(levelDomain.getName());
            if (levelDomain.getDisorder() != null){
                getDisorder(levelDomain.getDisorder(), holder.disorder);
            } else {
                holder.disorder.setText(context.getString(R.string.not));
            }
            getElement(levelDomain.getRecommendedelements(), holder.notRecommendedElement, holder.element1, holder.element2, holder.element3, holder.element4);
            holder.level.setText(String.valueOf(levelDomain.getRecommendedlevel()));
            holder.rank.setText(String.valueOf(levelDomain.getUnlockrank()));

            ArrayList<ItemMaterial> arrayMaterialUp = new ArrayList<>(levelDomain.getRewardpreview());
            //đảo ngược list
            Collections.reverse(arrayMaterialUp);
            //adapter
            MaterialInDomainAdapter adapterInDomain = new MaterialInDomainAdapter(context, arrayMaterialUp);
            //layout manager
            LinearLayoutManager layoutManagerMaterialUp = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            holder.recyclerItem.setLayoutManager(layoutManagerMaterialUp);
            holder.recyclerItem.setAdapter(adapterInDomain);
            adapterInDomain.notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }

    static class LevelViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView disorder;
        private final ImageView element1;
        private final ImageView element2;
        private final ImageView element3;
        private final ImageView element4;
        private final TextView notRecommendedElement;
        private final TextView level;
        private final TextView rank;
        private final RecyclerView recyclerItem;

        public LevelViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_level_domain);
            disorder = itemView.findViewById(R.id.disorder_level_domain);
            element1 = itemView.findViewById(R.id.element1_level_domain);
            element2 = itemView.findViewById(R.id.element2_level_domain);
            element3 = itemView.findViewById(R.id.element3_level_domain);
            element4 = itemView.findViewById(R.id.element4_level_domain);
            notRecommendedElement = itemView.findViewById(R.id.not_recommended_element);
            level = itemView.findViewById(R.id.recommended_level_domain);
            rank = itemView.findViewById(R.id.recommended_rank_domain);
            recyclerItem = itemView.findViewById(R.id.recycler_item_level_domain);
        }
    }

    private void getDisorder(ArrayList<String> disorder, TextView textDisorder) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < disorder.size(); i++) {
            if (i != disorder.size() - 1) {
                string.append("- ").append(disorder.get(i)).append("\n");
            } else {
                string.append("- ").append(disorder.get(i));
            }
        }
        textDisorder.setText(string);
    }

    private void getElement(ArrayList<String> recommendedelements, TextView notRecommendedElement, ImageView element1, ImageView element2, ImageView element3, ImageView element4) {
        if (recommendedelements != null){
            notRecommendedElement.setVisibility(View.GONE);
            if (recommendedelements.size() == 1) {
                element1.setVisibility(View.VISIBLE);
                setImageFromKey(element1, recommendedelements.get(0));
                element2.setVisibility(View.GONE);
                element3.setVisibility(View.GONE);
                element4.setVisibility(View.GONE);
            }
            if (recommendedelements.size() == 2) {
                element1.setVisibility(View.VISIBLE);
                setImageFromKey(element1, recommendedelements.get(0));
                element2.setVisibility(View.VISIBLE);
                setImageFromKey(element2, recommendedelements.get(1));
                element3.setVisibility(View.GONE);
                element4.setVisibility(View.GONE);
            }
            if (recommendedelements.size() == 3) {
                element1.setVisibility(View.VISIBLE);
                setImageFromKey(element1, recommendedelements.get(0));
                element2.setVisibility(View.VISIBLE);
                setImageFromKey(element2, recommendedelements.get(1));
                element3.setVisibility(View.VISIBLE);
                setImageFromKey(element3, recommendedelements.get(2));
                element4.setVisibility(View.GONE);
            }
            if (recommendedelements.size() == 4) {
                element1.setVisibility(View.VISIBLE);
                setImageFromKey(element1, recommendedelements.get(0));
                element2.setVisibility(View.VISIBLE);
                setImageFromKey(element2, recommendedelements.get(1));
                element3.setVisibility(View.VISIBLE);
                setImageFromKey(element3, recommendedelements.get(2));
                element4.setVisibility(View.VISIBLE);
                setImageFromKey(element4, recommendedelements.get(3));
            }
        } else {
            notRecommendedElement.setVisibility(View.VISIBLE);
            element1.setVisibility(View.GONE);
            element2.setVisibility(View.GONE);
            element3.setVisibility(View.GONE);
            element4.setVisibility(View.GONE);
        }
    }

    private void setImageFromKey(ImageView image, String key) {
        if (key.contains(context.getString(R.string.geo))) {
            image.setImageResource(R.drawable.element_geo);
        }
        if (key.contains(context.getString(R.string.pyro))) {
            image.setImageResource(R.drawable.element_pyro);
        }
        if (key.contains(context.getString(R.string.hydro))) {
            image.setImageResource(R.drawable.element_hydro);
        }
        if (key.contains(context.getString(R.string.electro))) {
            image.setImageResource(R.drawable.element_electro);
        }
        if (key.contains(context.getString(R.string.cryo))) {
            image.setImageResource(R.drawable.element_cryo);
        }
        if (key.contains(context.getString(R.string.anemo))) {
            image.setImageResource(R.drawable.element_anemo);
        }
        if (key.contains(context.getString(R.string.dendro))) {
            image.setImageResource(R.drawable.element_dendro);
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
