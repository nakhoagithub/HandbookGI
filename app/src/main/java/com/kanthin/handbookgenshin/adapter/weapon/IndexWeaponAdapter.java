package com.kanthin.handbookgenshin.adapter.weapon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.presenter.CustomStringLanguage;
import com.kanthin.handbookgenshin.model.weapon.StatsWeapon;

import java.util.ArrayList;

public class IndexWeaponAdapter extends RecyclerView.Adapter<IndexWeaponAdapter.IndexWeaponViewHolder> {

    private final Context context;
    private final ArrayList<StatsWeapon> array;

    public IndexWeaponAdapter(Context context, ArrayList<StatsWeapon> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public IndexWeaponViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_index_weapon, parent, false);
        return new IndexWeaponViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexWeaponViewHolder holder, int position) {
        StatsWeapon statsWeapon = array.get(position);
        if (statsWeapon != null){
            if (position == 0){
                holder.lv.setText(context.getResources().getString(R.string.level));
                holder.atk.setText(context.getResources().getString(R.string.attack));
                holder.substats.setText(new CustomStringLanguage(context).handlerStringSubStats(statsWeapon.getSubstatKey()));
            } else {
                String lv = String.valueOf(statsWeapon.getLvKey());
                String atk = String.valueOf(statsWeapon.getAttack());
                String substat = String.valueOf(statsWeapon.getSpecialized());
                holder.lv.setText(lv);
                holder.atk.setText(atk.substring(0, atk.indexOf(".")));
                holder.substats.setText(substat);
            }
        }
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }

    static class IndexWeaponViewHolder extends RecyclerView.ViewHolder{

        private final TextView lv;
        private final TextView atk;
        private final TextView substats;

        public IndexWeaponViewHolder(@NonNull View itemView) {
            super(itemView);
            lv = itemView.findViewById(R.id.item_lv_index_weapon);
            atk = itemView.findViewById(R.id.item_atk_index_weapon);
            substats = itemView.findViewById(R.id.item_substat_index_weapon);
        }
    }
}
