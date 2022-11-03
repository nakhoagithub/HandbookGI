package com.kanthin.handbookgenshin.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.presenter.MenuHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.MenuInterface;

public class MenuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, MenuInterface {

    ImageButton btBack, btChangeLang, btJoinDiscord, btSendMail, btJoinZalo;
    SwitchMaterial switchNightMode;
    TextView info;
    MenuHandler handler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /*
    @Override
    protected void attachBaseContext(Context newBase) {

     //Get configuration and resources before onCreate method
//        Resources resources = newBase.getResources();
//        Configuration configuration = new Configuration(resources.getConfiguration());
//        configuration.uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED;
//        Context context = newBase.createConfigurationContext(configuration);
//
//        // Set locale with configuration saved
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//        String language = sharedPreferences.getString("language", "vi");
//        Locale locale = new Locale(language);
//        Locale.setDefault(locale);
//        configuration.setLocale(locale);
//        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        super.attachBaseContext(newBase);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Init();

        handler.viewMenu(info, switchNightMode);

        btJoinDiscord.setOnClickListener(v -> handler.joinDiscord());
        btJoinZalo.setOnClickListener(v -> handler.joinZalo());
        btSendMail.setOnClickListener(v -> handler.sendMail());
        btBack.setOnClickListener(v -> onBackPressed());
        btChangeLang.setOnClickListener(v -> handler.dialogChangeLang());

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                handler.dialogChangeNightMode(b, switchNightMode, this);
            }
        };
        switchNightMode.setOnCheckedChangeListener(listener);

    }

    private void Init() {
        btBack = findViewById(R.id.bt_back_menu);
        btChangeLang = findViewById(R.id.bt_change_language_menu);
        btJoinDiscord = findViewById(R.id.bt_join_discord);
        btJoinZalo = findViewById(R.id.bt_join_zalo);
        btSendMail = findViewById(R.id.bt_send_mail);
        switchNightMode = findViewById(R.id.switch_night_mode);
        info = findViewById(R.id.info_menu);

        handler = new MenuHandler(this, this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void success(String key) {
    }

    @Override
    public void fail(String error) {

    }
}