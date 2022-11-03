package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.adapter.character.BuildCharacterAdapter;
import com.kanthin.handbookgenshin.model.character.BuildCharacter;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class BuildCharacterHandler {
    private final Context context;
    private final Interface anInterface;

    public BuildCharacterHandler(Context context, Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void viewBuildCharacter(String name, ArrayList<BuildCharacter> array, BuildCharacterAdapter adapterBuildCharacter, LinearProgressIndicator progress){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("buildCharacter").child(name).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                array.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    BuildCharacter buildCharacter = dataSnapshot.getValue(BuildCharacter.class);
                    if (buildCharacter != null){
                        array.add(buildCharacter);
                        Log.d("zxc", buildCharacter.getName() + " name");
                    }
                }
                adapterBuildCharacter.notifyDataSetChanged();
                if (array.size() == 0){
                    anInterface.fail("NOT_DATA");
                }
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

