package com.kanthin.handbookgenshin.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.kanthin.handbookgenshin.view.fragment.ArtifactFragment;
import com.kanthin.handbookgenshin.view.fragment.CharacterFragment;
import com.kanthin.handbookgenshin.view.fragment.HomeFragment;
import com.kanthin.handbookgenshin.view.fragment.MaterialFragment;
import com.kanthin.handbookgenshin.view.fragment.WeaponFragment;

public class MainAdapter extends FragmentStateAdapter {

    public MainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public androidx.fragment.app.Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new CharacterFragment();
            case 2:
                return new WeaponFragment();
            case 3:
                return new ArtifactFragment();
            case 4:
                return new MaterialFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }


}
