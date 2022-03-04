package com.example.ethereal.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.Activities.MeditationPlayerActivity;
import com.example.ethereal.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import Model.Health;

public class ActivitiesHealthAdapter extends RecyclerView.Adapter<ActivitiesHealthAdapter.ActivitiesHealthHolder> {

    ArrayList<Health> health;

    public ActivitiesHealthAdapter(ArrayList<Health> health) {
        this.health = health;
    }

    @NonNull
    @Override
    public ActivitiesHealthHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activities_health_card, parent, false);
        ActivitiesHealthAdapter.ActivitiesHealthHolder activitiesHealthHolder = new ActivitiesHealthAdapter.ActivitiesHealthHolder(view);
        return activitiesHealthHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesHealthHolder holder, int position) {

        Health health1 = health.get(position);
        holder.tv_health_title.setText(health1.getTitle());
        holder.tv_health_desc.setText(health1.getDescription());
        holder.img_health_thumbnail.setImageResource(health1.getThumbnail());
        holder.healthcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), MeditationPlayerActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return health.size();
    }
    public static class ActivitiesHealthHolder extends RecyclerView.ViewHolder{

        TextView tv_health_title, tv_health_desc;
        ImageView img_health_thumbnail;
        MaterialCardView healthcard;
        public ActivitiesHealthHolder(@NonNull View itemView) {
            super(itemView);
            tv_health_title = itemView.findViewById(R.id.healthtitle);
            tv_health_desc = itemView.findViewById(R.id.healthdesc);
            img_health_thumbnail = itemView.findViewById(R.id.healthimg);
            healthcard = itemView.findViewById(R.id.healthcard);
        }
    }
}

//    private Context mContext;
//    private List<Health> mData;
//
//    public ActivitiesHealthAdapter(Context mContext, List<Health> mData) {
//        this.mContext = mContext;
//        this.mData = mData;
//    }
//
//    @NonNull
//    @Override
//    public ActivitiesHealthAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
//        LayoutInflater mInflater = LayoutInflater.from(mContext);
//        view = mInflater.inflate(R.layout.activities_health_card, parent,false);
//        return new ActivitiesHealthAdapter.MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ActivitiesHealthAdapter.MyViewHolder holder, int position) {
//        holder.tv_health_title.setText(mData.get(position).getTitle());
//        holder.tv_health_desc.setText(mData.get(position).getDescription());
//        holder.img_health_thumbnail.setImageResource(mData.get(position).getThumbnail());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView tv_health_title, tv_health_desc;
//        ImageView img_health_thumbnail;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tv_health_title = itemView.findViewById(R.id.healthtitle);
//            tv_health_desc = itemView.findViewById(R.id.healthdesc);
//            img_health_thumbnail = itemView.findViewById(R.id.healthimg);
//        }
//    }
//}
