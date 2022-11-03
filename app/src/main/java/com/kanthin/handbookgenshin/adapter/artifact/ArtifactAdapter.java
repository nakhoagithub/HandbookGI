package com.kanthin.handbookgenshin.adapter.artifact;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.artifact.Artifact;
import com.kanthin.handbookgenshin.view.InfoArtifactActivity;

import java.util.ArrayList;

public class ArtifactAdapter extends RecyclerView.Adapter<ArtifactAdapter.ArtifactViewHolder> {

    private final Context context;
    private final ArrayList<Artifact> array;
    private final int layout;
    private boolean showShimmer = true;
    private int lastPosition = -1;


    public ArtifactAdapter(Context context, ArrayList<Artifact> array, int layout) {
        this.context = context;
        this.array = array;
        this.layout = layout;
    }

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }

    @NonNull
    @Override
    public ArtifactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ArtifactViewHolder(v, array, context);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ArtifactViewHolder holder, int position) {
        if (showShimmer){
            holder.backgroundShimmer.startShimmer();
        } else {
            setAnimation(holder.itemView, position);
            holder.backgroundShimmer.stopShimmer();
            holder.backgroundShimmer.setShimmer(null);
            holder.name.setBackground(null);
            holder.image1.setBackground(null);
            holder.image2.setBackground(null);
            holder.image3.setBackground(null);
            holder.image4.setBackground(null);
            holder.image5.setBackground(null);
            holder.attribute1.setBackground(null);
            holder.attribute2.setBackground(null);
            holder.setShowShimmer(false);

            Artifact artifact = array.get(position);
            if (artifact != null){
                //layout
                if (artifact.getRarity() != null){
                    switch (artifact.getRarity().get(artifact.getRarity().size()-1)){
                        case "1":
                            holder.layout.setBackgroundResource(R.drawable.background_1s);
                            break;
                        case "2":
                            holder.layout.setBackgroundResource(R.drawable.background_2s);
                            break;
                        case "3":
                            holder.layout.setBackgroundResource(R.drawable.background_3s);
                            break;
                        case "4":
                            holder.layout.setBackgroundResource(R.drawable.background_4s);
                            break;
                        case "5":
                            holder.layout.setBackgroundResource(R.drawable.background_5s);
                            break;
                    }
                } else holder.layout.setBackgroundResource(R.drawable.background_1s);
                //name
                holder.name.setText(artifact.getName());
                //image
                if (artifact.getImages().getFlower() != null){
                    Glide.with(context)
                            .load(artifact.getImages().getFlower())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_null)
                            .into(holder.image1);
                    Glide.with(context)
                            .load(artifact.getImages().getPlume())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_null)
                            .into(holder.image2);
                    Glide.with(context)
                            .load(artifact.getImages().getSands())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_null)
                            .into(holder.image3);
                    Glide.with(context)
                            .load(artifact.getImages().getGoblet())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_null)
                            .into(holder.image4);
                    Glide.with(context)
                            .load(artifact.getImages().getCirclet())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_null)
                            .into(holder.image5);
                } else {
                    holder.image1.setImageDrawable(null);
                    holder.image2.setImageDrawable(null);
                    holder.image4.setImageDrawable(null);
                    holder.image5.setImageDrawable(null);
                    Glide.with(context)
                            .load(artifact.getImages().getCirclet())
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(R.drawable.ic_null)
                            .into(holder.image3);
                }
                // attribute
                if (artifact.getSet2() != null){
                    holder.attribute1.setText(context.getResources().getString(R.string.set2) + ": " + artifact.getSet2());
                    holder.attribute2.setVisibility(View.VISIBLE);
                    holder.attribute2.setText(context.getResources().getString(R.string.set4) + ": " + artifact.getSet4());
                } else {
                    holder.attribute1.setText(context.getResources().getString(R.string.set1) + ": " + artifact.getSet1());
                    holder.attribute2.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (showShimmer){
            return 3;
        } else{
            return array != null ? array.size() : 0;
        }
    }

    static class ArtifactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ArrayList<Artifact> array;
        private final Context context;
        private final ShimmerFrameLayout backgroundShimmer;
        private final LinearLayout layout;
        private final TextView name;
        private final ImageView image1;
        private final ImageView image2;
        private final ImageView image3;
        private final ImageView image4;
        private final ImageView image5;
        private final TextView attribute1;
        private final TextView attribute2;
        private boolean showShimmer = true;


        public void setShowShimmer(boolean showShimmer) {
            this.showShimmer = showShimmer;
        }

        public ArtifactViewHolder(@NonNull View itemView, ArrayList<Artifact> array, Context context) {
            super(itemView);
            this.array = array;
            this.context = context;
            backgroundShimmer = itemView.findViewById(R.id.shimmer_item_artifact);
            layout = itemView.findViewById(R.id.layout_artifact);
            name = itemView.findViewById(R.id.name_artifact);
            image1 = itemView.findViewById(R.id.image1_artifact);
            image2 = itemView.findViewById(R.id.image2_artifact);
            image3 = itemView.findViewById(R.id.image3_artifact);
            image4 = itemView.findViewById(R.id.image4_artifact);
            image5 = itemView.findViewById(R.id.image5_artifact);
            attribute1 = itemView.findViewById(R.id.attribute1_artifact);
            attribute2 = itemView.findViewById(R.id.attribute2_artifact);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (!showShimmer){
                if (array != null){
                    Intent i = new Intent(view.getContext(), InfoArtifactActivity.class);
                    i.putExtra("artifact", array.get(getAdapterPosition()));
                    context.startActivity(i);
                    ((Activity) context).overridePendingTransition(R.anim.zoom_in, R.anim.fade_out);
                }
            }
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.down_to_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
