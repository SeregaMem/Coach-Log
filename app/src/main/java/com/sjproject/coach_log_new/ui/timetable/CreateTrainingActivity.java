package com.sjproject.coach_log_new.ui.timetable;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.sjproject.coach_log_new.DBHelper;
import com.sjproject.coach_log_new.DataBaseAdapter;
import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.ActivityCreateTrainingBinding;
import com.sjproject.coach_log_new.ui.athletes.Athletes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateTrainingActivity extends AppCompatActivity {

    EditText etTrainingDescription;
    TextView tvAthletesCount;
    Button btnAddTimeTable, btnDate, btnTime;

    ActivityCreateTrainingBinding binding;

    Calendar dateAndTime = Calendar.getInstance();

    int athletes_count = 0;

    List<Athletes> athletesList = new ArrayList<>();
    DataBaseAdapter dataBaseAdapter;

    private int trainingID_intent, trainingCOUNT_intent;
    private static String string_trainingID_intent = null;
    private static String trainingName_intent = null;
    private static String trainingDate_intent = null;
    private static String trainingTime_intent = null;
    private static String string_trainingCOUNT_intent = null;


    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_training);

        binding = ActivityCreateTrainingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnAddTimeTable = binding.btnAddTimeTable;
        btnAddTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInDataBase(v);
                finish();
            }
        });

        btnDate = binding.btnDate;
        btnTime = binding.btnTime;

        setInitialDate();
        setInitialTime();

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

        Intent intentFragmentTimeTable = getIntent();

        if (intentFragmentTimeTable.hasExtra(Intent.EXTRA_COMPONENT_NAME)) {
            string_trainingID_intent = intentFragmentTimeTable.getStringExtra("trainingID");
            trainingName_intent = intentFragmentTimeTable.getStringExtra("trainingNAME");
            trainingDate_intent = intentFragmentTimeTable.getStringExtra("trainingDATE");
            trainingTime_intent = intentFragmentTimeTable.getStringExtra("trainingTIME");
            string_trainingCOUNT_intent = intentFragmentTimeTable.getStringExtra("trainingCOUNT");

            etTrainingDescription.setText(trainingName_intent);
            btnDate.setText(trainingDate_intent);
            btnTime.setText(trainingTime_intent);
            tvAthletesCount.setText(string_trainingCOUNT_intent);
        }

    }

    public void setDate(View v) {
        new DatePickerDialog(CreateTrainingActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void setInitialDate() {

        btnDate.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_DATE |
                        DateUtils.FORMAT_SHOW_YEAR));
    }

    private void setInitialTime() {
        btnTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME));
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
            setInitialDate();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialTime();
        }
    };

    private void addInDataBase(View view) {
        ContentValues cv = new ContentValues();

        String name_description =
                etTrainingDescription.getText().toString();
        String athletes_count_taken =
                tvAthletesCount.getText().toString();
        athletes_count =
                Integer.parseInt(athletes_count_taken);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String dateText = btnDate.getText().toString();
        String timeText = btnTime.getText().toString();

        cv.put(DBHelper.KEY_NAME, name_description);
        cv.put(DBHelper.KEY_DATE_TIMETABLE, dateText);
        cv.put(DBHelper.KEY_TIME_TIMETABLE, timeText);
        cv.put(DBHelper.KEY_ATHLETES_COUNT, athletes_count);

        db.insert(DBHelper.TABLE_NAME_TIMETABLE,
                null, cv);

        dbHelper.close();
    }

    private void addCheckBoxFromBD() {
        dataBaseAdapter = new DataBaseAdapter(this);
        athletesList = dataBaseAdapter.getAllAthlete();
        LinearLayout llathletes = binding.llCheckbox;
        int i = 0;
        while (athletesList.size() > i) {
            Athletes athletes = athletesList.get(i);
            CheckBox newCB = new CheckBox(this);
            // Сделать проверку на наличие тренировок!!!
            newCB.setText(athletes.getName());
            newCB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (newCB.isChecked()) {
                        athletes_count += 1;
                    } else athletes_count -= 1;
                    tvAthletesCount.setText(athletes_count + "");
                }
            });
            llathletes.addView(newCB);
            i++;
        }
    }
}