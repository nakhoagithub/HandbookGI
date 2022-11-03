package com.kanthin.handbookgenshin.adapter.character;

import android.animation.LayoutTransition;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.character.BuildCharacter;
import com.kanthin.handbookgenshin.model.index.IndexWeapon;

import java.util.ArrayList;

public class BuildCharacterAdapter extends RecyclerView.Adapter<BuildCharacterAdapter.BuildViewHolder> {

    private final Context context;
    private final ArrayList<BuildCharacter> array;
    private int lastPosition = -1;

    public BuildCharacterAdapter(Context context, ArrayList<BuildCharacter> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public BuildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_build_character, parent, false);
        return new BuildViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BuildViewHolder holder, int position) {
        BuildCharacter buildCharacter = array.get(position);
        if (buildCharacter != null){
            setAnimation(holder.itemView, position);

            holder.description.setText(buildCharacter.getDescription());
            if (buildCharacter.getSet2First() != null){
                holder.plus.setVisibility(View.VISIBLE);
                holder.layoutSet2.setVisibility(View.VISIBLE);
                getImageArtifact(buildCharacter.getSet2First(), holder.imageSet2First);
                getImageArtifact(buildCharacter.getSet2Second(), holder.imageSet2Second);
            } else {
                getImageArtifact(buildCharacter.getSet4(), holder.imageSet2First);
                holder.plus.setVisibility(View.GONE);
                holder.layoutSet2.setVisibility(View.GONE);
            }
            switch (buildCharacter.getSand()){
                case "1":
                    holder.sand.setText(context.getString(R.string.hp));
                    break;
                case "2":
                    holder.sand.setText(context.getString(R.string.def));
                    break;
                case "3":
                    holder.sand.setText(context.getString(R.string.attack));
                    break;
                case "4":
                    holder.sand.setText(context.getString(R.string.energy_recharge));
                    break;
                case "5":
                    holder.sand.setText(context.getString(R.string.elemental_mastery));
                    break;
            }
            switch (buildCharacter.getGoblet()){
                case "1":
                    holder.goblet.setText(context.getString(R.string.hp));
                    break;
                case "2":
                    holder.goblet.setText(context.getString(R.string.def));
                    break;
                case "3":
                    holder.goblet.setText(context.getString(R.string.attack));
                    break;
                case "4":
                    holder.goblet.setText(context.getString(R.string.dame_physical));
                    break;
                case "5":
                    holder.goblet.setText(context.getString(R.string.dame_element));
                    break;
                case "6":
                    holder.goblet.setText(context.getString(R.string.elemental_mastery));
                    break;
            }
            switch (buildCharacter.getCirclet()){
                case "1":
                    holder.circlet.setText(context.getString(R.string.hp));
                    break;
                case "2":
                    holder.circlet.setText(context.getString(R.string.def));
                    break;
                case "3":
                    holder.circlet.setText(context.getString(R.string.attack));
                    break;
                case "4":
                    holder.circlet.setText(context.getString(R.string.elemental_mastery));
                    break;
                case "5":
                    holder.circlet.setText(context.getString(R.string.crit_rate));
                    break;
                case "6":
                    holder.circlet.setText(context.getString(R.string.crit_dame));
                    break;
                case "7":
                    holder.circlet.setText(context.getString(R.string.healing_bonus));
                    break;
            }
            getImageWeapon(buildCharacter.getWeapon(), holder.imageWeapon, holder.layoutWeapon);
            holder.btShowDescription.setOnClickListener(v -> showInfo(holder.description, holder.btShowDescription, holder.card, holder.layout));
        }
    }

    private void showInfo(TextView view, ImageButton btShow, CardView card, LinearLayout layout) {
        if (view.getVisibility() == View.GONE) {
            btShow.setImageResource(R.drawable.ic_up);
            TransitionManager.beginDelayedTransition(card, new AutoTransition());
            layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            view.setVisibility(View.VISIBLE);
        } else {
            btShow.setImageResource(R.drawable.ic_down);
            TransitionManager.beginDelayedTransition(card, new AutoTransition());
            layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return array != null ? array.size() : 0;
    }

    static class BuildViewHolder extends RecyclerView.ViewHolder {
        private final CardView card;
        private final LinearLayout layout;
        private final RelativeLayout layoutWeapon;
        private final ImageView imageWeapon;
        private final ImageView imageSet2First;
        private final RelativeLayout layoutSet2;
        private final ImageView imageSet2Second;
        private final TextView plus;
        private final TextView sand;
        private final TextView goblet;
        private final TextView circlet;
        private final ImageButton btShowDescription;
        private final TextView description;


        public BuildViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card_build_character);
            layout = itemView.findViewById(R.id.layout_build_character);
            layoutWeapon = itemView.findViewById(R.id.layout_weapon);
            imageWeapon = itemView.findViewById(R.id.image_weapon);
            imageSet2First = itemView.findViewById(R.id.image_set2_first);
            layoutSet2 = itemView.findViewById(R.id.layout_set2_second);
            imageSet2Second = itemView.findViewById(R.id.image_set2_second);
            plus = itemView.findViewById(R.id.plus);
            sand = itemView.findViewById(R.id.mainstat_sand);
            goblet = itemView.findViewById(R.id.mainstat_goblet);
            circlet = itemView.findViewById(R.id.mainstat_circlet);
            btShowDescription = itemView.findViewById(R.id.bt_show_description);
            description = itemView.findViewById(R.id.description_build_character);

        }
    }

    private void getImageWeapon(String name, ImageView image, RelativeLayout layout){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("index").child("weapons").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                IndexWeapon weapon = snapshot.getValue(IndexWeapon.class);
                if (weapon != null){
                    Glide.with(context.getApplicationContext())
                            .load(weapon.getImages().getIcon())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_null)
                            .into(image);
                    switch (weapon.getRarity()){
                        case "3":
                            layout.setBackgroundResource(R.drawable.background_3s);
                            break;
                        case "4":
                            layout.setBackgroundResource(R.drawable.background_4s);
                            break;
                        case "5":
                            layout.setBackgroundResource(R.drawable.background_5s);
                            break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getImageArtifact(String name, ImageView image){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("index").child("artifacts").child(name).child("images").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("flower").getValue(String.class) != null){
                    Glide.with(context.getApplicationContext())
                            .load(snapshot.child("flower").getValue(String.class))
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_null)
                            .into(image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.down_to_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
