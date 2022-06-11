package com.sjproject.coach_log_new.ui.statistic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.FragmentStatisticBinding;

public class StatisticFragment extends Fragment implements View.OnClickListener {

    private FragmentStatisticBinding binding;

    Button btn_start_month, btn_finish_month, btn_start_period, btn_finish_period;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentStatisticBinding.inflate(inflater, container, false);

        btn_start_month = binding.btnStartMonth;
        btn_start_month.setOnClickListener(this);

        btn_finish_month = binding.btnFinishMonth;
        btn_finish_month.setOnClickListener(this);

        btn_start_period = binding.btnStartPeriod;
        btn_start_period.setOnClickListener(this);

        btn_finish_period = binding.btnFinishPeriod;
        btn_finish_period.setOnClickListener(this);

        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn_start_month):
                break;
            case (R.id.btn_finish_month):
                break;
            case (R.id.btn_start_period):
                break;
            case (R.id.btn_finish_period):
                break;
        }
    }
}