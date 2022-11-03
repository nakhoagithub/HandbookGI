package com.kanthin.handbookgenshin.view;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.enemy.EnemyAdapter;
import com.kanthin.handbookgenshin.model.index.IndexEnemy;
import com.kanthin.handbookgenshin.presenter.EnemyHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class EnemyActivity extends AppCompatActivity implements Interface {

    ImageButton btBack;
    RecyclerView recyclerEnemy;
    ArrayList<IndexEnemy> arrayEnemy;
    EnemyAdapter adapterEnemy;
    EnemyHandler handler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ememy);
        Init();

        btBack.setOnClickListener(v -> onBackPressed());

        handler.viewEnemy(arrayEnemy, adapterEnemy);
    }

    private void Init() {
        btBack = findViewById(R.id.bt_back_enemy);

        recyclerEnemy = findViewById(R.id.recycler_enemy);

        arrayEnemy = new ArrayList<>();
        adapterEnemy = new EnemyAdapter(this, arrayEnemy);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerEnemy.setLayoutManager(layoutManager);
        recyclerEnemy.setAdapter(adapterEnemy);

        handler = new EnemyHandler(this, this);
    }

    @Override
    public void success(String key) {
    }

    @Override
    public void fail(String error) {

    }
}