package com.kanthin.handbookgenshin.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.spinner.SpinnerArtifactAdapter;
import com.kanthin.handbookgenshin.adapter.spinner.SpinnerCharacterAdapter;
import com.kanthin.handbookgenshin.adapter.spinner.SpinnerStringAdapter;
import com.kanthin.handbookgenshin.adapter.spinner.SpinnerWeaponAdapter;
import com.kanthin.handbookgenshin.model.index.IndexArtifact;
import com.kanthin.handbookgenshin.model.index.IndexCharacter;
import com.kanthin.handbookgenshin.model.index.IndexWeapon;
import com.kanthin.handbookgenshin.presenter.ContributeHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.InterfaceNotification;

import java.util.ArrayList;

public class ContributeActivity extends AppCompatActivity implements InterfaceNotification {

    ImageButton btBack;
    Spinner spinnerCharacter, spinnerWeapon, spinnerSetArtifact1, spinnerSetArtifact2, spinnerSand, spinnerGoblet, spinnerCirclet;
    ProgressBar progressCharacter, progressWeapon;
    TextView textSet1, textSet2;
    RadioButton radioSet2;

    ArrayList<IndexCharacter> arrayCharacter;
    SpinnerCharacterAdapter adapterCharacter;

    ArrayList<IndexArtifact> arrayArtifact;
    SpinnerArtifactAdapter adapterArtifact;

    ArrayList<IndexWeapon> arrayWeapon;
    ArrayList<IndexWeapon> arrayNotChangeWeapon;
    SpinnerWeaponAdapter adapterWeapon;

    ArrayList<String> arraySpinnerSand, arraySpinnerGoblet, arraySpinnerCirclet;
    SpinnerStringAdapter adapterSand, adapterGoblet, adapterCirclet;

    TextInputEditText editDescription;
    Button btContribute;

    ContributeHandler handler;
    ProgressDialog progressDialog;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);
        Init();
        btBack.setOnClickListener(v -> onBackPressed());
        radioSet2.setOnCheckedChangeListener((compoundButton, b) -> handler.viewLayoutSetArtifact(b));
        btContribute.setOnClickListener(v ->
                handler.contributeArtifactCharacter(spinnerCharacter, spinnerWeapon, radioSet2, spinnerSetArtifact1,
                        spinnerSetArtifact2, spinnerSand, spinnerGoblet, spinnerCirclet,
                        editDescription));

        handler.loadSpinnerCharacter(arrayCharacter, adapterCharacter);
        handler.loadSpinnerWeapon(arrayWeapon, arrayNotChangeWeapon, adapterWeapon);
        handler.handlerSpinnerCharacterAndWeapon(spinnerCharacter, spinnerWeapon, arrayWeapon, arrayNotChangeWeapon, adapterWeapon);

        handler.loadSpinnerArtifact(arrayArtifact, adapterArtifact);
        handler.loadSpinnerMainStatSand(arraySpinnerSand, adapterSand);
        handler.loadSpinnerMainStatGoblet(arraySpinnerGoblet, adapterGoblet);
        handler.loadSpinnerMainStatCirclet(arraySpinnerCirclet, adapterCirclet);
    }

    private void Init() {
        btBack = findViewById(R.id.bt_back_contribute);

        progressCharacter = findViewById(R.id.progress_character_contribute);
        progressWeapon = findViewById(R.id.progress_weapon_contribute);
        radioSet2 = findViewById(R.id.radio_set2);
        //radioSet4 = findViewById(R.id.radio_set4);
        textSet1 = findViewById(R.id.text_set_artifact1);
        spinnerSetArtifact1 = findViewById(R.id.spinner_set_artifact1);
        spinnerSetArtifact2 = findViewById(R.id.spinner_set_artifact2);
        textSet2 = findViewById(R.id.text_set_artifact2);

        arrayCharacter = new ArrayList<>();
        arrayWeapon = new ArrayList<>();
        arrayNotChangeWeapon = new ArrayList<>();
        arrayArtifact = new ArrayList<>();
        arraySpinnerSand = new ArrayList<>();
        arraySpinnerGoblet = new ArrayList<>();
        arraySpinnerCirclet = new ArrayList<>();

        //character
        adapterCharacter = new SpinnerCharacterAdapter(this, arrayCharacter);
        spinnerCharacter = findViewById(R.id.spinner_character_contribute);
        spinnerCharacter.setAdapter(adapterCharacter);
        //weapon
        adapterWeapon = new SpinnerWeaponAdapter(this, arrayWeapon);
        spinnerWeapon = findViewById(R.id.spinner_weapon_contribute);
        spinnerWeapon.setAdapter(adapterWeapon);

        //artifact
        adapterArtifact = new SpinnerArtifactAdapter(this, arrayArtifact);
        spinnerSetArtifact1.setAdapter(adapterArtifact);
        spinnerSetArtifact2.setAdapter(adapterArtifact);

        //main-stat
        adapterSand = new SpinnerStringAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                arraySpinnerSand);
        spinnerSand = findViewById(R.id.spinner_sand_contribute);
        spinnerSand.setAdapter(adapterSand);

        adapterGoblet = new SpinnerStringAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                arraySpinnerGoblet);
        spinnerGoblet = findViewById(R.id.spinner_goblet_contribute);
        spinnerGoblet.setAdapter(adapterGoblet);

        adapterCirclet = new SpinnerStringAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                arraySpinnerCirclet);
        spinnerCirclet = findViewById(R.id.spinner_circlet_contribute);
        spinnerCirclet.setAdapter(adapterCirclet);

        //edit
        editDescription = findViewById(R.id.edit_description_artifact);
        //button
        btContribute = findViewById(R.id.bt_contribute_artifact);

        handler = new ContributeHandler(this, this);
        progressDialog = new ProgressDialog(this);
    }

    public void dialogFinishContribute(String text){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_message);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams windowAttributes = window.getAttributes();
            windowAttributes.gravity = Gravity.BOTTOM;
            window.setAttributes(windowAttributes);
            dialog.setCancelable(false);

            TextView message = dialog.findViewById(R.id.text_message_dialog);
            message.setText(text);

            //bt yes
            TextView yes = dialog.findViewById(R.id.bt_yes_dialog);
            yes.setOnClickListener(v1 -> {
                dialog.dismiss();
                handler.finishContribute(spinnerCharacter, spinnerWeapon, spinnerSetArtifact1, spinnerSetArtifact2, spinnerSand, spinnerGoblet, spinnerCirclet, editDescription);
            });
        }
        dialog.show();
    }

    @Override
    public void success(String key) {
        switch (key){
            case "PROGRESS_CHARACTER":
                progressCharacter.setVisibility(View.GONE);
                break;
            case "PROGRESS_WEAPON":
                progressWeapon.setVisibility(View.GONE);
                break;
            case "SET2":
                set2();
                break;
            case "SET4":
                set4();
                break;
            case "SENDING":
                progressDialog.setMessage(getResources().getString(R.string.sending));
                progressDialog.show();
                break;
            case "FINISH":
                progressDialog.dismiss();
                dialogFinishContribute(getResources().getString(R.string.thank_you_contribute));
                break;
        }
    }

    @Override
    public void fail(String error) {
        switch (error){
            case "INCOMPLETE_INFORMATION":
                Toast.makeText(this, getResources().getString(R.string.incomplete_information), Toast.LENGTH_SHORT).show();
                break;
            case "INCOMPLETE_DESCRIPTION":
                Toast.makeText(this, getResources().getString(R.string.incomplete_description), Toast.LENGTH_SHORT).show();
                break;
        }
    }



    private void set2(){
        textSet1.setText(getResources().getString(R.string.set2_artifact_first));
        textSet2.setText(getResources().getString(R.string.set2_artifact_second));
        textSet2.setVisibility(View.VISIBLE);
        spinnerSetArtifact2.setVisibility(View.VISIBLE);
    }

    private void set4(){
        textSet1.setText(getResources().getString(R.string.set4_artifact));
        textSet2.setVisibility(View.GONE);
        spinnerSetArtifact2.setVisibility(View.GONE);
    }
}