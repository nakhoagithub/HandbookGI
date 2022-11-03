package com.kanthin.handbookgenshin.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.domain.DomainLevelAdapter;
import com.kanthin.handbookgenshin.model.domain.LevelDomain;
import com.kanthin.handbookgenshin.presenter.InfoDomainHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class InfoDomainActivity extends AppCompatActivity implements Interface {

    AdView adView;

    ImageButton btBack;
    LinearProgressIndicator progress;
    TextView nameDomain, entrance, region, dayOfWeek, description;
    RecyclerView recyclerLv;
    ArrayList<LevelDomain> arrayLevel;
    DomainLevelAdapter adapterDomainLevel;

    InfoDomainHandler handler;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_domain);
        Init();
        Intent i = getIntent();
        String name = i.getStringExtra("name");

        btBack.setOnClickListener(v -> onBackPressed());

        handler.viewDomain(name, progress, nameDomain, entrance, region, dayOfWeek, description, arrayLevel, adapterDomainLevel);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void Init() {
        adView = findViewById(R.id.ads_domain);

        btBack = findViewById(R.id.bt_back_info_domain);
        progress = findViewById(R.id.progress_domain);
        nameDomain = findViewById(R.id.name_domain);
        entrance = findViewById(R.id.entrance_domain);
        region = findViewById(R.id.region_domain);
        dayOfWeek = findViewById(R.id.day_of_week_domain);
        description = findViewById(R.id.description_domain);

        recyclerLv = findViewById(R.id.recycler_domain_level);
        arrayLevel = new ArrayList<>();
        adapterDomainLevel = new DomainLevelAdapter(this, arrayLevel);
        LinearLayoutManager layoutLevelDomain = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerLv.setLayoutManager(layoutLevelDomain);
        recyclerLv.setAdapter(adapterDomainLevel);

        handler = new InfoDomainHandler(this, this);
    }

    @Override
    public void success(String key) {

    }

    @Override
    public void fail(String error) {

    }
}