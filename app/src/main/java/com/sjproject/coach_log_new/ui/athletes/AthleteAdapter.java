package com.sjproject.coach_log_new.ui.athletes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.R;

public class AthleteAdapter extends RecyclerView.Adapter<AthleteAdapter.AthleteViewHolder> {

    String LOG_TAG = "myLOG";
    private int countItems;

    public AthleteAdapter(int athletesCount) {
        Log.d(LOG_TAG, "AthleteAdapter IN");
        countItems = athletesCount;
    }

    @NonNull
    @Override
    public AthleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOG_TAG, "onCreateViewHolder IN");
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_athlete;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        AthleteViewHolder viewHolder = new AthleteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AthleteViewHolder holder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder IN");
        // отрисовка
    }

    @Override
    public int getItemCount() {
        Log.d(LOG_TAG, "getItemCount IN");
        return 100;
    }

    class AthleteViewHolder extends RecyclerView.ViewHolder {

        TextView athleteNameView, athletebdayView;

        public AthleteViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d(LOG_TAG, "AthleteViewHolder IN");

            athleteNameView = (TextView) itemView.findViewById(R.id.tv_athleteName);
            athletebdayView = (TextView) itemView.findViewById(R.id.tv_athlete_bday);
        }
    }
}
