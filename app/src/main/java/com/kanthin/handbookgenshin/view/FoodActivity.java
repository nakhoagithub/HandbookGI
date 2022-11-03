package com.kanthin.handbookgenshin.view;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.food.FoodAdapter;
import com.kanthin.handbookgenshin.model.index.IndexFood;
import com.kanthin.handbookgenshin.presenter.FoodHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.Interface;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity implements Interface {

    ImageButton btBack;
    RecyclerView recyclerFood;
    ArrayList<IndexFood> arrayFood;
    FoodAdapter adapterFood;
    FoodHandler handler;
    TextView countFood;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Init();

        btBack.setOnClickListener(v -> onBackPressed());

        handler.viewFood(arrayFood, adapterFood, countFood);
    }

    private void Init() {
        btBack = findViewById(R.id.bt_back_food);

        countFood = findViewById(R.id.count_item_food);
        recyclerFood = findViewById(R.id.recycler_food);

        arrayFood = new ArrayList<>();
        adapterFood = new FoodAdapter(this, arrayFood, R.layout.item_food_grid_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerFood.setLayoutManager(layoutManager);
        recyclerFood.setAdapter(adapterFood);

        handler = new FoodHandler(this, this);
    }

    @Override
    public void success(String key) {

    }

    @Override
    public void fail(String error) {

    }
}