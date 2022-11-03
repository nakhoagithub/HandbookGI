package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.adapter.material.MaterialAdapter;
import com.kanthin.handbookgenshin.model.material.Material;
import com.kanthin.handbookgenshin.presenter.anInterface.MaterialInterface;

import java.util.ArrayList;

public class MaterialHandler {
    private final Context context;
    private final MaterialInterface materialInterface;
    private final String language;
    private final TextView countItem;


    public MaterialHandler(Context context, MaterialInterface anInterface, TextView countItem) {
        this.context = context;
        this.materialInterface = anInterface;
        this.countItem = countItem;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        language = sharedPreferences.getString("language", "en");
    }

    public void loadMaterial(ArrayList<Material> arrayNotChange, ArrayList<Material> array, MaterialAdapter adapter) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language);
        database.child("materials").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Material material = dataSnapshot.getValue(Material.class);
                    if (material != null) {
                        array.add(material);
                        arrayNotChange.add(material);
                    }
                    adapter.notifyDataSetChanged();
                }
                countItem.setText(String.valueOf(array.size()));
                array.sort((material, t1) -> ComparisonChain.start()
                        .compare(material.getSortorder(), t1.getSortorder(), Ordering.natural().nullsFirst())
                        .result());
                adapter.notifyDataSetChanged();
                adapter.setShowShimmer(false);
                materialInterface.success();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
