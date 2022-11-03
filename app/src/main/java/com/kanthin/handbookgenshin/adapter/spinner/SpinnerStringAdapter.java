package com.kanthin.handbookgenshin.adapter.spinner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kanthin.handbookgenshin.R;

import java.util.List;

public class SpinnerStringAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> array;

    public SpinnerStringAdapter(@NonNull Context context, int resource, List<String> objects) {
        super(context, resource);
        this.context = context;
        this.array = objects;
    }

    @Override
    public int getCount(){
        return array.size();
    }

    @Override
    public String getItem(int position){
        return array.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(context.getColor(R.color.color_text));
        label.setText(array.get(position));
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(context.getColor(R.color.color_text));
        label.setText(array.get(position));
        return label;
    }
}
