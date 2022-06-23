package com.sjproject.coach_log_new.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.ui.timetable.timetable_create_training.CreateTrainingActivity;
import com.sjproject.coach_log_new.object.Training;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    private Context context;

    private List<Training> trainingList;
    private RecyclerView rvTraining;

    final View.OnClickListener onClickListener = new mOnClickListener();


    public TrainingAdapter(Context context, List<Training> trainingList, RecyclerView rvTraining) {
        this.context = context;
        this.trainingList = trainingList;
        this.rvTraining = rvTraining;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_training, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Training training = trainingList.get(position);
            holder.tvTrainingName.setText(training.getName());
            holder.tvTrainingDate.setText(training.getDate() + " " + training.getTime());
            holder.tvTrainingAthleteCount.setText("Спортсменов: " + training.getAthlete_count());
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
            /*int itemPosition = rvTraining.getChildLayoutPosition(v);
            Training training = trainingList.get(itemPosition);
            Intent intent =  new Intent(context, CreateTrainingActivity.class);

            intent.putExtra(Intent.EXTRA_COMPONENT_NAME, true);
            intent.putExtra("trainingID", training.getId() + "");
            intent.putExtra("trainingNAME", training.getName());
            intent.putExtra("trainingDATE", training.getDate());
            intent.putExtra("trainingTIME", training.getTime());
            intent.putExtra("trainingCOUNT", training.getAthlete_count() + "");

            v.getContext().startActivity(intent);*/
        }
    }
}
