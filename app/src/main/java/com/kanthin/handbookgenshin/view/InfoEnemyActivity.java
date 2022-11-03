package com.kanthin.handbookgenshin.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.presenter.InfoEnemyHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

public class InfoEnemyActivity extends AppCompatActivity implements Interface {
    AdView adView;

    RelativeLayout backgroundItem;
    ImageButton btBack;
    ImageView imageItem;
    TextView nameItem, nameInfo, type, description, specialName, category;
    InfoEnemyHandler handler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_enemy);
        Init();
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        view(name);

        btBack.setOnClickListener(v -> onBackPressed());

    }

    private void view(String name) {
        nameItem.setText(name);
        nameInfo.setText(name);
        handler.viewEnemy(name, imageItem, category, type, description, specialName);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void Init() {
        adView = findViewById(R.id.ads_enemy);

        btBack = findViewById(R.id.bt_back_info_enemy);

        ShimmerFrameLayout shimmer = findViewById(R.id.shimmer_item_enemy);
        shimmer.setShimmer(null);
        shimmer.stopShimmer();
        backgroundItem = findViewById(R.id.background_item_enemy);
        backgroundItem.setBackgroundResource(R.drawable.rarity_1_background);

        
        imageItem = findViewById(R.id.image_item_enemy);
        nameItem = findViewById(R.id.name_item_enemy);
        nameInfo = findViewById(R.id.name_info_enemy);
        category = findViewById(R.id.category_info_enemy);
        type = findViewById(R.id.type_info_enemy);
        description = findViewById(R.id.description_info_enemy);
        specialName = findViewById(R.id.special_name_info_enemy);

        handler = new InfoEnemyHandler(this, this);
    }

    @Override
    public void success(String key) {

    }

    @Override
    public void fail(String error) {

    }
}