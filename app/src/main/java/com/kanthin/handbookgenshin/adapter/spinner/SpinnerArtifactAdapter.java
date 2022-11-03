package com.kanthin.handbookgenshin.adapter.spinner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.index.IndexArtifact;

import java.util.List;

public class SpinnerArtifactAdapter extends BaseAdapter {

    private final Context context;
    private final List<IndexArtifact> array;
    private final SharedPreferences sharedPreferences;

    public SpinnerArtifactAdapter(Context context, List<IndexArtifact> array) {
        this.context = context;
        this.array = array;
        sharedPreferences = context.getSharedPreferences("setting", 0);
    }

    @Override
    public int getCount() {
        return array != null ? array.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return array.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_spinner_image, viewGroup, false);

        CardView cardImage = view.findViewById(R.id.card_image);
        TextView name = view.findViewById(R.id.name_item_spinner);
        ImageView image = view.findViewById(R.id.image_item_spinner);

        if (sharedPreferences.getString("language", "vi").equals("vi")) {
            name.setText(array.get(i).getVi());
        } else name.setText(array.get(i).getEn());

        if (i == 0) cardImage.setVisibility(View.GONE);
        else cardImage.setVisibility(View.VISIBLE);


        if (array.get(i).getImages() != null) {
            if (array.get(i).getImages().getFlower() != null) {
                Glide.with(context)
                        .load(array.get(i).getImages().getFlower())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_null)
                        .into(image);
            }
        }

        return view;
    }
}
