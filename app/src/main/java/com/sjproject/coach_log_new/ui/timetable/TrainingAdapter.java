package com.sjproject.coach_log_new.ui.timetable;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.R;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    Context context;

    List<Training> trainingList;
    RecyclerView rvTraining;
    String date;

    final View.OnClickListener onClickListener = new mOnClickListener();


    public TrainingAdapter(Context context, List<Training> trainingList, RecyclerView rvTraining,
                           String date) {
        this.context = context;
        this.trainingList = trainingList;
        this.rvTraining = rvTraining;
        this.date = date;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_training, parent, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Training training = trainingList.get(position);
        if (date.equals(training.getDate())) {
            holder.tvTrainingName.setText(training.getName());
            holder.tvTrainingDate.setText(training.getDate() + " " + training.getTime());
            holder.tvTrainingAthleteCount.setText("Спортсменов: " + training.getAthlete_count());
        }
    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTrainingName, tvTrainingDate, tvTrainingAthleteCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrainingName = (TextView) itemView.findViewById(R.id.tv_training_Name);
            tvTrainingDate = (TextView) itemView.findViewById(R.id.tv_training_Date);
            tvTrainingAthleteCount = (TextView) itemView.findViewById(R.id.tv_training_AthletesCount);
        }
    }

    private class mOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}
