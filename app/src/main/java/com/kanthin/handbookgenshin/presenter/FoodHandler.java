package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.adapter.food.FoodAdapter;
import com.kanthin.handbookgenshin.model.index.IndexFood;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class FoodHandler {
    private final Context context;
    private final Interface anInterface;

    public FoodHandler(Context context, Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void viewFood(ArrayList<IndexFood> arrayFood, FoodAdapter adapterFood, TextView countFood){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("index").child("foods").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    IndexFood indexFood = dataSnapshot.getValue(IndexFood.class);
                    if (indexFood != null){
                        arrayFood.add(indexFood);
                    }
                }
                countFood.setText(String.valueOf(arrayFood.size()));
                adapterFood.notifyDataSetChanged();
                adapterFood.setShowShimmer(false);
                anInterface.success("");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
