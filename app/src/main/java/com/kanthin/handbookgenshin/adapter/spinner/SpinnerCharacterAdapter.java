package com.kanthin.handbookgenshin.adapter.spinner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.index.IndexCharacter;

import java.util.List;

public class SpinnerCharacterAdapter extends BaseAdapter {
    private final Context context;
    private final List<IndexCharacter> array;

    public SpinnerCharacterAdapter(Context context, List<IndexCharacter> array) {
        this.context = context;
        this.array = array;
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
        RelativeLayout layout = view.findViewById(R.id.layout);

        name.setText(array.get(i).getName());

        if (i == 0)
            cardImage.setVisibility(View.GONE);
        else cardImage.setVisibility(View.VISIBLE);

        if (array.get(i).getImages() != null) {
            if (array.get(i).getImages().getIcon() != null) {
                cardImage.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(array.get(i).getImages().getIcon())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_null)
                        .into(image);

            }
        }
        if (array.get(i).getRarity() != null) {
            switch (array.get(i).getRarity()) {
                case "4":
                    layout.setBackgroundResource(R.drawable.background_4s);
                    break;
                case "5":
                    layout.setBackgroundResource(R.drawable.background_5s);
                    break;
            }
        }
        return view;
    }
}
