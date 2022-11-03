package com.kanthin.handbookgenshin.adapter.domain;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.kanthin.handbookgenshin.R;
import com.kanthin.handbookgenshin.model.character.talents.ItemMaterial;
import com.kanthin.handbookgenshin.model.domain.Domain;
import com.kanthin.handbookgenshin.view.InfoDomainActivity;

import java.util.ArrayList;
import java.util.Collections;

public class DomainAdapter extends RecyclerView.Adapter<DomainAdapter.DomainTodayViewHolder> {

    private final Context context;
    private final ArrayList<Domain> array;
    private final int layout;
    private int itemShimmer;
    private boolean showShimmer = true;
    private int lastPosition = 1;

    public DomainAdapter(Context context, ArrayList<Domain> array, int layout) {
        this.context = context;
        this.array = array;
        this.layout = layout;
    }

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }

    public void setItemShimmer(int itemShimmer) {
        this.itemShimmer = itemShimmer;
    }

    @NonNull
    @Override
    public DomainTodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout, parent, false);
        return new DomainTodayViewHolder(v, array);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull DomainTodayViewHolder holder, int position) {
        if (showShimmer) {
            holder.backgroundShimmer.startShimmer();
        } else {
            setAnimation(holder.itemView, position);

            holder.backgroundShimmer.stopShimmer();
            holder.backgroundShimmer.setShimmer(null);
            holder.background.setBackground(null);
            holder.setShowShimmer(false);

            Domain domain = array.get(position);
            if (domain != null) {
                if (domain.getName() != null)
                    holder.name.setText(domain.getName());

                //tạo array và lấy dữ liệu
                ArrayList<ItemMaterial> arrayMaterialUp = new ArrayList<>(domain.getDomainLvs().get(domain.getDomainLvs().size()-1).getRewardpreview());
                //đảo ngược list
                Collections.reverse(arrayMaterialUp);
                //adapter
                MaterialInDomainAdapter adapterInDomain = new MaterialInDomainAdapter(context, arrayMaterialUp);
                //layout manager
                LinearLayoutManager layoutManagerMaterialUp = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                holder.recyclerInDomain.setLayoutManager(layoutManagerMaterialUp);
                holder.recyclerInDomain.setAdapter(adapterInDomain);
                adapterInDomain.notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getItemCount() {
        if (showShimmer) {
            return itemShimmer;
        } else {
            return array != null ? array.size() : 0;
        }
    }

    static class DomainTodayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ShimmerFrameLayout backgroundShimmer;
        private final TextView name;
        private final RecyclerView recyclerInDomain;
        private final LinearLayout background;

        private final ArrayList<Domain> array;
        private boolean showShimmer = true;
        private final Context context;

        public void setShowShimmer(boolean showShimmer) {
            this.showShimmer = showShimmer;
        }

        public DomainTodayViewHolder(@NonNull View itemView, ArrayList<Domain> array) {
            super(itemView);
            this.array = array;
            this.context = itemView.getContext();
            backgroundShimmer = itemView.findViewById(R.id.shimmer_item_domain_today);
            name = itemView.findViewById(R.id.name_item_domain_today);
            recyclerInDomain = itemView.findViewById(R.id.recycler_in_item_domain_today);
            background = itemView.findViewById(R.id.background_item_domain_today);

            itemView.setOnClickListener(this);
            recyclerInDomain.addOnItemTouchListener(touchListener);
        }

        RecyclerView.OnItemTouchListener touchListener = new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, MotionEvent e) {
                int action = e.getAction();
                if (action == MotionEvent.ACTION_MOVE) {
                    rv.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        };

        @Override
        public void onClick(View view) {
            if (!showShimmer) {
                if (array != null) {
                    Intent i = new Intent(context, InfoDomainActivity.class);
                    i.putExtra("name", array.get(getAdapterPosition()).getName());
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
