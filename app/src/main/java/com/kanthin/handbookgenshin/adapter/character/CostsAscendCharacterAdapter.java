package com.kanthin.handbookgenshin.adapter.character;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.MaterialUpAdapter;
import com.kanthin.handbookgenshin.model.ItemCostsAscend;
import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;

import java.util.ArrayList;

public class CostsAscendCharacterAdapter extends RecyclerView.Adapter<CostsAscendCharacterAdapter.CostsAscendCharacterViewHolder> {

    private final Context context;
    private final ArrayList<ItemCostsAscend> array;

    public CostsAscendCharacterAdapter(Context context, ArrayList<ItemCostsAscend> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public CostsAscendCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_costs_ascend, parent, false);
        return new CostsAscendCharacterViewHolder(v);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CostsAscendCharacterViewHolder holder, int position) {
        ItemCostsAscend cost = array.get(position);
        if (cost != null){
            if (position == 0){
                holder.ascend.setText(context.getResources().getString(R.string.ascend));
                holder.lv.setText(context.getResources().getString(R.string.level));
                holder.title.setVisibility(View.VISIBLE);
                holder.recyclerMaterial.setVisibility(View.GONE);
            } else {
                holder.ascend.setText(cost.getAscend());
                holder.title.setVisibility(View.GONE);
                holder.lv.setText(cost.getLv());
                holder.recyclerMaterial.setVisibility(View.VISIBLE);

                //tạo array và lấy dữ liệu
                ArrayList<ItemMaterial> arrayMaterialUp = new ArrayList<>(cost.getArray());
                //adapter
                MaterialUpAdapter adapterMaterialUp = new MaterialUpAdapter(context, arrayMaterialUp);
                //layout manager
                LinearLayoutManager layoutManagerMaterialUp = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                holder.recyclerMaterial.setLayoutManager(layoutManagerMaterialUp);
                holder.recyclerMaterial.setAdapter(adapterMaterialUp);
                adapterMaterialUp.notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }

    static class CostsAscendCharacterViewHolder extends RecyclerView.ViewHolder{

        private final TextView ascend;
        private final TextView lv;
        private final TextView title;
        private final RecyclerView recyclerMaterial;

        public CostsAscendCharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            ascend = itemView.findViewById(R.id.ascend_costs_ascend);
            lv = itemView.findViewById(R.id.lv_costs_ascend);
            title = itemView.findViewById(R.id.title_material_costs_ascend);
            recyclerMaterial = itemView.findViewById(R.id.recycler_material_costs_ascend);
        }
    }
}
