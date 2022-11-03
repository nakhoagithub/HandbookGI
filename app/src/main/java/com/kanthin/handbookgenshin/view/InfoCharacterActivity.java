package com.kanthin.handbookgenshin.view;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.adapter.character.ConstellationAdapter;
import com.kanthin.handbookgenshin.adapter.character.CostsAscendCharacterAdapter;
import com.kanthin.handbookgenshin.adapter.character.CostsTalentAdapter;
import com.kanthin.handbookgenshin.adapter.character.IndexCharacterAdapter;
import com.kanthin.handbookgenshin.model.ItemCostsAscend;
import com.kanthin.handbookgenshin.model.character.Character;
import com.kanthin.handbookgenshin.model.character.Constellation;
import com.kanthin.handbookgenshin.model.character.StatsCharacter;
import com.kanthin.handbookgenshin.model.character.talents.ItemCostsTalent;
import com.kanthin.handbookgenshin.presenter.InfoCharacterHandler;
import com.kanthin.handbookgenshin.presenter.anInterface.InfoCharacterInterface;

import java.util.ArrayList;

public class InfoCharacterActivity extends AppCompatActivity implements InfoCharacterInterface {

    AdView adView;

    ImageButton btBack, btShowInfo, btBuildCharacter, btAttributeCombat1, btAttributeCombat2, btAttributeCombat3, btAttributeCombatSp;
    CardView cardInfo;
    LinearLayout layoutInfoCharacter, layoutMoreInfo;
    ImageView imageItemCharacter, imageRarity, imageWeapon, imageVision, imageGender;
    RelativeLayout layoutBackgroundItem;
    TextView nameItem, nameCharacter, subStatCharacter, birthdayCharacter, constellationCharacter,
            regionCharacter, affiliationCharacter, descriptionCharacter;
    //combat
    ImageView imageCombat1, imageCombat2, imageCombat3, imageCombatSp, imagePassive1, imagePassive2, imagePassive3;
    TextView nameCombat1, nameCombat2, nameCombat3, nameCombatSp, namePassive1, namePassive2, namePassive3,
            infoCombat1, infoCombat2, infoCombat3, infoCombatSp, infoPassive1, infoPassive2, infoPassive3,
            descCombat2, descCombat3, descCombatSp;
    View viewCombatSp;

    RecyclerView recyclerCosts, recyclerIndex, recyclerConstellation, recyclerCostsAscend;
    //costs talent
    ArrayList<ItemCostsTalent> arrayCostsTalent;
    CostsTalentAdapter adapterCostsTalents;
    //constellation
    ArrayList<Constellation> arrayConstellation;
    ConstellationAdapter adapterConstellation;
    //costs ascend
    ArrayList<ItemCostsAscend> arrayCostsAscend;
    CostsAscendCharacterAdapter adapterCostsAscend;
    //index character
    ArrayList<StatsCharacter> arrayIndexCharacter;
    IndexCharacterAdapter adapterIndexCharacter;

    InfoCharacterHandler handler;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.zoom_out, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_character);
        Init();
        //get data intent
        Intent i = getIntent();
        Character character = (Character) i.getSerializableExtra("character");
        view(character);
        btBack.setOnClickListener(v -> onBackPressed());
        btShowInfo.setOnClickListener(v -> showInfo());
        btBuildCharacter.setOnClickListener(v -> {
            Intent i1 = new Intent(this, BuildCharacterActivity.class);
            i1.putExtra("name", character.getName());
            startActivity(i1);
            overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
        });
        btAttributeCombat1.setOnClickListener(v -> handler.loadAttributeCombat1(character));
        btAttributeCombat2.setOnClickListener(v -> handler.loadAttributeCombat2(character));
        btAttributeCombat3.setOnClickListener(v -> handler.loadAttributeCombat3(character));
        btAttributeCombatSp.setOnClickListener(v -> handler.loadAttributeCombatSp(character));

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    private void view(Character character) {
        adView = findViewById(R.id.ads_character);

        handler.loadImage(character, imageItemCharacter);
        handler.loadInfo(character, nameItem, nameCharacter, imageRarity, imageWeapon, imageVision, layoutBackgroundItem);
        handler.loadInfoMore(character, subStatCharacter, imageGender, birthdayCharacter,
                constellationCharacter, regionCharacter, affiliationCharacter, descriptionCharacter);
        //load combat
        handler.loadCombat1(character, imageCombat1, nameCombat1, infoCombat1);
        handler.loadCombat2(character, imageCombat2, nameCombat2, infoCombat2, descCombat2);
        handler.loadCombat3(character, imageCombat3, nameCombat3, infoCombat3, descCombat3);
        handler.loadCombatSp(character, viewCombatSp, imageCombatSp, nameCombatSp, infoCombatSp, descCombatSp);
        handler.loadPassive1(character, imagePassive1, namePassive1, infoPassive1);
        handler.loadPassive2(character, imagePassive2, namePassive2, infoPassive2);
        handler.loadPassive3(character, imagePassive3, namePassive3, infoPassive3);

        //view costs talents
        handler.loadCostsTalent(character, arrayCostsTalent, adapterCostsTalents);
        //view constellation
        handler.loadConstellation(character, arrayConstellation, adapterConstellation);
        //view
        handler.loadCostsAscendCharacter(character, arrayCostsAscend, adapterCostsAscend);
        // view index character
        handler.loadIndexCharacter(character, arrayIndexCharacter, adapterIndexCharacter);
    }

    @Override
    public void success() {

    }

    @Override
    public void fail(String error) {

    }

    private void Init() {
        btBack = findViewById(R.id.bt_back_info_character);
        btShowInfo = findViewById(R.id.bt_show_more_info_character);
        btBuildCharacter = findViewById(R.id.bt_build_info_character);
        //layout thay đổi animation
        cardInfo = findViewById(R.id.card_info_character);
        layoutInfoCharacter = findViewById(R.id.layout_info_character);
        layoutMoreInfo = findViewById(R.id.layout_more_info_character);

        ShimmerFrameLayout shimmer = findViewById(R.id.shimmer_item_character);
        shimmer.setShimmer(null);
        shimmer.stopShimmer();
        imageItemCharacter = findViewById(R.id.image_item_character);
        imageRarity = findViewById(R.id.image_rarity_info_character);
        imageWeapon = findViewById(R.id.image_weapon_info_character);
        imageVision = findViewById(R.id.image_vision_info_character);
        imageGender = findViewById(R.id.image_gender_info_character);
        layoutBackgroundItem = findViewById(R.id.background_item_character);
        nameItem = findViewById(R.id.name_item_character);
        nameCharacter = findViewById(R.id.name_info_character);
        subStatCharacter = findViewById(R.id.sub_stats_info_character);
        birthdayCharacter = findViewById(R.id.birthday_info_character);
        constellationCharacter = findViewById(R.id.constellation_info_character);
        regionCharacter = findViewById(R.id.region_info_character);
        affiliationCharacter = findViewById(R.id.affiliation_info_character);
        descriptionCharacter = findViewById(R.id.description_info_character);

        //combat1
        imageCombat1 = findViewById(R.id.image_compat1);
        btAttributeCombat1 = findViewById(R.id.bt_attribute_compat1);
        nameCombat1 = findViewById(R.id.name_combat1);
        infoCombat1 = findViewById(R.id.info_combat1);
        //combat2
        imageCombat2 = findViewById(R.id.image_compat2);
        btAttributeCombat2 = findViewById(R.id.bt_attribute_compat2);
        nameCombat2 = findViewById(R.id.name_combat2);
        infoCombat2 = findViewById(R.id.info_combat2);
        descCombat2 = findViewById(R.id.desc_combat2);
        //combat3
        imageCombat3 = findViewById(R.id.image_compat3);
        btAttributeCombat3 = findViewById(R.id.bt_attribute_compat3);
        nameCombat3 = findViewById(R.id.name_combat3);
        infoCombat3 = findViewById(R.id.info_combat3);
        descCombat3 = findViewById(R.id.desc_combat3);
        //combat3
        viewCombatSp = findViewById(R.id.view_compat_sp);
        imageCombatSp = findViewById(R.id.image_compat_sp);
        btAttributeCombatSp = findViewById(R.id.bt_attribute_compat_sp);
        nameCombatSp = findViewById(R.id.name_combat_sp);
        infoCombatSp = findViewById(R.id.info_combat_sp);
        descCombatSp = findViewById(R.id.desc_combat_sp);
        //recycler costs talents
        recyclerCosts = findViewById(R.id.recycler_costs_talent);
        recyclerCosts.setNestedScrollingEnabled(false);
        arrayCostsTalent = new ArrayList<>();
        adapterCostsTalents = new CostsTalentAdapter(this, arrayCostsTalent);
        LinearLayoutManager layoutManagerCostsTalent = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerCosts.setLayoutManager(layoutManagerCostsTalent);
        recyclerCosts.setAdapter(adapterCostsTalents);
        //passive1
        imagePassive1 = findViewById(R.id.image_passive1);
        namePassive1 = findViewById(R.id.name_passive1);
        infoPassive1 = findViewById(R.id.info_passive1);
        //passive2
        imagePassive2 = findViewById(R.id.image_passive2);
        namePassive2 = findViewById(R.id.name_passive2);
        infoPassive2 = findViewById(R.id.info_passive2);
        //passive3
        imagePassive3 = findViewById(R.id.image_passive3);
        namePassive3 = findViewById(R.id.name_passive3);
        infoPassive3 = findViewById(R.id.info_passive3);
        //constellation
        recyclerConstellation = findViewById(R.id.recycler_constellation);
        recyclerConstellation.setNestedScrollingEnabled(false);
        arrayConstellation = new ArrayList<>();
        adapterConstellation = new ConstellationAdapter(this, arrayConstellation);
        LinearLayoutManager managerConstellation = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerConstellation.setLayoutManager(managerConstellation);
        recyclerConstellation.setAdapter(adapterConstellation);
        //costs ascend
        recyclerCostsAscend = findViewById(R.id.recycler_costs_ascend_character);
        recyclerCostsAscend.setNestedScrollingEnabled(false);
        arrayCostsAscend = new ArrayList<>();
        adapterCostsAscend = new CostsAscendCharacterAdapter(this, arrayCostsAscend);
        LinearLayoutManager managerCostsAscend = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerCostsAscend.setLayoutManager(managerCostsAscend);
        recyclerCostsAscend.setAdapter(adapterCostsAscend);
        //index character
        recyclerIndex = findViewById(R.id.recycler_index_character);
        recyclerIndex.setNestedScrollingEnabled(false);
        arrayIndexCharacter = new ArrayList<>();
        adapterIndexCharacter = new IndexCharacterAdapter(this, arrayIndexCharacter);
        LinearLayoutManager managerIndexCharacter = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerIndex.setLayoutManager(managerIndexCharacter);
        recyclerIndex.setAdapter(adapterIndexCharacter);

        handler = new InfoCharacterHandler(this, this);
    }

    private void showInfo() {
        if (layoutMoreInfo.getVisibility() == View.GONE) {
            btShowInfo.setImageResource(R.drawable.ic_up);
            TransitionManager.beginDelayedTransition(cardInfo, new AutoTransition());
            layoutInfoCharacter.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            layoutMoreInfo.setVisibility(View.VISIBLE);
        } else {
            btShowInfo.setImageResource(R.drawable.ic_down);
            TransitionManager.beginDelayedTransition(cardInfo, new AutoTransition());
            layoutInfoCharacter.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            layoutMoreInfo.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}