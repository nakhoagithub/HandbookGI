package com.kanthin.handbookgenshin.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.character.BuildCharacterAdapter;
import com.kanthin.handbookgenshin.model.character.BuildCharacter;
import com.kanthin.handbookgenshin.presenter.BuildCharacterHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class BuildCharacterActivity extends AppCompatActivity implements Interface {

    ImageButton btBack;
    LinearProgressIndicator progressIndicator;
    RecyclerView recyclerBuild;
    ArrayList<BuildCharacter> array;
    BuildCharacterAdapter adapterBuildCharacter;

    BuildCharacterHandler handler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_character);
        Init();
        Intent i = getIntent();
        String name = i.getStringExtra("name");

        btBack.setOnClickListener(v -> onBackPressed());

        handler.viewBuildCharacter(name, array, adapterBuildCharacter, progressIndicator);
    }

    private void Init() {
        btBack = findViewById(R.id.bt_back_build_character);
        progressIndicator = findViewById(R.id.progress_build_character);
        recyclerBuild = findViewById(R.id.recycler_build_character);

        array = new ArrayList<>();
        adapterBuildCharacter = new BuildCharacterAdapter(this, array);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerBuild.setLayoutManager(layoutManager);
        recyclerBuild.setAdapter(adapterBuildCharacter);

        handler = new BuildCharacterHandler(this, this);
    }

    @Override
    public void success(String key) {

    }

    @Override
    public void fail(String error) {
        if (error.equals("NOT_DATA"))
        Toast.makeText(this, getResources().getString(R.string.not_data), Toast.LENGTH_SHORT).show();
    }
}