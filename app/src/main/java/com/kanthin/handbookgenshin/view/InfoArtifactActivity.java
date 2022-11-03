package com.kanthin.handbookgenshin.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.artifact.Artifact;
import com.kanthin.handbookgenshin.presenter.InfoArtifactHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.InfoArtifactInterface;

public class InfoArtifactActivity extends AppCompatActivity implements InfoArtifactInterface {

    AdView adView;

    ImageButton btBack;
    TextView nameArtifact, attribute1, attribute2, rarity,
            name1, name2, name3, name4, name5,
            relic1, relic2, relic3, relic4, relic5,
            description1, description2, description3, description4, description5;
    ImageView image1, image2, image3, image4, image5;
    CardView card1, card2, card3, card4, card5;


    InfoArtifactHandler handler;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_artifact);
        Init();
        Intent i = getIntent();
        Artifact artifact;
        String name = i.getStringExtra("name");

        if (name != null){
            loadArtifact(name);
        } else {
            artifact = (Artifact) i.getSerializableExtra("artifact");
            view(artifact);
        }

        btBack.setOnClickListener(v -> onBackPressed());
    }

    private void loadArtifact(String name){
        SharedPreferences sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language)
                .child("artifacts").child(name);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Artifact artifact = snapshot.getValue(Artifact.class);
                view(artifact);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void view(Artifact artifact) {
        handler.loadInfo(artifact, nameArtifact, attribute1, attribute2);
        handler.loadRarity(artifact, rarity);
        handler.loadInfoSet(artifact, name1, name2, name3, name4, name5, image1, image2, image3, image4, image5,
        relic1, relic2, relic3, relic4, relic5, description1, description2, description3, description4, description5,
                card1, card2, card3, card4, card5);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void Init() {
        adView = findViewById(R.id.ads_artifact);

        btBack = findViewById(R.id.bt_back_info_artifact);
        nameArtifact = findViewById(R.id.name_info_artifact);
        attribute1 = findViewById(R.id.attribute1_info_artifact);
        attribute2 = findViewById(R.id.attribute2_info_artifact);

        card1 = findViewById(R.id.card1_artifact);
        image1 = findViewById(R.id.image1_info_artifact);
        name1 = findViewById(R.id.name1_info_artifact);
        relic1 = findViewById(R.id.relic_type1_artifact);
        description1 = findViewById(R.id.description1_artifact);

        card2 = findViewById(R.id.card2_artifact);
        image2 = findViewById(R.id.image2_info_artifact);
        name2 = findViewById(R.id.name2_info_artifact);
        relic2 = findViewById(R.id.relic_type2_artifact);
        description2 = findViewById(R.id.description2_artifact);

        card3 = findViewById(R.id.card3_artifact);
        image3 = findViewById(R.id.image3_info_artifact);
        name3 = findViewById(R.id.name3_info_artifact);
        relic3 = findViewById(R.id.relic_type3_artifact);
        description3 = findViewById(R.id.description3_artifact);

        card4 = findViewById(R.id.card4_artifact);
        image4 = findViewById(R.id.image4_info_artifact);
        name4 = findViewById(R.id.name4_info_artifact);
        relic4 = findViewById(R.id.relic_type4_artifact);
        description4 = findViewById(R.id.description4_artifact);

        card5 = findViewById(R.id.card5_artifact);
        image5 = findViewById(R.id.image5_info_artifact);
        name5 = findViewById(R.id.name5_info_artifact);
        relic5 = findViewById(R.id.relic_type5_artifact);
        description5 = findViewById(R.id.description5_artifact);

        rarity = findViewById(R.id.rarity_info_artifact);

        handler = new InfoArtifactHandler(this, this);
    }
}