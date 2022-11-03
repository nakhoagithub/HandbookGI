package com.kanthin.handbookgenshin.view;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.domain.DomainAdapter;
import com.kanthin.handbookgenshin.model.domain.Domain;
import com.kanthin.handbookgenshin.presenter.DomainHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class DomainActivity extends AppCompatActivity implements Interface {

    ImageButton btBack;
    RecyclerView recyclerDomain;
    ArrayList<Domain> arrayDomain;
    DomainAdapter adapterDomain;

    DomainHandler handler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain);
        Init();

        btBack.setOnClickListener(v -> onBackPressed());

        handler.viewDomain(arrayDomain, adapterDomain);
    }

    private void Init() {
        btBack = findViewById(R.id.bt_back_domain);

        recyclerDomain = findViewById(R.id.recycler_domain);
        arrayDomain = new ArrayList<>();
        adapterDomain = new DomainAdapter(this, arrayDomain, R.layout.item_domain_grid_view);
        adapterDomain.setItemShimmer(6);
        GridLayoutManager managerDomain = new GridLayoutManager(this, 2);
        recyclerDomain.setLayoutManager(managerDomain);
        recyclerDomain.setAdapter(adapterDomain);

        handler = new DomainHandler(this, this);
    }

    @Override
    public void success(String key) {

    }

    @Override
    public void fail(String error) {

    }
}