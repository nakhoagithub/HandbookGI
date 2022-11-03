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
import com.kanthin.handbookgenshin.adapter.material.MaterialAdapter;
import com.kanthin.handbookgenshin.model.material.Material;
import com.kanthin.handbookgenshin.presenter.MaterialHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.FilterInterface;
import com.kanthin.handbookgenshin.presenter.anInterface.MaterialInterface;
import com.kanthin.handbookgenshin.presenter.filter.FilterMaterial;

import java.util.ArrayList;

public class MaterialFragment extends Fragment implements MaterialInterface, FilterInterface {

    ImageButton btFilter;
    TextView countItem;
    RecyclerView recycler;
    MaterialAdapter adapter;
    ArrayList<Material> array;
    ArrayList<Material> arrayNotChange;

    MaterialHandler materialHandler;
    FilterMaterial filterMaterial;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_material, container, false);
        Init(v);
        materialHandler.loadMaterial(arrayNotChange, array, adapter);
        btFilter.setOnClickListener(v1 -> filterMaterial.dialogFilterMaterial(arrayNotChange, array, adapter));
        return v;
    }

    private void Init(View v) {
        btFilter = v.findViewById(R.id.bt_filter_material);
        countItem = v.findViewById(R.id.count_item_material);
        recycler = v.findViewById(R.id.recycler_material);
        array = new ArrayList<>();
        arrayNotChange = new ArrayList<>();
        adapter = new MaterialAdapter(getContext(), array, R.layout.item_material_grid_view);
        GridLayoutManager managerMaterial = new GridLayoutManager(requireActivity(), 3);
        recycler.setLayoutManager(managerMaterial);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);

        materialHandler = new MaterialHandler(requireActivity(), this, countItem);
        filterMaterial = new FilterMaterial(requireActivity(), this, countItem);
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
