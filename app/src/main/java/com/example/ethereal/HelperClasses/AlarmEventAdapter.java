package com.example.ethereal.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ethereal.AlarmDatabase.EntityClass;
import com.example.ethereal.R;

import java.util.List;

public class AlarmEventAdapter extends RecyclerView.Adapter<AlarmEventAdapter.ViewHolder> {

    Context context;
    List<EntityClass> entityClasses;

    public AlarmEventAdapter(Context context, List<EntityClass> entityClasses) {
        this.context = context;
        this.entityClasses = entityClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listings_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.eventText.setText(entityClasses.get(position).getEventname());
        holder.timeAndDateText.setText(entityClasses.get(position).getEventdate() + " " + entityClasses.get(position).getEventtime());
    }

    @Override
    public int getItemCount() {
        return entityClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView eventText, timeAndDateText;
        private LinearLayout toplayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = itemView.findViewById(R.id.event);
            timeAndDateText = (TextView) itemView.findViewById(R.id.time_and_date);
            toplayout = (LinearLayout) itemView.findViewById(R.id.toplayout);
        }
    }

}
