package com.sjproject.coach_log_new.ui.statistic;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.FragmentStatisticBinding;
import com.sjproject.coach_log_new.object.Athletes;
import com.sjproject.coach_log_new.object.Subscription;
import com.sjproject.coach_log_new.object.Training;
import com.sjproject.coach_log_new.object.Training_athlete;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StatisticFragment extends Fragment implements View.OnClickListener {

    private FragmentStatisticBinding binding;
    private Calendar calendar = Calendar.getInstance();

    private List<Athletes> athletesList = new ArrayList<>();
    private List<Subscription> subscriptionList = new ArrayList<>();
    private List<Training> trainingList = new ArrayList<>();
    private List<Training_athlete> training_athleteList = new ArrayList<>();

    private DataBaseAdapter dataBaseAdapter;

    private Button btn_create_statistic, btn_start_period, btn_finish_period;
    private TextView tv_sum_number, tv_training_count;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStatisticBinding.inflate(inflater, container, false);

        btn_start_period = binding.btnStartPeriod;
        btn_start_period.setOnClickListener(this);

        btn_finish_period = binding.btnFinishPeriod;
        btn_finish_period.setOnClickListener(this);

        btn_create_statistic = binding.btnCreateStatistic;
        btn_create_statistic.setOnClickListener(this);

        tv_sum_number = binding.tvSumNumber;
        tv_training_count = binding.tvTrainingCountNumber;

        dataBaseAdapter = new DataBaseAdapter(getActivity());

        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.btn_start_period):
                new DatePickerDialog(getActivity(), startPeriod,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
            case (R.id.btn_finish_period):
                new DatePickerDialog(getActivity(), finishPeriod,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
            case (R.id.btn_create_statistic):
                if (!btn_start_period.getText().equals("") ||
                        !btn_finish_period.getText().equals("")) getStatistic();
        }
    }

    DatePickerDialog.OnDateSetListener finishPeriod = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String month = (monthOfYear + 1) + "";
            String days = dayOfMonth + "";

            if (monthOfYear < 10)
                month = "0" + (monthOfYear + 1);
            if (dayOfMonth < 10)
                days = "0" + dayOfMonth;

            String date = days + "/" + month + "/" + year;

            btn_finish_period.setText(date);
        }
    };

    DatePickerDialog.OnDateSetListener startPeriod = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String month = (monthOfYear + 1) + "";
            String days = dayOfMonth + "";

            if (monthOfYear < 10)
                month = "0" + (monthOfYear + 1);
            if (dayOfMonth < 10)
                days = "0" + dayOfMonth;

            String date = days + "/" + month + "/" + year;

            btn_start_period.setText(date);
        }
    };

    private void getStatistic() {
        String start_date_string = btn_start_period.getText().toString();
        String [] start_dateParts = start_date_string.split("/");
        String start_days_string = start_dateParts[0];
        int start_days = Integer.parseInt(start_days_string);
        String start_month_string = start_dateParts[1];
        int start_month = Integer.parseInt(start_month_string);
        String start_year_string = start_dateParts[2];
        int start_year = Integer.parseInt(start_year_string);

        String finish_date_string = btn_finish_period.getText().toString();
        String [] finish_dateParts = finish_date_string.split("/");
        String finish_days_string = finish_dateParts[0];
        int finish_days = Integer.parseInt(finish_days_string);
        String finish_month_string = finish_dateParts[1];
        int finish_month = Integer.parseInt(finish_month_string);
        String finish_year_string = finish_dateParts[2];
        int finish_year = Integer.parseInt(finish_year_string);

        athletesList = dataBaseAdapter.getAllAthlete();
        trainingList = dataBaseAdapter.getAllTraining();
        training_athleteList = dataBaseAdapter.getAllTrainingAthlete();
        subscriptionList = dataBaseAdapter.getAllSubscription();

        int i = 0;
        int sum = 0;

        while (subscriptionList.size() > i) {
            Subscription  subscription = subscriptionList.get(i);
            String finish_date_sub = subscription.getDate_finish();

            String [] dateParts = finish_date_sub.split("/");
            String getDays = dateParts[0];
            int sub_days = Integer.parseInt(getDays);
            String getMonth = dateParts[1];
            int sub_month = Integer.parseInt(getMonth);

            if ((start_month <= sub_month) && (start_days <= sub_days) && (finish_month >= sub_month)) {
                sum += subscription.getPrice();
            }

            i++;
        }
        tv_sum_number.setText("" + sum);

        i = 0;
        int training_count = 0;
        while (trainingList.size() >= i) {
            Training training = trainingList.get(i);
            String training_date = training.getDate();

            String [] dateParts = training_date.split("/");
            String getTrainingDay = dateParts[0];
            int trainingDay = Integer.parseInt(getTrainingDay);
            String getTrainingMonth = dateParts[1];
            int trainingMonth = Integer.parseInt(getTrainingMonth);
            String getTrainingYear = dateParts[2];
            int trainingYear = Integer.parseInt(getTrainingYear);

            if ((trainingMonth >= start_month) && (trainingMonth <= finish_month)) {
                training_count ++;
            }
            i++;
        }
        tv_training_count.setText("" + training_count);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        athletesList = null;
        trainingList = null;
        training_athleteList = null;
        subscriptionList = null;
        dataBaseAdapter.close();
    }
}