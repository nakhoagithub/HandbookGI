package com.kanthin.handbookgenshin.adapter.character;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.character.talents.LabelAndLever;

import java.util.ArrayList;

public class AttributeCombatAdapter extends RecyclerView.Adapter<AttributeCombatAdapter.AttributeViewHolder> {

    private final Context context;
    private final ArrayList<LabelAndLever> array;

    public AttributeCombatAdapter(Context context, ArrayList<LabelAndLever> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public AttributeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_attribute_combat, parent, false);
        return new AttributeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AttributeViewHolder holder, int position) {
        LabelAndLever labelAndLever = array.get(position);
        if (labelAndLever != null){
            holder.label.setText(labelAndLever.getLabel());
            if (labelAndLever.getParam().size() == 15){
                holder.attr1.setText(labelAndLever.getParam().get(0));
                holder.attr2.setText(labelAndLever.getParam().get(1));
                holder.attr3.setText(labelAndLever.getParam().get(2));
                holder.attr4.setText(labelAndLever.getParam().get(3));
                holder.attr5.setText(labelAndLever.getParam().get(4));
                holder.attr6.setText(labelAndLever.getParam().get(5));
                holder.attr7.setText(labelAndLever.getParam().get(6));
                holder.attr8.setText(labelAndLever.getParam().get(7));
                holder.attr9.setText(labelAndLever.getParam().get(8));
                holder.attr10.setText(labelAndLever.getParam().get(9));
                holder.attr11.setText(labelAndLever.getParam().get(10));
                holder.attr12.setText(labelAndLever.getParam().get(11));
                holder.attr13.setText(labelAndLever.getParam().get(12));
                holder.attr14.setText(labelAndLever.getParam().get(13));
                holder.attr15.setText(labelAndLever.getParam().get(14));
            } else {
                holder.attr1.setText(labelAndLever.getParam().get(0));
            }
        }
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }

    static class AttributeViewHolder extends RecyclerView.ViewHolder{

        private final TextView label;
        private final TextView attr1;
        private final TextView attr2;
        private final TextView attr3;
        private final TextView attr4;
        private final TextView attr5;
        private final TextView attr6;
        private final TextView attr7;
        private final TextView attr8;
        private final TextView attr9;
        private final TextView attr10;
        private final TextView attr11;
        private final TextView attr12;
        private final TextView attr13;
        private final TextView attr14;
        private final TextView attr15;


        public AttributeViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label_attribute_combat);
            attr1 = itemView.findViewById(R.id.lv1_attribute_combat);
            attr2 = itemView.findViewById(R.id.lv2_attribute_combat);
            attr3 = itemView.findViewById(R.id.lv3_attribute_combat);
            attr4 = itemView.findViewById(R.id.lv4_attribute_combat);
            attr5 = itemView.findViewById(R.id.lv5_attribute_combat);
            attr6 = itemView.findViewById(R.id.lv6_attribute_combat);
            attr7 = itemView.findViewById(R.id.lv7_attribute_combat);
            attr8 = itemView.findViewById(R.id.lv8_attribute_combat);
            attr9 = itemView.findViewById(R.id.lv9_attribute_combat);
            attr10 = itemView.findViewById(R.id.lv10_attribute_combat);
            attr11 = itemView.findViewById(R.id.lv11_attribute_combat);
            attr12 = itemView.findViewById(R.id.lv12_attribute_combat);
            attr13 = itemView.findViewById(R.id.lv13_attribute_combat);
            attr14 = itemView.findViewById(R.id.lv14_attribute_combat);
            attr15 = itemView.findViewById(R.id.lv15_attribute_combat);

        }
    }
}
