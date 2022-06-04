package com.sjproject.coach_log_new.ui.athletes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.R;

public class AthleteAdapter extends RecyclerView.Adapter<AthleteAdapter.AthleteViewHolder> {

    private int countItems;

    public AthleteAdapter(int athletesCount) {
        countItems = athletesCount;
    }

    @NonNull
    @Override
    public AthleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_athlete;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        AthleteViewHolder viewHolder = new AthleteViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AthleteViewHolder holder, int position) {
        holder.bindName(position);
        holder.bindBday(position);
    }

    @Override
    public int getItemCount() {
        return countItems;
    }

    class AthleteViewHolder extends RecyclerView.ViewHolder {

        TextView athleteNameView, athletebdayView;

        public AthleteViewHolder(@NonNull View itemView) {
            super(itemView);

            athleteNameView = (TextView) itemView.findViewById(R.id.tv_athleteName);
            athletebdayView = (TextView) itemView.findViewById(R.id.tv_athlete_bday);
        }

        void bindName(int idName) {
            athleteNameView.setText(idName);
        }

        void bindBday(int idBday) {
            athletebdayView.setText(idBday);
        }
    }
}
