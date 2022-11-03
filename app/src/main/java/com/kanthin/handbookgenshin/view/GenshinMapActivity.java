package com.kanthin.handbookgenshin.view;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.presenter.GenshinMapHandler;

public class GenshinMapActivity extends AppCompatActivity {

    ImageButton btBack;
    WebView genshinMap;
    LinearProgressIndicator progress;

    GenshinMapHandler handler;

    @Override
    public void onBackPressed() {

        if (genshinMap.canGoBack()) {
            genshinMap.goBack();
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genshin_map);
        Init();
        btBack.setOnClickListener(v -> onBackPressed());

        handler.handlerGenshinMap(genshinMap, progress);
    }

    private void Init() {
        btBack = findViewById(R.id.bt_back_genshin_map);
        genshinMap = findViewById(R.id.web_genshin_map);
        progress = findViewById(R.id.progress_genshin_map);
        handler = new GenshinMapHandler(this);
    }
}