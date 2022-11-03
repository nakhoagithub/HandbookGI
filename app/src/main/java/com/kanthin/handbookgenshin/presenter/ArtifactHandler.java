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
import com.kanthin.handbookgenshin.adapter.artifact.ArtifactAdapter;
import com.kanthin.handbookgenshin.model.artifact.Artifact;
import com.kanthin.handbookgenshin.presenter.anInterface.ArtifactInterface;

import java.util.ArrayList;

public class ArtifactHandler {

    private final Context context;
    private final ArtifactInterface anInterface;
    private final String language;
    private final TextView countItem;

    public ArtifactHandler(Context context, ArtifactInterface anInterface, TextView countItem) {
        this.context = context;
        this.anInterface = anInterface;
        this.countItem = countItem;
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", 0);
        language = sharedPreferences.getString("language", "en");
    }

    public void loadArtifact(ArrayList<Artifact> arrayNotChange, ArrayList<Artifact> array, ArtifactAdapter adapter) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language)
                .child("artifacts");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Artifact artifact = dataSnapshot.getValue(Artifact.class);
                    if (artifact != null) {
                        array.add(artifact);
                        arrayNotChange.add(artifact);
                    }
                    adapter.notifyDataSetChanged();
                }
                countItem.setText(String.valueOf(array.size()));
//                array.sort((artifact, t1) -> ComparisonChain.start()
//                        .compare(t1.getRarity().get(t1.getRarity().size()-1), artifact.getRarity().get(artifact.getRarity().size()-1), Ordering.natural().nullsFirst())
//                        .compare(t1.getName(), artifact.getName(), Ordering.natural().nullsFirst())
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
