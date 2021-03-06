package com.sjproject.coach_log_new.ui.athletes.athlete_create_athlete;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.ActivityAddAthletesBinding;

import java.util.Calendar;

public class AddAthletesActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddAthletesBinding binding;

    private Calendar calendar = Calendar.getInstance();
    private EditText etName, etPhoneNumber, etBDate;
    private Button btnCreateNewAthlete;

    private DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddAthletesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        etName = binding.etName;
        etPhoneNumber = binding.etPhoneNumber;

        etBDate = binding.etBDate;
        etBDate.setOnClickListener(this);

        btnCreateNewAthlete = binding.btnCreateNewAthlete;
        btnCreateNewAthlete.setOnClickListener(this);

        dataBaseAdapter = new DataBaseAdapter(this);
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
            case (R.id.etBDate):
                setInitialDate();
                setDate(v);
                break;
            case (R.id.btnCreateNewAthlete):
                String name = etName.getText().toString();
                String phone = etPhoneNumber.getText().toString();
                String bday = etBDate.getText().toString();

                dataBaseAdapter.addAthlete(name, phone, bday);

                finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseAdapter.close();
        binding = null;
    }
}