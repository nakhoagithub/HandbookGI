package com.kanthin.handbookgenshin.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.Html;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.presenter.anInterface.MenuInterface;
import com.kanthin.handbookgenshin.view.MainActivity;

public class MenuHandler {
    private final Context context;
    private final MenuInterface anInterface;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final UIApp uiApp;

    public MenuHandler(Context context, MenuInterface anInterface) {
        this.context = context;
        this.anInterface = anInterface;
        sharedPreferences = context.getSharedPreferences("setting", 0);
        editor = sharedPreferences.edit();
        uiApp = new UIApp(context);
    }

    public void viewMenu(TextView info, SwitchMaterial switchNightMode) {
        String s = context.getString(R.string.info_app);
        s = s.replace("\n", "<br>");
        s = s.replace("{b}", "<b>").replace("{/b}", "</b>");
        info.setText(Html.fromHtml(s, Html.FROM_HTML_MODE_LEGACY));

        switchNightMode.setChecked(sharedPreferences.getBoolean("nightMode", false));
    }

    public void dialogChangeLang() {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_app_language);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(false);

            RadioButton vi = dialog.findViewById(R.id.lang_vi);
            vi.setChecked(sharedPreferences.getString("language", "en").equals("vi"));
            RadioButton en = dialog.findViewById(R.id.lang_en);
            en.setChecked(sharedPreferences.getString("language", "en").equals("en"));

            TextView yes = dialog.findViewById(R.id.bt_yes_dialog);
            TextView no = dialog.findViewById(R.id.bt_no_dialog);

            yes.setOnClickListener(v1 -> {
                handlerChangeAppLang(vi, en);
                dialog.dismiss();
            });

            no.setOnClickListener(v1 -> dialog.dismiss());
        }
        dialog.show();
    }

    private void handlerChangeAppLang(RadioButton vi, RadioButton en) {
        if (vi.isChecked() && !sharedPreferences.getString("language", "").equals("vi")) {
            sharedPreferences.edit().putString("language", "vi").apply();
            uiApp.updateResource("vi");
            exit();
        }

        if (en.isChecked() && !sharedPreferences.getString("language", "").equals("en")) {
            sharedPreferences.edit().putString("language", "en").apply();
            uiApp.updateResource("en");
            exit();
        }
    }

    public void dialogChangeNightMode(boolean b, SwitchMaterial switchNightMode, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_night_mode);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(false);

            TextView yes = dialog.findViewById(R.id.bt_yes_dialog);
            TextView no = dialog.findViewById(R.id.bt_no_dialog);

            yes.setOnClickListener(v1 -> {
                editor.putBoolean("nightMode", b).apply();
                exit();
                if (b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            });

            no.setOnClickListener(v1 -> {
                switchNightMode.setOnCheckedChangeListener(null);
                switchNightMode.setChecked(!switchNightMode.isChecked());
                switchNightMode.setOnCheckedChangeListener(onCheckedChangeListener);
                dialog.dismiss();
            });
        }
        dialog.show();
    }

    private void exit() {
        Intent intent = new Intent(context, MainActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        intent.putExtra("EXIT", true);
        context.startActivity(intent);
    }

    public void joinDiscord() {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.gg/DeEej53z6j")));
    }

    public void sendMail() {
        Intent intent = new Intent (Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"kanthindev@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Ứng dụng Handbook GI");
        intent.setPackage("com.google.android.gm");
        context.startActivity(intent);
    }

    public void joinZalo() {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://zalo.me/g/yvkxjg234")));
    }
}
