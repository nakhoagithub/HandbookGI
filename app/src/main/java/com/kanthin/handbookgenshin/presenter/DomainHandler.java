package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.adapter.domain.DomainAdapter;
import com.kanthin.handbookgenshin.model.domain.Domain;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class DomainHandler {
    private final Context context;
    private final Interface anInterface;
    private final String language;

    public DomainHandler(Context context, Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        language = sharedPreferences.getString("language", "en");
    }

    public void viewDomain(ArrayList<Domain> arrayDomain, DomainAdapter adapterDomain) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child(language).child("domains").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Domain domain = dataSnapshot.getValue(Domain.class);
                    if (domain != null){
                        arrayDomain.add(domain);
                    }
                }
                adapterDomain.notifyDataSetChanged();
                adapterDomain.setShowShimmer(false);
                anInterface.success("");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
