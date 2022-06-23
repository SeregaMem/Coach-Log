package com.sjproject.coach_log_new.ui.timetable.timetable_create_training;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.Adapter.AthleteCBAdapter;
import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.ActivityCreateTrainingBinding;
import com.sjproject.coach_log_new.object.Athletes;
import com.sjproject.coach_log_new.object.Training_athlete;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateTrainingActivity extends AppCompatActivity {

    private EditText etTrainingDescription;
    private TextView tvAthletesCount;
    private Button btnAddTimeTable, btnDate, btnTime;

    private ActivityCreateTrainingBinding binding;

    private final Calendar dateAndTime = Calendar.getInstance();

    private List<Athletes> athletesList = new ArrayList<>();
    private List<Training_athlete> training_athleteList = new ArrayList<>();

    private DataBaseAdapter dataBaseAdapter;
    private AthleteCBAdapter athleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_training);

        binding = ActivityCreateTrainingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataBaseAdapter = new DataBaseAdapter(this);

        btnAddTimeTable = binding.btnAddTimeTable;
        btnAddTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTraining = etTrainingDescription.getText().toString();
                String dateTraining = btnDate.getText().toString();
                String timeTraining = btnTime.getText().toString();
                training_athleteList = athleteAdapter.getTraining_athleteList();

                int i = 0;
                boolean check = true;
                while (training_athleteList.size() > i) {
                    Training_athlete training_athlete = training_athleteList.get(i);
                    if (training_athlete.getTraining_id() == -1) {
                        check = false;
                    }
                    i++;
                }

                if (check) dataBaseAdapter.addTraining(nameTraining, dateTraining, timeTraining,
                        training_athleteList);
                else Toast.makeText(v.getContext(), "Недостаточно оплаченных занятий",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        btnDate = binding.btnDate;
        btnTime = binding.btnTime;

        btnDate.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_DATE |
                        DateUtils.FORMAT_SHOW_YEAR));

        btnTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME));


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v);
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(v);
            }
        });

        etTrainingDescription = binding.etTrainingDescription;
        tvAthletesCount = binding.tvAthletesCount;
        tvAthletesCount.setText("0");

        addCheckBoxFromBD();
    }

    public void setDate(View v) {
        new DatePickerDialog(CreateTrainingActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void setTime(View v) {
        new TimePickerDialog(CreateTrainingActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String day = "" + dayOfMonth;
            String month = "" + (monthOfYear + 1);

            if (monthOfYear < 10) month = "0" + (monthOfYear + 1);
            if (dayOfMonth < 10) day = "0" + dayOfMonth;

            String date = day + "/" + month + "/" + year;

            btnDate.setText(date);
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);

            String hour = hourOfDay + "";
            String min = minute + "";

            if (hourOfDay < 10)  hour = "0" + hourOfDay;
            if (minute < 10) min = "0" + minute;

            btnTime.setText(hour + ":" + min);
        }
    };

    private void addCheckBoxFromBD() {
        athletesList = dataBaseAdapter.getAllAthlete();

        RecyclerView recyclerView = binding.rvCreateTraining;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        athleteAdapter = new AthleteCBAdapter(this, athletesList, recyclerView);
        recyclerView.setAdapter(athleteAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        athletesList = null;
        training_athleteList = null;
        dataBaseAdapter.close();
    }
}