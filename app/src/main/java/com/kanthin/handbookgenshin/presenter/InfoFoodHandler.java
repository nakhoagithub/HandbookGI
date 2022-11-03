package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.MaterialUpAdapter;
import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;
import com.kanthin.handbookgenshin.model.food.Food;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class InfoFoodHandler {
    private final Context context;
    private final Interface anInterface;
    private final String lang;

    public InfoFoodHandler(Context context, Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        lang = sharedPreferences.getString("language", "en");
    }

    public void viewFood(String name, ImageView imageItem, TextView type, TextView effect, TextView description,
                         RelativeLayout backgroundItem, ImageView imageRarity, ArrayList<ItemMaterial> arrayMaterial,
                         MaterialUpAdapter adapterMaterial, CardView cardDelicious, CardView cardNormal, CardView cardSuspicious, TextView effectDelicious, TextView effectNormal, TextView effectSuspicious, TextView descriptionDelicious, TextView descriptionNormal, TextView descriptionSuspicious){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child(lang).child("foods").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Food food = snapshot.getValue(Food.class);
                if (food != null){
                    type.setText(food.getFoodfilter());
                    effect.setText(food.getEffect());
                    description.setText(food.getDescription());
                    getImageFood(food.getKey(), imageItem);
                    getRarityFood(food, backgroundItem, imageRarity);
                    getMaterialFood(food, arrayMaterial, adapterMaterial);
                    getLevelFood(food, cardDelicious, cardNormal, cardSuspicious, effectDelicious, effectNormal,
                            effectSuspicious, descriptionDelicious, descriptionNormal, descriptionSuspicious);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getImageFood(String key, ImageView image) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("images").child("foods").child(key).child("icon").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Glide.with(context.getApplicationContext())
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

    private void getRarityFood(Food food, RelativeLayout backgroundItem, ImageView imageRarity) {
        if (food.getRarity() != null) {
            switch (food.getRarity()) {
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
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getMaterialFood(Food food, ArrayList<ItemMaterial> arrayMaterial, MaterialUpAdapter adapterMaterial) {
        if (food.getIngredients() != null){
            arrayMaterial.addAll(food.getIngredients());
            adapterMaterial.notifyDataSetChanged();
        }
    }

    private void getLevelFood(Food food, CardView cardDelicious, CardView cardNormal, CardView cardSuspicious,
                              TextView effectDelicious, TextView effectNormal, TextView effectSuspicious,
                              TextView descriptionDelicious, TextView descriptionNormal, TextView descriptionSuspicious){
        if (food.getDelicious() != null){
            effectDelicious.setText(food.getDelicious().getEffect());
            descriptionDelicious.setText(food.getDelicious().getDescription());
        } else cardDelicious.setVisibility(View.GONE);

        if (food.getNormal() != null){
            effectNormal.setText(food.getNormal().getEffect());
            descriptionNormal.setText(food.getNormal().getDescription());
        } else cardNormal.setVisibility(View.GONE);

        if (food.getSuspicious() != null){
            effectSuspicious.setText(food.getSuspicious().getEffect());
            descriptionSuspicious.setText(food.getSuspicious().getDescription());
        } else cardSuspicious.setVisibility(View.GONE);
    }
}
