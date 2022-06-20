package com.sjproject.coach_log_new.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.object.Athletes;
import com.sjproject.coach_log_new.object.Training_athlete;

import java.util.ArrayList;
import java.util.List;

public class AthleteCBAdapter extends RecyclerView.Adapter<AthleteCBAdapter.ViewHolder> {

    Context context;

    List<Athletes> athletesList;
    RecyclerView recyclerView;
    List<Training_athlete> training_athleteList = new ArrayList<>();

    final View.OnClickListener onClickListener = new cbClickListener();

    public AthleteCBAdapter(Context context, List<Athletes> athletesList, RecyclerView recyclerView) {
        this.context = context;
        this.athletesList = athletesList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_checkbox, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Athletes athletes = athletesList.get(position);
        holder.athlete_id.setText("" + athletes.getId());
        holder.tv_name.setText(athletes.getName());
        holder.tv_bday.setText(athletes.getBday());
        holder.tv_sub_training_count_cb.setText("" + athletes.getTraining_count());
    }

    @Override
    public int getItemCount() {
        return athletesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView athlete_id, tv_name, tv_bday, check_box, tv_sub_training_count_cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            athlete_id = (TextView) itemView.findViewById(R.id.ATHLETE_ID);
            tv_name = (TextView) itemView.findViewById(R.id.tv_athleteName_cb);
            tv_bday = (TextView) itemView.findViewById(R.id.tv_athlete_bday_cb);
            tv_sub_training_count_cb = (TextView) itemView.findViewById(R.id.tv_sub_training_count_cb);
            check_box = (CheckBox) itemView.findViewById(R.id.athlete_check);
        }
    }

    private class cbClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            TextView Athlete_ID = v.findViewById(R.id.ATHLETE_ID);
            TextView tv_Athlete_count = v.findViewById(R.id.tv_sub_training_count_cb);
            String Athlete_count_toString = tv_Athlete_count.getText().toString();
            int Athlete_count = Integer.parseInt(Athlete_count_toString);
            CheckBox checkBox = v.findViewById(R.id.athlete_check);
            checkBox.setChecked(!checkBox.isChecked());
            String athlteID_toString = Athlete_ID.getText().toString();
            int athleteID = Integer.parseInt(athlteID_toString);
            int i = 0;
            if (checkBox.isChecked()) {
                Training_athlete training_athlete = new Training_athlete(athleteID,
                        Athlete_count - 1);
                training_athleteList.add(training_athlete);
                tv_Athlete_count.setText("" + (Athlete_count - 1));
            } else {
                while (training_athleteList.size() > i) {
                    Training_athlete training_athlete = training_athleteList.get(i);

                    if (training_athlete.getAthlete_id() == athleteID)
                        training_athleteList.remove(i);
                    i++;
                }
                tv_Athlete_count.setText("" + (Athlete_count + 1));
            }
        }
    }

    public List<Training_athlete> getTraining_athleteList() {
        return training_athleteList;
    }
}
