package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.adapter.enemy.EnemyAdapter;
import com.kanthin.handbookgenshin.model.index.IndexEnemy;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class EnemyHandler {
    private final Context context;
    private final Interface anInterface;

    public EnemyHandler(Context context, Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void viewEnemy(ArrayList<IndexEnemy> arrayEnemy, EnemyAdapter adapterEnemy){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("index").child("enemies").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    IndexEnemy indexEnemy = dataSnapshot.getValue(IndexEnemy.class);
                    if (indexEnemy != null){
                        arrayEnemy.add(indexEnemy);
                    }
                }
                adapterEnemy.notifyDataSetChanged();
                adapterEnemy.setShowShimmer(false);
                anInterface.success("");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
