package com.kanthin.handbookgenshin.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.weapon.WeaponAdapter;
import com.kanthin.handbookgenshin.model.weapon.Weapon;
import com.kanthin.handbookgenshin.presenter.filter.FilterWeapon;
import com.kanthin.handbookgenshin.presenter.WeaponHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.FilterInterface;
import com.kanthin.handbookgenshin.presenter.anInterface.WeaponInterface;

import java.util.ArrayList;

public class WeaponFragment extends Fragment implements WeaponInterface, FilterInterface {

    ImageButton btFilter;
    TextView countItem;
    RecyclerView recycler;
    WeaponAdapter adapter;
    ArrayList<Weapon> array;
    ArrayList<Weapon> arrayNotChange;

    WeaponHandler weaponHandler;
    FilterWeapon filterWeapon;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weapon, container, false);
        Init(v);
        weaponHandler.loadWeapon(arrayNotChange, array, adapter);
        btFilter.setOnClickListener(v1 -> filterWeapon.dialogFilterWeapon(arrayNotChange, array, adapter));
        return v;
    }

    private void Init(View v) {
        btFilter = v.findViewById(R.id.bt_filter_weapon);
        countItem = v.findViewById(R.id.count_item_weapon);
        recycler = v.findViewById(R.id.recycler_weapon);
        array = new ArrayList<>();
        arrayNotChange = new ArrayList<>();
        adapter = new WeaponAdapter(getContext(), array, R.layout.item_weapon_grid_view);
        GridLayoutManager managerCharacter = new GridLayoutManager(requireActivity(), 3);
        recycler.setLayoutManager(managerCharacter);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);

        weaponHandler = new WeaponHandler(requireActivity(), this, countItem);
        filterWeapon = new FilterWeapon(requireActivity(), this, countItem);
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String error) {

    }

    @Override
    public void nullFilter() {
        Snackbar.make(requireView(), R.string.not_result, 3000)
                .setAction("OK", view -> {

                })
                .show();
    }
}
