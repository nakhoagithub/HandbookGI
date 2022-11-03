package com.kanthin.handbookgenshin.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.MaterialUpAdapter;
import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;
import com.kanthin.handbookgenshin.presenter.InfoFoodHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class InfoFoodActivity extends AppCompatActivity implements Interface {

    AdView adView;

    RelativeLayout backgroundItem;
    ImageButton btBack;
    ImageView imageItem, imageRarity;
    TextView nameItem, nameInfo, type, description, effect;

    RecyclerView recyclerMaterial;
    ArrayList<ItemMaterial> arrayMaterial;
    MaterialUpAdapter adapterMaterial;

    CardView cardDelicious, cardNormal, cardSuspicious;
    TextView effectDelicious, effectNormal, effectSuspicious;
    TextView descriptionDelicious, descriptionNormal, descriptionSuspicious;

    InfoFoodHandler handler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_food);
        Init();
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        view(name);
        btBack.setOnClickListener(v -> onBackPressed());
    }

    private void view(String name) {
        nameItem.setText(name);
        nameInfo.setText(name);
        handler.viewFood(name, imageItem, type, effect, description, backgroundItem, imageRarity,
                arrayMaterial, adapterMaterial, cardDelicious, cardNormal, cardSuspicious, effectDelicious, effectNormal,
                effectSuspicious, descriptionDelicious, descriptionNormal, descriptionSuspicious);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void Init(){
        adView = findViewById(R.id.ads_food);

        btBack = findViewById(R.id.bt_back_info_food);

        ShimmerFrameLayout shimmer = findViewById(R.id.shimmer_item_food);
        shimmer.setShimmer(null);
        shimmer.stopShimmer();
        backgroundItem = findViewById(R.id.background_item_food);
        imageItem = findViewById(R.id.image_item_food);
        imageRarity = findViewById(R.id.image_rarity_info_food);
        nameItem = findViewById(R.id.name_item_food);
        nameInfo = findViewById(R.id.name_info_food);
        type = findViewById(R.id.type_info_food);
        effect = findViewById(R.id.effect_info_food);
        description = findViewById(R.id.description_info_food);

        recyclerMaterial = findViewById(R.id.recycler_material_food);
        arrayMaterial = new ArrayList<>();
        adapterMaterial = new MaterialUpAdapter(this, arrayMaterial);
        LinearLayoutManager layoutManagerMaterial = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerMaterial.setLayoutManager(layoutManagerMaterial);
        recyclerMaterial.setAdapter(adapterMaterial);

        cardDelicious = findViewById(R.id.card_delicious_food);
        cardNormal = findViewById(R.id.card_normal_food);
        cardSuspicious = findViewById(R.id.card_suspicious_food);

        effectDelicious = findViewById(R.id.delicious_info_food);
        effectNormal = findViewById(R.id.normal_info_food);
        effectSuspicious = findViewById(R.id.suspicious_info_food);

        descriptionDelicious = findViewById(R.id.description_delicious_info_food);
        descriptionNormal = findViewById(R.id.description_normal_info_food);
        descriptionSuspicious = findViewById(R.id.description_suspicious_info_food);

        handler = new InfoFoodHandler(this, this);
    }

    @Override
    public void success(String key) {

    }

    @Override
    public void fail(String error) {

    }
}