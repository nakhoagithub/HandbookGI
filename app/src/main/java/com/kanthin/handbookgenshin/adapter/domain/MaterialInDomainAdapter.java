package com.kanthin.handbookgenshin.adapter.domain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
import com.kanthin.handbookgenshin.model.artifact.Artifact;
import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;
import com.kanthin.handbookgenshin.model.material.Material;
import com.kanthin.handbookgenshin.view.InfoArtifactActivity;
import com.kanthin.handbookgenshin.view.InfoMaterialActivity;

import java.util.ArrayList;

public class MaterialInDomainAdapter extends RecyclerView.Adapter<MaterialInDomainAdapter.MaterialInDomainViewHolder> {

    private final Context context;
    private final ArrayList<ItemMaterial> array;

    public MaterialInDomainAdapter(Context context, ArrayList<ItemMaterial> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MaterialInDomainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_material_in_domain, parent, false);
        return new MaterialInDomainViewHolder(v, array);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialInDomainViewHolder holder, int position) {
        ItemMaterial itemMaterial = array.get(position);
        if (itemMaterial != null) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                holder.imageMaterialUp.setTooltipText(itemMaterial.getName());
            } else TooltipCompat.setTooltipText(holder.imageMaterialUp, itemMaterial.getName());

            getInfoMaterial(itemMaterial, holder.imageMaterialUp, holder.background);
        }
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(getItemCount() - position - 1);
    }

    static class MaterialInDomainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final RelativeLayout background;
        private final ImageView imageMaterialUp;
        private final ArrayList<ItemMaterial> array;

        public MaterialInDomainViewHolder(@NonNull View itemView, ArrayList<ItemMaterial> array) {
            super(itemView);
            this.array = array;
            background = itemView.findViewById(R.id.background_rarity_material_in_domain);
            imageMaterialUp = itemView.findViewById(R.id.image_material_in_domain);
            imageMaterialUp.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (array.get(getAdapterPosition()).getRarity() == null){
                Intent i = new Intent(itemView.getContext(), InfoMaterialActivity.class);
                i.putExtra("name", array.get(getAdapterPosition()).getName());
                v.getContext().startActivity(i);
            } else {
                Intent i = new Intent(itemView.getContext(), InfoArtifactActivity.class);
                i.putExtra("name", array.get(getAdapterPosition()).getName());
                v.getContext().startActivity(i);
            }
        }
    }

    private void getInfoMaterial(ItemMaterial itemMaterial, ImageView imageItem, RelativeLayout background) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language)
                .child("materials").child(itemMaterial.getName());
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Material material = snapshot.getValue(Material.class);
                if (itemMaterial.getRarity() != null){
                    getArtifact(itemMaterial.getName(), itemMaterial.getRarity(), imageItem, background);
                }
                if (material != null){
                    String image = material.getImages().getFandom();
                    Glide.with(context.getApplicationContext())
                            .load(image)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .into(imageItem);

                    switch (material.getRarity()) {
                        case "1":
                            background.setBackgroundResource(R.drawable.background_1s);
                            break;
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
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void getArtifact(String name, String rarity, ImageView imageItem, RelativeLayout background) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(language)
                .child("artifacts").child(name);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Artifact artifact = snapshot.getValue(Artifact.class);
                if (artifact != null){
                    String image = artifact.getImages().getFlower();
                    Glide.with(context.getApplicationContext())
                            .load(image)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .into(imageItem);

                    switch (rarity) {
                        case "1":
                            background.setBackgroundResource(R.drawable.background_1s);
                            break;
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
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
