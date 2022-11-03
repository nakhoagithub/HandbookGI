package com.kanthin.handbookgenshin.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.MainAdapter;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FirebaseRemoteConfig remoteConfig;

    MainAdapter adapter;
    ViewPager2 viewPagerMain;
    BottomNavigationView navBottom;

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            Toast.makeText(this, getResources().getString(R.string.double_click_out), Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(() -> exit = false, 2500);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        View();
        if (getIntent().getBooleanExtra("EXIT", false)){
            finish();
        }

        remoteVersion();
    }

    private void remoteVersion() {
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        remoteConfig.setConfigSettingsAsync(configSettings);
        remoteConfig.fetchAndActivate().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                final String version = remoteConfig.getString("version");
                final String versionCode = remoteConfig.getString("versionCode");
                if (Integer.parseInt(version) > returnVersion()){
                    dialogUpdate(versionCode);
                }
            }
        });
    }

    private void dialogUpdate(String versionCode) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update_app);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(false);

            TextView textVersion = dialog.findViewById(R.id.version_update_app);
            textVersion.setText(getResources().getString(R.string.new_version, versionCode));

            TextView yes = dialog.findViewById(R.id.bt_yes_dialog);
            TextView no = dialog.findViewById(R.id.bt_no_dialog);

            yes.setOnClickListener(v1 -> {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.kanthin.handbookgenshin")));
                dialog.dismiss();
            });

            no.setOnClickListener(v1 -> dialog.dismiss());
        }
        dialog.show();
    }

    private void Init() {
        remoteConfig = FirebaseRemoteConfig.getInstance();

        adapter = new MainAdapter(this);
        viewPagerMain = findViewById(R.id.view_pager_main);
        viewPagerMain.setAdapter(adapter);
        viewPagerMain.setOffscreenPageLimit(1);
        navBottom = findViewById(R.id.nav_bottom_main);
    }

    @SuppressLint({"NonConstantResourceId", "ClickableViewAccessibility"})
    private void View() {
        navBottom.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    viewPagerMain.setCurrentItem(0, true);
                    break;
                case R.id.nav_character:
                    viewPagerMain.setCurrentItem(1, true);
                    break;
                case R.id.nav_weapon:
                    viewPagerMain.setCurrentItem(2, true);
                    break;
                case R.id.nav_artifact:
                    viewPagerMain.setCurrentItem(3, true);
                    break;
                case R.id.nav_material:
                    viewPagerMain.setCurrentItem(4, true);
                    break;
            }
            return true;
        });
        viewPagerMain.setUserInputEnabled(false);

        viewPagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        navBottom.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case 1:
                        navBottom.getMenu().findItem(R.id.nav_character).setChecked(true);
                        break;
                    case 2:
                        navBottom.getMenu().findItem(R.id.nav_weapon).setChecked(true);
                        break;
                    case 3:
                        navBottom.getMenu().findItem(R.id.nav_artifact).setChecked(true);
                        break;
                    case 4:
                        navBottom.getMenu().findItem(R.id.nav_material).setChecked(true);
                        break;
                }
            }
        });
    }

    private int returnVersion(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(packageInfo).versionCode;
    }
}