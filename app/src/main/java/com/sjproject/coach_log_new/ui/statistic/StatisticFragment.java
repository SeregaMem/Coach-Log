package com.sjproject.coach_log_new.ui.statistic;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.FragmentStatisticBinding;
import com.sjproject.coach_log_new.object.Athletes;
import com.sjproject.coach_log_new.object.Subscription;
import com.sjproject.coach_log_new.object.Training;
import com.sjproject.coach_log_new.object.Training_athlete;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StatisticFragment extends Fragment implements View.OnClickListener {

    private FragmentStatisticBinding binding;
    private Calendar calendar = Calendar.getInstance();

    SimpleDateFormat myformat_date = new SimpleDateFormat("dd/MM/yyyy");
    private List<Athletes> athletesList = new ArrayList<>();
    private List<Subscription> subscriptionList = new ArrayList<>();
    private List<Training> trainingList = new ArrayList<>();
    private List<Training_athlete> training_athleteList = new ArrayList<>();

    private DataBaseAdapter dataBaseAdapter;

    private Button btn_create_statistic, btn_start_period, btn_finish_period;
    private TextView tv_sum_number, tv_training_count, tv_max_athletes_count;

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
        tv_max_athletes_count = binding.tvMaxAthletesCountNumber;

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
        String finish_date_string = btn_finish_period.getText().toString();

        Date start_date = new Date(), finish_date = new Date();

        try {
            start_date = myformat_date.parse(start_date_string);
            finish_date = myformat_date.parse(finish_date_string);
        } catch (ParseException e) {
            Toast.makeText(getActivity(), "" + e, Toast.LENGTH_LONG).show();
        }

        athletesList = dataBaseAdapter.getAllAthlete();
        trainingList = dataBaseAdapter.getAllTraining();
        training_athleteList = dataBaseAdapter.getAllTrainingAthlete();
        subscriptionList = dataBaseAdapter.getAllSubscription();

        getPriceStatistic(start_date, finish_date);

        getTrainingCount(start_date, finish_date);
    }

    private void getPriceStatistic(Date start, Date finish) {
        int i = 0;
        int sum = 0;
        tv_sum_number.setText("" + 0);
        while (subscriptionList.size() > i) {
            Subscription  subscription = subscriptionList.get(i);
            String buy_date_sub_string = subscription.getBuy_date();

            Date buy_date_sub;

            try {
                buy_date_sub = myformat_date.parse(buy_date_sub_string);
            } catch (ParseException e) {
                Toast.makeText(getActivity(), "" + e, Toast.LENGTH_LONG).show();
                break;
            }

            if ((buy_date_sub.after(start)) && (buy_date_sub.before(finish))) {
                sum += subscription.getPrice();
            }
            i++;
        }
        tv_sum_number.setText("" + sum);
    }

    private void getTrainingCount(Date start, Date finish) {
        int i = 0;
        int training_count = 0;
        int max_count_athletes = 0;
        while (trainingList.size() > i) {
            Training training = trainingList.get(i);
            String training_date = training.getDate();

            Date  date = new Date();
            try {
                date = myformat_date.parse(training_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if ((date.after(start)) && (date.before(finish))) {
                training_count ++;
            }

            int count = training.getAthlete_count();
            if (count > max_count_athletes) {
                max_count_athletes = count;
            }
            i++;
        }
        tv_training_count.setText("" + training_count);

        tv_max_athletes_count.setText("" + max_count_athletes);
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