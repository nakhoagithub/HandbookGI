package com.kanthin.handbookgenshin.view;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.kanthin.handbookgenshin.R;

public class ElementActivity extends AppCompatActivity {

    ImageButton btBack;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element);
        Init();

        btBack.setOnClickListener(v -> onBackPressed());
    }

    private void Init() {
        btBack = findViewById(R.id.bt_back_element);
    }
}