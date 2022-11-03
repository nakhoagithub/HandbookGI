package com.kanthin.handbookgenshin;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.firebase.database.FirebaseDatabase;
import com.kanthin.handbookgenshin.presenter.UIApp;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        UIApp uiApp = new UIApp(this);
        SharedPreferences setting = getSharedPreferences("setting", 0);
        String lang = setting.getString("language", uiApp.getLangLocal());
        uiApp.updateResource(lang);
        uiApp.updateUIMode();
    }
}

