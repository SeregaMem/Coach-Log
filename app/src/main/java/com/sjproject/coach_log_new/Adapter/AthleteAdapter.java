package com.sjproject.coach_log_new.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.object.Athletes;
import com.sjproject.coach_log_new.object.Subscription;
import com.sjproject.coach_log_new.ui.athletes.athlete_info.AthleteDetails;

import java.util.ArrayList;
import java.util.List;


public class AthleteAdapter extends RecyclerView.Adapter<AthleteAdapter.ViewHolder> {

    private Context context;

    private List<Athletes> athletesList;
    private RecyclerView rvAthletes;

    final View.OnClickListener onClickListener = new myOnClickListener();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_bday, tv_training_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_athleteName);
            tv_bday = (TextView) itemView.findViewById(R.id.tv_athlete_bday);
            tv_training_count = (TextView) itemView.findViewById(R.id.tv_sub_training_count);
        }
    }

    public AthleteAdapter(Context context, List<Athletes> athletesList,
                          RecyclerView rvAthletes) {
        this.context = context;
        this.athletesList = athletesList;
        this.rvAthletes = rvAthletes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_athlete, parent, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AthleteAdapter.ViewHolder holder, int position) {
        Athletes athletes = athletesList.get(position);
        holder.tv_name.setText(athletes.getName());
        holder.tv_bday.setText(athletes.getBday());
        holder.tv_training_count.setText(athletes.getTraining_count() + "");
    }


    @Override
    public int getItemCount() {
        return athletesList.size();
    }


    private class myOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
                int itemPosition = rvAthletes.getChildLayoutPosition(v);
                Athletes athlete = athletesList.get(itemPosition);
                Intent intent = new Intent(context, AthleteDetails.class);

                intent.putExtra("athleteID", athlete.getId());
                intent.putExtra("athleteNAME", athlete.getName());
                intent.putExtra("athletePHONE", athlete.getPhone());
                intent.putExtra("athleteBDAY", athlete.getBday());

                v.getContext().startActivity(intent);
        }
    }
}
