package com.kanthin.handbookgenshin.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.TooltipCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;
import com.kanthin.handbookgenshin.view.InfoMaterialActivity;

import java.util.ArrayList;

public class MaterialUpAdapter extends RecyclerView.Adapter<MaterialUpAdapter.MaterialUpViewHolder> {

    private final Context context;
    private final ArrayList<ItemMaterial> array;

    public MaterialUpAdapter(Context context, ArrayList<ItemMaterial> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MaterialUpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_material_up, parent, false);
        return new MaterialUpViewHolder(v, array);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialUpViewHolder holder, int position) {
        ItemMaterial itemMaterial = array.get(position);
        if (itemMaterial != null) {
            holder.countMaterial.setText(String.valueOf(itemMaterial.getCount()));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                holder.imageMaterialUp.setTooltipText(itemMaterial.getName());
            } else TooltipCompat.setTooltipText(holder.imageMaterialUp, itemMaterial.getName());
            getImage(itemMaterial.getName(), holder.imageMaterialUp, holder.background);
        }
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }

    static class MaterialUpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final RelativeLayout background;
        private final ImageView imageMaterialUp;
        private final TextView countMaterial;
        private final ArrayList<ItemMaterial> array;

        public MaterialUpViewHolder(@NonNull View itemView, ArrayList<ItemMaterial> array) {
            super(itemView);
            this.array = array;
            background = itemView.findViewById(R.id.background_rarity_material_up);
            imageMaterialUp = itemView.findViewById(R.id.image_material_up);
            countMaterial = itemView.findViewById(R.id.count_material_up);
            imageMaterialUp.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (array.get(getAdapterPosition()).getRarity() == null){
                Intent i = new Intent(itemView.getContext(), InfoMaterialActivity.class);
                i.putExtra("name", array.get(getAdapterPosition()).getName());
                v.getContext().startActivity(i);
            }
        }
    }

    private void getImage(String name, ImageView imageMaterialUp, RelativeLayout background) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "vi");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language)
                .child("materials").child(name);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = String.valueOf(snapshot.child("images").child("fandom").getValue());
                Glide.with(context.getApplicationContext())
                        .load(image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .into(imageMaterialUp);
                String rarity = String.valueOf(snapshot.child("rarity").getValue());
                switch (rarity) {
                    case "2":
                        background.setBackgroundResource(R.drawable.background_2s);
                        break;
                    case "3":
                        background.setBackgroundResource(R.drawable.background_3s);
                        break;
                    case "4":
                        background.setBackgroundResource(R.drawable.background_4s);
                        break;
                    case "5":
                        background.setBackgroundResource(R.drawable.background_5s);
                        break;
                    default: background.setBackgroundResource(R.drawable.background_1s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
