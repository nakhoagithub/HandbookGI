package com.kanthin.handbookgenshin.view;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.weapon.CostsAscendWeaponAdapter;
import com.kanthin.handbookgenshin.adapter.weapon.IndexWeaponAdapter;
import com.kanthin.handbookgenshin.model.ItemCostsAscend;
import com.kanthin.handbookgenshin.model.weapon.StatsWeapon;
import com.kanthin.handbookgenshin.model.weapon.Weapon;
import com.kanthin.handbookgenshin.presenter.InfoWeaponHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.InfoWeaponInterface;

import java.util.ArrayList;

public class InfoWeaponActivity extends AppCompatActivity implements InfoWeaponInterface {

    AdView adView;

    ImageButton btBack, btShowInfo;
    CardView cardInfo;
    LinearLayout layoutInfoWeapon, layoutMoreInfo;
    ImageView imageItemWeapon, imageRarity;
    RelativeLayout layoutBackgroundItem;
    TextView nameItem, nameWeapon, attack, subStatWeapon, typeWeapon, effectNameWeapon, descriptionWeapon,
            effectR1, effectR2, effectR3, effectR4, effectR5;
    RecyclerView recyclerIndex, recyclerCostsAscend;
    ArrayList<ItemCostsAscend> arrayCostsAscend;
    CostsAscendWeaponAdapter adapterCostsAscend;

    //index
    ArrayList<StatsWeapon> arrayIndexWeapon;
    IndexWeaponAdapter adapterIndexWeapon;

    InfoWeaponHandler handler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_weapon);
        Init();
        Intent i = getIntent();
        Weapon weapon = (Weapon) i.getSerializableExtra("weapon");
        view(weapon);
        btBack.setOnClickListener(v -> onBackPressed());
        btShowInfo.setOnClickListener(v -> showInfo());
    }

    private void view(Weapon weapon) {
        handler.loadImage(weapon, imageItemWeapon);
        handler.loadInfo(weapon, nameItem, nameWeapon, attack, imageRarity, typeWeapon, subStatWeapon, layoutBackgroundItem);
        handler.loadInfoMore(weapon, effectNameWeapon, descriptionWeapon);
        handler.loadEffectWeapon(weapon, effectR1, effectR2, effectR3, effectR4, effectR5);

        //costs ascend weapon
        handler.loadCostAscendWeapon(weapon, arrayCostsAscend, adapterCostsAscend);

        //index
        handler.loadIndexWeapon(weapon, arrayIndexWeapon, adapterIndexWeapon);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String error) {

    }

    private void Init() {
        adView = findViewById(R.id.ads_weapon);

        btBack = findViewById(R.id.bt_back_info_weapon);
        btShowInfo = findViewById(R.id.bt_show_more_info_weapon);
        cardInfo = findViewById(R.id.card_info_weapon);
        layoutInfoWeapon= findViewById(R.id.layout_info_weapon);
        layoutMoreInfo = findViewById(R.id.layout_more_info_weapon);

        ShimmerFrameLayout shimmer = findViewById(R.id.shimmer_item_weapon);
        shimmer.setShimmer(null);
        shimmer.stopShimmer();
        imageItemWeapon = findViewById(R.id.image_item_weapon);
        imageRarity = findViewById(R.id.image_rarity_info_weapon);
        layoutBackgroundItem = findViewById(R.id.relative_item_weapon);
        nameItem = findViewById(R.id.name_item_weapon);
        nameWeapon = findViewById(R.id.name_info_weapon);
        attack = findViewById(R.id.attack_info_weapon);
        typeWeapon = findViewById(R.id.type_info_weapon);
        subStatWeapon = findViewById(R.id.sub_stats_info_weapon);
        effectNameWeapon = findViewById(R.id.effect_name_info_weapon);
        descriptionWeapon = findViewById(R.id.description_info_weapon);

        //effect weapon
        effectR1 = findViewById(R.id.info_effect_r1_weapon);
        effectR2 = findViewById(R.id.info_effect_r2_weapon);
        effectR3 = findViewById(R.id.info_effect_r3_weapon);
        effectR4 = findViewById(R.id.info_effect_r4_weapon);
        effectR5 = findViewById(R.id.info_effect_r5_weapon);

        //costs ascend
        recyclerCostsAscend = findViewById(R.id.recycler_costs_ascend_weapon);
        recyclerCostsAscend.setNestedScrollingEnabled(false);
        arrayCostsAscend = new ArrayList<>();
        adapterCostsAscend = new CostsAscendWeaponAdapter(this, arrayCostsAscend);
        LinearLayoutManager managerCostsAscend = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerCostsAscend.setLayoutManager(managerCostsAscend);
        recyclerCostsAscend.setAdapter(adapterCostsAscend);

        //index weapon
        recyclerIndex = findViewById(R.id.recycler_index_weapon);
        recyclerIndex.setNestedScrollingEnabled(false);
        arrayIndexWeapon = new ArrayList<>();
        adapterIndexWeapon = new IndexWeaponAdapter(this, arrayIndexWeapon);
        LinearLayoutManager managerIndexWeapon = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerIndex.setLayoutManager(managerIndexWeapon);
        recyclerIndex.setAdapter(adapterIndexWeapon);

        handler = new InfoWeaponHandler(this, this);
    }

    private void showInfo() {
        if (layoutMoreInfo.getVisibility() == View.GONE) {
            btShowInfo.setImageResource(R.drawable.ic_up);
            TransitionManager.beginDelayedTransition(cardInfo, new AutoTransition());
            layoutInfoWeapon.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            layoutMoreInfo.setVisibility(View.VISIBLE);
        } else {
            btShowInfo.setImageResource(R.drawable.ic_down);
            TransitionManager.beginDelayedTransition(cardInfo, new AutoTransition());
            layoutInfoWeapon.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            layoutMoreInfo.setVisibility(View.GONE);
        }
    }

}