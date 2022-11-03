package com.kanthin.handbookgenshin.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class UIApp {
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public UIApp(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("setting", 0);
        editor = sharedPreferences.edit();
    }

    public void update() {
        updateResource(sharedPreferences.getString("language", getLangLocal()));
        updateUIMode();
    }

    public String getLangLocal(){
        Locale locale = Locale.getDefault();
        if (locale.getLanguage().contains("vi") ||
        locale.getLanguage().contains("en")){
            return locale.getLanguage();
        } else {
            return "en";
        }
    }

    public void updateUIMode(){
        boolean nightMode = sharedPreferences.getBoolean("nightMode", false);
        if (nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void updateResource(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        setLang(lang);
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private void setLang(String lang){
        editor.putString("language", lang).apply();
    }
}
