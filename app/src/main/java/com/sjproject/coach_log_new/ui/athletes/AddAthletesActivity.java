package com.sjproject.coach_log_new.ui.athletes;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.sjproject.coach_log_new.DBHelper;
import com.sjproject.coach_log_new.R;

import java.util.Calendar;

public class AddAthletesActivity extends AppCompatActivity implements View.OnClickListener {

    private Calendar calendar = Calendar.getInstance();
    private EditText etName, etPhoneNumber, etBDate;
    private Button btnCreateNewAthlete;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_athletes);

        etName = (EditText) findViewById(R.id.etName);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);

        etBDate = (EditText) findViewById(R.id.etBDate);
        etBDate.setOnClickListener(this);

        btnCreateNewAthlete = (Button) findViewById(R.id.btnCreateNewAthlete);
        btnCreateNewAthlete.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    public void setDate(View v) {
        new DatePickerDialog(AddAthletesActivity.this, d,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };

    private void setInitialDate() {
        etBDate.setText(DateUtils.formatDateTime(this, calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etBDate:
                setInitialDate();
                setDate(v);
                break;
            case R.id.btnCreateNewAthlete:
                addAthletesInBase();
                finish();
                break;
        }
    }

    private void addAthletesInBase() {
        ContentValues cv = new ContentValues();

        String name = etName.getText().toString();
        String phone_number = etPhoneNumber.getText().toString();
        String bday = etBDate.getText().toString();


        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cv.put("athlete_name", name);
        cv.put("phoneNumber", phone_number);
        cv.put("bday", bday);

        db.insert("athletesTable", null, cv);

        dbHelper.close();
    }
}