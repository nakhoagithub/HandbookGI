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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.artifact.ArtifactAdapter;
import com.kanthin.handbookgenshin.model.artifact.Artifact;
import com.kanthin.handbookgenshin.presenter.ArtifactHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.ArtifactInterface;
import com.kanthin.handbookgenshin.presenter.anInterface.FilterInterface;
import com.kanthin.handbookgenshin.presenter.filter.FilterArtifact;

import java.util.ArrayList;

public class ArtifactFragment extends Fragment implements ArtifactInterface, FilterInterface {

    ImageButton btFilter;
    TextView countItem;
    RecyclerView recycler;
    ArtifactAdapter adapter;
    ArrayList<Artifact> array;
    ArrayList<Artifact> arrayNotChange;

    ArtifactHandler artifactHandler;
    FilterArtifact filterArtifact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_artifact, container, false);
        Init(v);
        artifactHandler.loadArtifact(arrayNotChange, array, adapter);
        btFilter.setOnClickListener(v1 -> filterArtifact.dialogFilterArtifact(arrayNotChange, array, adapter));
        return v;
    }

    private void Init(View v) {
        btFilter = v.findViewById(R.id.bt_filter_artifact);
        countItem = v.findViewById(R.id.count_item_artifact);
        recycler = v.findViewById(R.id.recycler_artifact);
        array = new ArrayList<>();
        arrayNotChange = new ArrayList<>();
        adapter = new ArtifactAdapter(getContext(), array, R.layout.item_artifact);
        LinearLayoutManager managerCharacter = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(managerCharacter);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);

        artifactHandler = new ArtifactHandler(requireActivity(), this, countItem);
        filterArtifact = new FilterArtifact(requireActivity(), this, countItem);
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
