package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.domain.DomainLevelAdapter;
import com.kanthin.handbookgenshin.model.domain.Domain;
import com.kanthin.handbookgenshin.model.domain.LevelDomain;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class InfoDomainHandler {
    private final Context context;
    private final Interface anInterface;
    private final String language;

    public InfoDomainHandler(Context context, Interface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        language = sharedPreferences.getString("language", "en");

    }

    public void viewDomain(String name, LinearProgressIndicator progress, TextView nameDomain, TextView entrance,
                           TextView region, TextView dayOfWeek, TextView description, ArrayList<LevelDomain> arrayLevel,
                           DomainLevelAdapter adapterDomainLevel) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child(language).child("domains").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Domain domain = snapshot.getValue(Domain.class);
                if (domain != null) {
                    progress.setVisibility(View.GONE);
                    nameDomain.setText(domain.getName());
                    entrance.setText(domain.getDomainentrance());
                    region.setText(domain.getRegion());
                    description.setText(domain.getDescription().replace("\\n", "\n"));
                    if (domain.getDaysofweek() != null){
                        setDayOfWeek(domain.getDaysofweek(), dayOfWeek);
                    } else dayOfWeek.setText(context.getString(R.string.not));
                    setLevelDomain(domain.getDomainLvs(), arrayLevel, adapterDomainLevel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setDayOfWeek(ArrayList<String> daysofweek, TextView dayOfWeek) {
        StringBuilder day = new StringBuilder();
        for (int i = 0; i < daysofweek.size(); i++) {
            if (i != daysofweek.size() - 1) {
                day.append(daysofweek.get(i)).append(", ");
            } else {
                day.append(daysofweek.get(i)).append(".");
            }
        }
        dayOfWeek.setText(day);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void setLevelDomain(ArrayList<LevelDomain> domainLvs, ArrayList<LevelDomain> arrayLevel, DomainLevelAdapter adapterDomainLevel) {
        arrayLevel.addAll(domainLvs);
        adapterDomainLevel.notifyDataSetChanged();
    }
}
