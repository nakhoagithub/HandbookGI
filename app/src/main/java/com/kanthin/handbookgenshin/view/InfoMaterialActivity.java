package com.kanthin.handbookgenshin.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.material.Material;
import com.kanthin.handbookgenshin.presenter.InfoMaterialHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.InfoMaterialInterface;

public class InfoMaterialActivity extends AppCompatActivity implements InfoMaterialInterface {

    AdView adView;

    RelativeLayout backgroundItem;
    ImageButton btBack;
    ImageView imageItem, imageRarity;
    TextView nameItem, nameInfo, type, description, dropdomain, dayOpenDungeno, source;
    InfoMaterialHandler handler;

    //LinearProgressIndicator progressIndicator;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_material);
        Init();
        Intent i = getIntent();
        Material material;

        String name = i.getStringExtra("name");
        if (name != null){
            loadMaterial(name);
        } else {
            material = (Material) i.getSerializableExtra("material");
            view(material);
        }

        btBack.setOnClickListener(v -> onBackPressed());
    }

    private void loadMaterial(String name){
        SharedPreferences sharedPreferences = getSharedPreferences("setting", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language)
                .child("materials").child(name);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Material material = snapshot.getValue(Material.class);
                view(material);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void view(Material material) {
        handler.loadMaterial(material, backgroundItem, nameItem, nameInfo, imageItem, imageRarity, type, description);
        handler.loadSource(material, dropdomain, dayOpenDungeno, source);

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
        adView = findViewById(R.id.ads_material);

        btBack = findViewById(R.id.bt_back_info_material);

        ShimmerFrameLayout shimmer = findViewById(R.id.shimmer_item_material);
        shimmer.setShimmer(null);
        shimmer.stopShimmer();
        backgroundItem = findViewById(R.id.background_item_material);
        imageItem = findViewById(R.id.image_item_material);
        imageRarity = findViewById(R.id.image_rarity_info_material);
        nameItem = findViewById(R.id.name_item_material);
        nameInfo = findViewById(R.id.name_info_material);
        type = findViewById(R.id.type_info_material);
        description = findViewById(R.id.description_info_material);
        dropdomain = findViewById(R.id.dropdomain_info_material);
        dayOpenDungeno = findViewById(R.id.day_open_dungeno_info_material);
        source = findViewById(R.id.source_info_material);

        handler = new InfoMaterialHandler(this, this);
    }


}