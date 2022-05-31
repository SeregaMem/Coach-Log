package com.sjproject.coach_log_new.ui.athletes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjproject.coach_log_new.R;


public class AthleteDetailsFragment extends Fragment {

    private static final String positionKey = "POSITION_KEY";

    public static AthleteDetailsFragment getInstance(int position) {
        AthleteDetailsFragment athleteDetailsFragment = new AthleteDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(positionKey, position);
        athleteDetailsFragment.setArguments(args);
        return  athleteDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_athlete_details, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstancesState) {
        super.onViewCreated(view, savedInstancesState);
        // Насколько я понял, здесь нужно создать все что будет в хмл файле
        TextView textView = (TextView) view.findViewById(R.id.textView123);
        textView.setText("position " + getArguments().getInt(positionKey));
    }
}