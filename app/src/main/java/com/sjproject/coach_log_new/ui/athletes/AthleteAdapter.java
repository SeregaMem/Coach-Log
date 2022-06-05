package com.sjproject.coach_log_new.ui.athletes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.DBHelper;
import com.sjproject.coach_log_new.R;

import java.util.List;


public class AthleteAdapter extends RecyclerView.Adapter<AthleteAdapter.ViewHolder> {

    String LOG_TAG = "myLog";
    Context context;

    List<Athletes> athletesList;
    RecyclerView rvAthletes;
    final View.OnClickListener onClickListener = new myOnClickListener();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_bday;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_athleteName);
            tv_bday = (TextView) itemView.findViewById(R.id.tv_athlete_bday);
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
    public void onBindViewHolder(@NonNull AthleteAdapter.ViewHolder holder, int i) {
        Athletes athletes = athletesList.get(i);
        holder.tv_name.setText(athletes.getName());
        holder.tv_bday.setText(athletes.getBday());
    }


    @Override
    public int getItemCount() {
        return athletesList.size();
    }



    private class myOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}
