package com.sjproject.coach_log_new.ui.athletes.info;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.FragmentAthleteInfoBinding;


public class AthleteINFOFragment extends Fragment {

    private FragmentAthleteInfoBinding binding;

    TextView athleteInfoNAME, athleteInfo_PHONE, athleteInfoBDAY;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAthleteInfoBinding.inflate(inflater, container, false);

        athleteInfoNAME = binding.tvAthleteInfoName;
        athleteInfoNAME.setText(AthleteDetails.athleteNAME);

        athleteInfo_PHONE = binding.tvAthleteInfoPhone;
        athleteInfo_PHONE.setText(AthleteDetails.athletePHONE);

        athleteInfoBDAY = binding.tvAthleteInfoBday;
        athleteInfoBDAY.setText(AthleteDetails.athleteBDAY);

        View root = binding.getRoot();

        return root;
    }
}