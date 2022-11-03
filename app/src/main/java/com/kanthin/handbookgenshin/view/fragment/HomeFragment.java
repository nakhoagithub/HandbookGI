package com.kanthin.handbookgenshin.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.domain.DomainAdapter;
import com.kanthin.handbookgenshin.model.domain.Domain;
import com.kanthin.handbookgenshin.presenter.HomeHandler;
import com.kanthin.handbookgenshin.view.ContributeActivity;
import com.kanthin.handbookgenshin.view.DomainActivity;
import com.kanthin.handbookgenshin.view.ElementActivity;
import com.kanthin.handbookgenshin.view.EnemyActivity;
import com.kanthin.handbookgenshin.view.FoodActivity;
import com.kanthin.handbookgenshin.view.GenshinMapActivity;
import com.kanthin.handbookgenshin.view.MenuActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    //TemplateView template;

    ImageButton btMenu;
    RelativeLayout btGenshinMap, btDomain, btEnemy, btFood, btElement, btContributeArtifact;
    TextView dayInWeek, admob, notification;
    ImageView imageBackground;
    RecyclerView recyclerDomainToday;

    //char rate up
    DomainAdapter adapterDomainToday;
    ArrayList<Domain> arrayDomainToday;

    //slide
    HomeHandler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Init(v);
        btMenu.setOnClickListener(v1 -> {
            startActivity(new Intent(requireActivity(), MenuActivity.class));
            //in left là màn hình second chạy qua bến phai
            requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        btGenshinMap.setOnClickListener(v1 -> {
            startActivity(new Intent(requireActivity(), GenshinMapActivity.class));
            requireActivity().overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
        });

        btDomain.setOnClickListener(v1 -> {
            startActivity(new Intent(requireActivity(), DomainActivity.class));
            requireActivity().overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
        });

        btEnemy.setOnClickListener(v1 -> {
            startActivity(new Intent(requireActivity(), EnemyActivity.class));
            requireActivity().overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
        });

        btFood.setOnClickListener(v1 -> {
            startActivity(new Intent(requireActivity(), FoodActivity.class));
            requireActivity().overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
        });

        btElement.setOnClickListener(v1 -> {
            startActivity(new Intent(requireActivity(), ElementActivity.class));
            requireActivity().overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
        });

        btContributeArtifact.setOnClickListener(v1 -> {
            startActivity(new Intent(requireActivity(), ContributeActivity.class));
            requireActivity().overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
        });

        handler.viewBackground(imageBackground, dayInWeek);
        handler.viewDomainToday(arrayDomainToday, adapterDomainToday);
        handler.viewNotification(notification);

        //chạy quảng cáo
//        AdLoader adLoader = new AdLoader.Builder(requireContext(), getString(R.string.ads_native))
//                .forNativeAd(NativeAd -> {
//                    // Show the ad.
//                    if (requireActivity().isDestroyed()) {
//                        NativeAd.destroy();
//                        return;
//                    }
//                    NativeTemplateStyle styles = new
//                            NativeTemplateStyle.Builder().build();
//                    template.setStyles(styles);
//                    template.setVisibility(View.VISIBLE);
//                    admob.setVisibility(View.VISIBLE);
//                    template.setNativeAd(NativeAd);
//                })
//                .withAdListener(new AdListener() {
//                    @Override
//                    public void onAdFailedToLoad(@NonNull LoadAdError adError) {
//                        // Handle the failure by logging, altering the UI, and so on.
//                    }
//                })
//                .withNativeAdOptions(new NativeAdOptions.Builder()
//                        // Methods in the NativeAdOptions.Builder class can be
//                        // used here to specify individual options settings.
//                        .build())
//                .build();
//        adLoader.loadAd(new AdRequest.Builder().build());

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void Init(View v) {
        //template = v.findViewById(R.id.my_template);
        notification = v.findViewById(R.id.notification_home);

        btMenu = v.findViewById(R.id.bt_menu);

        //bt home
        btGenshinMap = v.findViewById(R.id.bt_genshin_map);
        btDomain = v.findViewById(R.id.bt_domain_home);
        btEnemy = v.findViewById(R.id.bt_enemy_home);
        btFood = v.findViewById(R.id.bt_food_home);
        btElement = v.findViewById(R.id.bt_element_home);
        btContributeArtifact = v.findViewById(R.id.bt_contribute_home);

        admob = v.findViewById(R.id.admob_home);
        imageBackground = v.findViewById(R.id.image_background_home);
        dayInWeek = v.findViewById(R.id.day_in_week_home);
        recyclerDomainToday = v.findViewById(R.id.recycler_domain_today_home);
        arrayDomainToday = new ArrayList<>();
        adapterDomainToday = new DomainAdapter(requireContext(), arrayDomainToday, R.layout.item_domain);
        LinearLayoutManager managerDomainToday = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerDomainToday.setHasFixedSize(true);
        recyclerDomainToday.setLayoutManager(managerDomainToday);
        recyclerDomainToday.setAdapter(adapterDomainToday);
        recyclerDomainToday.setHasFixedSize(true);

        handler = new HomeHandler(requireActivity());
    }
}
