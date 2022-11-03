package com.kanthin.handbookgenshin.adapter.character;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.presenter.CustomStringLanguage;
import com.kanthin.handbookgenshin.model.character.StatsCharacter;

import java.util.ArrayList;

public class IndexCharacterAdapter extends RecyclerView.Adapter<IndexCharacterAdapter.IndexCharacterViewHolder> {

    private final Context context;
    private final ArrayList<StatsCharacter> array;

    public IndexCharacterAdapter(Context context, ArrayList<StatsCharacter> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public IndexCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_index_character, parent, false);
        return new IndexCharacterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexCharacterViewHolder holder, int position) {
        StatsCharacter statsCharacter = array.get(position);
        if (statsCharacter != null){
            if (position == 0){
                holder.lv.setText(context.getResources().getString(R.string.level));
                holder.atk.setText(context.getResources().getString(R.string.attack));
                holder.hp.setText(context.getResources().getString(R.string.hp));
                holder.def.setText(context.getResources().getString(R.string.defence));
                holder.substats.setText(new CustomStringLanguage(context).handlerStringSubStats(statsCharacter.getSubstatKey()));
            } else {
                String lv = String.valueOf(statsCharacter.getLvKey());
                String atk = String.valueOf(statsCharacter.getAttack());
                String hp = String.valueOf(statsCharacter.getHp());
                String def = String.valueOf(statsCharacter.getDefense());
                String substat = String.valueOf(statsCharacter.getSpecialized());
                holder.lv.setText(lv);
                holder.atk.setText(atk.substring(0, atk.indexOf(".")));
                holder.hp.setText(hp.substring(0, hp.indexOf(".")));
                holder.def.setText(def.substring(0, def.indexOf(".")));
                holder.substats.setText(substat);
            }

        }
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }

    static class IndexCharacterViewHolder extends RecyclerView.ViewHolder{

        private final TextView lv;
        private final TextView atk;
        private final TextView hp;
        private final TextView def;
        private final TextView substats;

        public IndexCharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            lv = itemView.findViewById(R.id.item_lv_index_character);
            atk = itemView.findViewById(R.id.item_atk_index_character);
            hp = itemView.findViewById(R.id.item_hp_index_character);
            def = itemView.findViewById(R.id.item_def_index_character);
            substats = itemView.findViewById(R.id.item_substat_index_character);
        }
    }
}
