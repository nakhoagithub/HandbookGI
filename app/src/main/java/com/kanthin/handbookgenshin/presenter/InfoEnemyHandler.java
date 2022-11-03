package com.kanthin.handbookgenshin.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.enemy.Enemy;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

public class InfoEnemyHandler {
    private final Context context;
    private Interface anInterface;
    private String lang;

    public InfoEnemyHandler(Context context, Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        lang = sharedPreferences.getString("language", "en");
    }

    private String getLinkImage(Enemy enemy){
        String link_nameicon = enemy.getImages().getNameicon();
        return "https://res.cloudinary.com/genshin/image/upload/sprites/" + link_nameicon + ".png";
    }

    public void viewEnemy(String name, ImageView imageItem, TextView category, TextView type, TextView description,
                          TextView specialName){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child(lang).child("enemies").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Enemy enemy = snapshot.getValue(Enemy.class);
                if (enemy != null){
                    category.setText(enemy.getCategory());
                    description.setText(enemy.getDescription());
                    specialName.setText(enemy.getSpecialname());
                    getTypeEnemy(enemy.getType(), type);

                    Glide.with(context.getApplicationContext())
                            .load(getLinkImage(enemy))
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_null)
                            .into(imageItem);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getTypeEnemy(String type, TextView textType) {
        switch (type){
            case "BOSS":
                textType.setText(context.getString(R.string.enemy_boss));
                break;
            case "COMMON":
                textType.setText(context.getString(R.string.enemy_common));
                break;
            case "ELITE":
                textType.setText(context.getString(R.string.enemy_elite));
                break;
        }
    }

}
