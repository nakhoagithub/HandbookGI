package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.adapter.weapon.WeaponAdapter;
import com.kanthin.handbookgenshin.model.weapon.Weapon;
import com.kanthin.handbookgenshin.presenter.anInterface.WeaponInterface;

import java.util.ArrayList;

public class WeaponHandler {
    private final Context context;
    private final WeaponInterface anInterface;
    private final String language;
    private final TextView countItem;

    public WeaponHandler(Context context, WeaponInterface anInterface, TextView countItem) {
        this.context = context;
        this.anInterface = anInterface;
        this.countItem = countItem;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        language = sharedPreferences.getString("language", "vi");
    }

    public void loadWeapon(ArrayList<Weapon> arrayNotChange, ArrayList<Weapon> array, WeaponAdapter adapter) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language)
                .child("weapons");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Weapon weapon = dataSnapshot.getValue(Weapon.class);
                    if (weapon != null) {
                        array.add(weapon);
                        arrayNotChange.add(weapon);
                    }
                }
                countItem.setText(String.valueOf(array.size()));
                //sort array weapon
//                array.sort((weapon, t1) -> ComparisonChain.start()
//                        .compare(t1.getRarity(), weapon.getRarity(), Ordering.natural().nullsFirst())
//                        .compare(weapon.getName(), t1.getName(), Ordering.natural().nullsFirst())
//                        .result());

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
