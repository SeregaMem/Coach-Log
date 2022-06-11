package com.sjproject.coach_log_new.ui.athletes.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sjproject.coach_log_new.DBHelper;
import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.ActivityAddSubBinding;

public class AddSubActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_buy_date, et_start_date, et_final_date, et_training_count, et_price;
    Button btn_add_sub;

    ActivityAddSubBinding binding;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddSubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelper(this);

        et_buy_date = binding.etBuyDate;
        et_start_date = binding.etStartDate;
        et_final_date = binding.etFinalDate;
        et_training_count = binding.etTrainingCount;
        et_price = binding.etPrice;

        btn_add_sub = binding.btnAddSub;
        btn_add_sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();

        int id = AthleteDetails.athleteID;
        String buy_date = et_buy_date.getText().toString();
        String start_date = et_start_date.getText().toString();
        String final_date = et_final_date.getText().toString();
        String training_count_string = et_training_count.getText().toString();
        int training_count = Integer.parseInt(training_count_string);
        String price_string = et_price.getText().toString();
        int price = Integer.parseInt(price_string);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cv.put(DBHelper.KEY_ROWID, id);
        cv.put(DBHelper.KEY_BUY_DATE, buy_date);
        cv.put(DBHelper.KEY_DATE_START, start_date);
        cv.put(DBHelper.KEY_DATE_FINISH, final_date);
        cv.put(DBHelper.KEY_TRAINING_COUNT, training_count);
        cv.put(DBHelper.KEY_PRICE, price);

        db.insert(DBHelper.TABLE_NAME_SUBSCRIPTION, null, cv);

        dbHelper.close();
        finish();
    }
}