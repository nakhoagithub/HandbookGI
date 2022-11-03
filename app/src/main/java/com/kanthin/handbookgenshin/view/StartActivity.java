package com.kanthin.handbookgenshin.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.presenter.UIApp;

import java.util.Objects;

public class StartActivity extends AppCompatActivity {

    TextView welcome;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        UIApp uiApp = new UIApp(this);
        //uiApp.update();
        SharedPreferences setting = getSharedPreferences("setting", 0);
        String lang = setting.getString("language", uiApp.getLangLocal());
        uiApp.updateResource(lang);
        uiApp.updateUIMode();

        TextView welcome = findViewById(R.id.welcome);
        welcome.setText(getResources().getString(R.string.welcome));

        TextView ver = findViewById(R.id.version_start);
        ver.setText(getResources().getString(R.string.text_version, returnVersionName()));

        MobileAds.initialize(this, initializationStatus -> {
        });

        new Handler().postDelayed(()->{
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
            finish();
        }, 2500);
    }

    private String returnVersionName(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(packageInfo).versionName;
    }
}