package com.kanthin.handbookgenshin.presenter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.spinner.SpinnerArtifactAdapter;
import com.kanthin.handbookgenshin.adapter.spinner.SpinnerCharacterAdapter;
import com.kanthin.handbookgenshin.adapter.spinner.SpinnerStringAdapter;
import com.kanthin.handbookgenshin.adapter.spinner.SpinnerWeaponAdapter;
import com.kanthin.handbookgenshin.model.character.BuildCharacter;
import com.kanthin.handbookgenshin.model.index.IndexArtifact;
import com.kanthin.handbookgenshin.model.index.IndexCharacter;
import com.kanthin.handbookgenshin.model.index.IndexWeapon;
import com.kanthin.handbookgenshin.presenter.anInterface.InterfaceNotification;

import java.util.ArrayList;

public class ContributeHandler {
    private final Context context;
    private final InterfaceNotification anInterface;

    public ContributeHandler(Context context, InterfaceNotification anInterface) {
        this.context = context;
        this.anInterface = anInterface;
    }

    public void viewLayoutSetArtifact(boolean b) {
        if (b) {
            anInterface.success("SET2");
        } else {
            anInterface.success("SET4");
        }
    }

    public void loadSpinnerCharacter(ArrayList<IndexCharacter> arrayCharacter, SpinnerCharacterAdapter adapterCharacter) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("index").child("characters").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String not = context.getString(R.string.not);
                arrayCharacter.add(new IndexCharacter(not));
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    IndexCharacter indexCharacter = dataSnapshot.getValue(IndexCharacter.class);
                    arrayCharacter.add(indexCharacter);
                }
                adapterCharacter.notifyDataSetChanged();
                anInterface.success("PROGRESS_CHARACTER");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadSpinnerWeapon(ArrayList<IndexWeapon> arrayWeapon, ArrayList<IndexWeapon> arrayNotChangeWeapon, SpinnerWeaponAdapter adapterWeapon) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("index").child("weapons").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String not = context.getString(R.string.not);
                arrayWeapon.add(new IndexWeapon(not, not, "null"));
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    IndexWeapon indexWeapon = dataSnapshot.getValue(IndexWeapon.class);
                    if (indexWeapon != null) {
                        if (indexWeapon.getRarity().equals("3") ||
                                indexWeapon.getRarity().equals("4") ||
                                indexWeapon.getRarity().equals("5")) {
                            arrayWeapon.add(indexWeapon);
                        }
                    }
                }
                arrayNotChangeWeapon.addAll(arrayWeapon);
                adapterWeapon.notifyDataSetChanged();
                anInterface.success("PROGRESS_WEAPON");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void handlerSpinnerCharacterAndWeapon(Spinner spinnerCharacter, Spinner spinnerWeapon,
                                                 ArrayList<IndexWeapon> arrayWeapon, ArrayList<IndexWeapon> arrayNotChangeWeapon,
                                                 SpinnerWeaponAdapter adapterWeapon) {
        spinnerCharacter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerWeapon.setEnabled(i != 0);
                ArrayList<IndexWeapon> arrayChange = new ArrayList<>(arrayNotChangeWeapon);
                IndexCharacter indexCharacter = (IndexCharacter) adapterView.getSelectedItem();
                if (indexCharacter.getWeapontype() != null) {
                    if (indexCharacter.getWeapontype().contains("Kiếm Đơn")) {
                        arrayChange.removeIf(weapon -> !weapon.getWeapontype().contains("Kiếm Đơn"));
                    }
                    if (indexCharacter.getWeapontype().contains("Trọng Kiếm")) {
                        arrayChange.removeIf(weapon -> !weapon.getWeapontype().contains("Trọng Kiếm"));
                    }
                    if (indexCharacter.getWeapontype().contains("Cung")) {
                        arrayChange.removeIf(weapon -> !weapon.getWeapontype().contains("Cung"));
                    }
                    if (indexCharacter.getWeapontype().contains("Vũ Khí Cán Dài")) {
                        arrayChange.removeIf(weapon -> !weapon.getWeapontype().contains("Vũ Khí Cán Dài"));
                    }
                    if (indexCharacter.getWeapontype().contains("Pháp Khí")) {
                        arrayChange.removeIf(weapon -> !weapon.getWeapontype().contains("Pháp Khí"));
                    }
                }
                arrayWeapon.clear();
                String not = context.getString(R.string.not);
                arrayWeapon.add(new IndexWeapon(not, not, "null"));
                arrayWeapon.addAll(arrayChange);
                spinnerWeapon.setSelection(0);
                adapterWeapon.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void loadSpinnerArtifact(ArrayList<IndexArtifact> arrayArtifact, SpinnerArtifactAdapter adapterArtifact) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("index").child("artifacts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String not = context.getString(R.string.not);
                arrayArtifact.add(new IndexArtifact(not, not));
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    IndexArtifact indexArtifact = dataSnapshot.getValue(IndexArtifact.class);
                    if (indexArtifact != null) {
                        if (indexArtifact.getImages().getFlower() != null) {
                            arrayArtifact.add(indexArtifact);
                        }
                    }
                }
                adapterArtifact.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadSpinnerMainStatSand(ArrayList<String> arraySpinnerSand, SpinnerStringAdapter adapterSand) {
        arraySpinnerSand.add(context.getString(R.string.not));
        arraySpinnerSand.add(context.getString(R.string.hp));
        arraySpinnerSand.add(context.getString(R.string.def));
        arraySpinnerSand.add(context.getString(R.string.attack));
        arraySpinnerSand.add(context.getString(R.string.energy_recharge));
        arraySpinnerSand.add(context.getString(R.string.elemental_mastery));
        adapterSand.notifyDataSetChanged();
    }

    public void loadSpinnerMainStatGoblet(ArrayList<String> arraySpinnerGoblet, SpinnerStringAdapter adapterGoblet) {
        arraySpinnerGoblet.add(context.getString(R.string.not));
        arraySpinnerGoblet.add(context.getString(R.string.hp));
        arraySpinnerGoblet.add(context.getString(R.string.def));
        arraySpinnerGoblet.add(context.getString(R.string.attack));
        arraySpinnerGoblet.add(context.getString(R.string.dame_physical));
        arraySpinnerGoblet.add(context.getString(R.string.dame_element));
        arraySpinnerGoblet.add(context.getString(R.string.elemental_mastery));
        adapterGoblet.notifyDataSetChanged();
    }

    public void loadSpinnerMainStatCirclet(ArrayList<String> arraySpinnerCirclet, SpinnerStringAdapter adapterCirclet) {
        arraySpinnerCirclet.add(context.getString(R.string.not));
        arraySpinnerCirclet.add(context.getString(R.string.hp));
        arraySpinnerCirclet.add(context.getString(R.string.def));
        arraySpinnerCirclet.add(context.getString(R.string.attack));
        arraySpinnerCirclet.add(context.getString(R.string.elemental_mastery));
        arraySpinnerCirclet.add(context.getString(R.string.crit_rate));
        arraySpinnerCirclet.add(context.getString(R.string.crit_dame));
        arraySpinnerCirclet.add(context.getString(R.string.healing_bonus));
        adapterCirclet.notifyDataSetChanged();
    }

    public void contributeArtifactCharacter(Spinner spinnerCharacter, Spinner spinnerWeapon, RadioButton radioSet2, Spinner spinnerSetArtifact1, Spinner spinnerSetArtifact2, Spinner spinnerSand, Spinner spinnerGoblet, Spinner spinnerCirclet, TextInputEditText editDescription) {
        IndexCharacter indexCharacter = (IndexCharacter) spinnerCharacter.getSelectedItem();
        IndexWeapon indexWeapon = (IndexWeapon) spinnerWeapon.getSelectedItem();
        String character = indexCharacter.getName();
        String weapon = indexWeapon.getVi();
        IndexArtifact indexArtifact1 = (IndexArtifact) spinnerSetArtifact1.getSelectedItem();
        IndexArtifact indexArtifact2 = (IndexArtifact) spinnerSetArtifact2.getSelectedItem();
        String artifactSet2First = indexArtifact1.getVi() + "";
        String artifactSet2Second = indexArtifact2.getVi() + "";
        String sand = spinnerSand.getSelectedItemPosition() + "";
        String goblet = spinnerGoblet.getSelectedItemPosition() + "";
        String circlet = spinnerCirclet.getSelectedItemPosition() + "";
        String description = "";
        if (editDescription.getText() != null) {
            description = editDescription.getText().toString();
        }
        checkContribute(character, weapon, radioSet2, artifactSet2First, artifactSet2Second, sand, goblet, circlet, description);
    }

    private void checkContribute(String character, String weapon, RadioButton radioSet2, String artifactSet2First,
                                 String artifactSet2Second, String sand, String goblet, String circlet,
                                 String description) {
        if (character.equals(context.getString(R.string.not)) || weapon.equals(context.getString(R.string.not)) ||
                sand.equals("0") || sand.equals("") ||
                goblet.equals("0") || goblet.equals("") ||
                circlet.equals("0") || circlet.equals("") ||
                description.length() == 0) {
            anInterface.fail("INCOMPLETE_INFORMATION");
        } else {
            if (radioSet2.isChecked()) {
                if (artifactSet2First.equals("0") || artifactSet2First.equals("") ||
                        artifactSet2Second.equals("0") || artifactSet2Second.equals("")) {
                    anInterface.fail("INCOMPLETE_INFORMATION");
                } else {
                    pushContributeSet2(character, weapon, artifactSet2First, artifactSet2Second, sand, goblet, circlet, description);
                }
            } else {
                if (artifactSet2First.equals("0") || artifactSet2First.equals("")) {
                    anInterface.fail("INCOMPLETE_INFORMATION");
                } else {
                    pushContributeSet4(character, weapon, artifactSet2First, sand, goblet, circlet, description);
                }
            }
        }

    }

    private void pushContributeSet2(String character, String weapon, String artifactSet2First,
                                    String artifactSet2Second, String sand, String goblet, String circlet,
                                    String description) {
        anInterface.success("SENDING");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        BuildCharacter buildCharacter = new BuildCharacter(character, weapon, artifactSet2First, artifactSet2Second, sand, goblet, circlet, description);
        db.child("contributes").child("buildCharacters").push().setValue(buildCharacter)
                .addOnSuccessListener(unused -> anInterface.success("FINISH"));
    }

    private void pushContributeSet4(String character, String weapon, String artifactSet2First, String sand, String goblet, String circlet, String description) {
        anInterface.success("SENDING");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        BuildCharacter buildCharacter = new BuildCharacter(character, weapon, artifactSet2First, sand, goblet, circlet, description);
        db.child("contributes").child("buildCharacters").push().setValue(buildCharacter)
                .addOnSuccessListener(unused -> anInterface.success("FINISH"));
    }

    public void finishContribute(Spinner spinnerCharacter, Spinner spinnerWeapon, Spinner spinnerSetArtifact1, Spinner spinnerSetArtifact2,
                                 Spinner spinnerSand, Spinner spinnerGoblet, Spinner spinnerCirclet,
                                 TextInputEditText editDescription) {
        spinnerCharacter.setSelection(0);
        spinnerWeapon.setSelection(0);
        spinnerSetArtifact1.setSelection(0);
        spinnerSetArtifact2.setSelection(0);
        spinnerSand.setSelection(0);
        spinnerGoblet.setSelection(0);
        spinnerCirclet.setSelection(0);
        editDescription.setText("");
    }
}
