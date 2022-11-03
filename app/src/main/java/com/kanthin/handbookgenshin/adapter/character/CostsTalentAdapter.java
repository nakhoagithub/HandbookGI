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
import com.kanthin.handbookgenshin.model.character.talents.ItemCostsTalent;
import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;

import java.util.ArrayList;

public class CostsTalentAdapter extends RecyclerView.Adapter<CostsTalentAdapter.CostsTalentViewHolder> {

    private final Context context;
    private final ArrayList<ItemCostsTalent> array;

    public CostsTalentAdapter(Context context, ArrayList<ItemCostsTalent> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public CostsTalentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_costs_talents, parent, false);
        return new CostsTalentViewHolder(v);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull CostsTalentViewHolder holder, int position) {
        ItemCostsTalent cost = array.get(position);
        if (cost != null){
            if (position == 0){
                holder.lv.setText(context.getResources().getString(R.string.level));
                holder.title.setVisibility(View.VISIBLE);
                holder.recyclerMaterial.setVisibility(View.GONE);
            } else {
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

    static class CostsTalentViewHolder extends RecyclerView.ViewHolder{

        private final TextView lv;
        private final RecyclerView recyclerMaterial;
        private final TextView title;

        public CostsTalentViewHolder(@NonNull View itemView) {
            super(itemView);
            lv = itemView.findViewById(R.id.lv_costs_talent);
            recyclerMaterial = itemView.findViewById(R.id.recycler_material_costs_talent);
            title = itemView.findViewById(R.id.title_material_costs_talent);
        }
    }
}
