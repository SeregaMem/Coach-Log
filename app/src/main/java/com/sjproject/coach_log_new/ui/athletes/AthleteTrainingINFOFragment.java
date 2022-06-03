package com.sjproject.coach_log_new.ui.athletes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sjproject.coach_log_new.R;

public class AthleteTrainingINFOFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_athlete_training_info, container, false);
    }

}
