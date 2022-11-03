package com.kanthin.handbookgenshin.presenter;

import android.annotation.SuppressLint;
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
import com.kanthin.handbookgenshin.adapter.domain.DomainAdapter;
import com.kanthin.handbookgenshin.model.domain.Domain;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeHandler {
    private final Context context;
    private final String language;
    private final Calendar calendar;

    public HomeHandler(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        language = sharedPreferences.getString("language", "en");

        calendar = Calendar.getInstance();


    }

    @SuppressLint("SetTextI18n")
    public void viewBackground(ImageView imageBackground, TextView dayInWeek) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("app").child("background").get().addOnSuccessListener(dataSnapshot -> {
            String link = dataSnapshot.getValue(String.class);
            Glide.with(context)
                    .load(link)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageBackground);
        });

        //day in week
        String day = getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
        dayInWeek.setText(day);
    }

    public void viewDomainToday(ArrayList<Domain> array, DomainAdapter adapter) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language);
        String day = getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
        database.child("domainsToday").child(day).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adapter.setItemShimmer(3);
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    String name = dataSnapshot1.getValue(String.class) + "";
                    getDomain(name, array, adapter);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String getDayOfWeek(int day) {
        switch (day) {
            case 1:
                return context.getString(R.string.day1);
            case 2:
                return context.getString(R.string.day2);
            case 3:
                return context.getString(R.string.day3);
            case 4:
                return context.getString(R.string.day4);
            case 5:
                return context.getString(R.string.day5);
            case 6:
                return context.getString(R.string.day6);
            case 7:
                return context.getString(R.string.day7);
        }
        return "";
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getDomain(String name, ArrayList<Domain> array, DomainAdapter adapter) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language);
        database.child("domains").child(name).get().addOnSuccessListener(dataSnapshot -> {
            Domain domain = dataSnapshot.getValue(Domain.class);
            if (domain != null) {
                array.add(domain);
            }
            adapter.setShowShimmer(false);
            adapter.notifyDataSetChanged();
        });
    }

    public void viewNotification(TextView notification) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("app");
        ref.child(language).child("notification").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s = snapshot.getValue(String.class) + "";
                notification.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
