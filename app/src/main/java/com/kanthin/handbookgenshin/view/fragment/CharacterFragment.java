package com.kanthin.handbookgenshin.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.character.CharacterAdapter;
import com.kanthin.handbookgenshin.model.character.Character;
import com.kanthin.handbookgenshin.presenter.CharacterHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.CharacterInterface;
import com.kanthin.handbookgenshin.presenter.anInterface.FilterInterface;
import com.kanthin.handbookgenshin.presenter.filter.FilterCharacter;

import java.util.ArrayList;

public class CharacterFragment extends androidx.fragment.app.Fragment implements CharacterInterface, FilterInterface {

    ImageButton btFilter;
    TextView countItem;
    RecyclerView recycler;
    CharacterAdapter adapter;
    ArrayList<Character> array;
    ArrayList<Character> arrayNotChange;

    CharacterHandler characterHandler;
    FilterCharacter filterCharacter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_character, container, false);
        Init(v);
        characterHandler.loadCharacter(arrayNotChange, array, adapter);
        btFilter.setOnClickListener(v1 -> filterCharacter.dialogFilterCharacter(arrayNotChange, array, adapter));
        return v;
    }

    private void Init(View v) {
        btFilter = v.findViewById(R.id.bt_filter_character);
        countItem = v.findViewById(R.id.count_item_character);
        recycler = v.findViewById(R.id.recycler_character);
        array = new ArrayList<>();
        arrayNotChange = new ArrayList<>();
        adapter = new CharacterAdapter(getContext(), array, R.layout.item_character_grid_view);
        GridLayoutManager managerCharacter = new GridLayoutManager(requireActivity(), 3);
        recycler.setLayoutManager(managerCharacter);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);

        characterHandler = new CharacterHandler(requireActivity(), this, countItem);
        filterCharacter = new FilterCharacter(requireActivity(), this, countItem);
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
