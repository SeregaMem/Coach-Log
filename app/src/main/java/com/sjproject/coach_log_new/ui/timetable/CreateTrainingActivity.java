package com.sjproject.coach_log_new.ui.timetable;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
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
import com.sjproject.coach_log_new.R;

import java.util.Calendar;

public class CreateTrainingActivity extends AppCompatActivity {

    EditText etTrainingDescription;
    TextView tvAthletesCount;
    Button btnAddTimeTable, btnDate, btnTime;

    Calendar dateAndTime = Calendar.getInstance();

    int athletes_count = 0;
    DBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_training);

        btnAddTimeTable = (Button) findViewById(R.id.btn_add_time_table);
        btnAddTimeTable.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
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
                                                   finish();
                                               }
                                           });

        btnDate = (Button) findViewById(R.id.btnDate);
        btnTime = (Button) findViewById(R.id.btnTime);

        setInitialDate(); setInitialTime();

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

        etTrainingDescription = (EditText) findViewById(R.id.et_training_description);
        tvAthletesCount = (TextView) findViewById(R.id.tv_athletes_count);
        tvAthletesCount.setText("0");

        dbHelper = new DBHelper(this);

        db = dbHelper.getReadableDatabase();

        Cursor c = db.query(DBHelper.TABLE_NAME_ATHLETE, null, null, null,
                null, null, null);

        if (c.moveToFirst()) {
            int idCcolIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int phoneColIndex = c.getColumnIndex("phone");
            int bdayColIndex = c.getColumnIndex("bday");

            LinearLayout llathletes = (LinearLayout) findViewById(R.id.ll_checkbox);

            do {
                CheckBox newCheckBox = new CheckBox(this);
                newCheckBox.setText(c.getString(nameColIndex));
                newCheckBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (newCheckBox.isChecked()) {
                            athletes_count += 1;
                        } else athletes_count -= 1;
                        tvAthletesCount.setText(athletes_count + "");
                    }
                });
                llathletes.addView(newCheckBox);
            } while (c.moveToNext());
        }

        c.close();
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
}