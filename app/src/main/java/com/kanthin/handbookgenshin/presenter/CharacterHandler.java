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
import com.kanthin.handbookgenshin.adapter.character.CharacterAdapter;
import com.kanthin.handbookgenshin.model.character.Character;
import com.kanthin.handbookgenshin.presenter.anInterface.CharacterInterface;

import java.util.ArrayList;

public class CharacterHandler {
    private final Context context;
    private final CharacterInterface anInterface;
    private final String language;
    private final TextView countItem;

    public CharacterHandler(Context context, CharacterInterface anInterface, TextView countItem) {
        this.context = context;
        this.anInterface = anInterface;
        this.countItem = countItem;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        language = sharedPreferences.getString("language", "en");
    }

    public void loadCharacter(ArrayList<Character> arrayAll, ArrayList<Character> array, CharacterAdapter adapter) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language)
                .child("characters");
        database.keepSynced(true);
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Character character = dataSnapshot.getValue(Character.class);
                    if (character != null) {
                        array.add(character);
                        arrayAll.add(character);
                    }
                    adapter.notifyDataSetChanged();
                }
                countItem.setText(String.valueOf(array.size()));
                array.sort((character, t1) -> ComparisonChain.start()
                        .compare(character.getName(), t1.getName(), Ordering.natural().nullsFirst())
                        .result());
                adapter.notifyDataSetChanged();
                adapter.setShowShimmer(false);
                anInterface.success();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
