package com.sjproject.coach_log_new.ui.athletes.athlete_info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.databinding.ActivityAddSubBinding;

public class AddSubActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_buy_date, et_start_date, et_final_date, et_training_count, et_price;
    private Button btn_add_sub;

    private ActivityAddSubBinding binding;

    private DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddSubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        et_buy_date = binding.etBuyDate;
        et_start_date = binding.etStartDate;
        et_final_date = binding.etFinalDate;
        et_training_count = binding.etTrainingCount;
        et_price = binding.etPrice;

        btn_add_sub = binding.btnAddSub;
        btn_add_sub.setOnClickListener(this);

        dataBaseAdapter = new DataBaseAdapter(this);
    }

    @Override
    public void onClick(View v) {
        int id = AthleteDetails.athleteID;
        String buy_date = et_buy_date.getText().toString();
        String start_date = et_start_date.getText().toString();
        String final_date = et_final_date.getText().toString();
        String training_count_string = et_training_count.getText().toString();
        int training_count = Integer.parseInt(training_count_string);
        String price_string = et_price.getText().toString();
        int price = Integer.parseInt(price_string);

        dataBaseAdapter.addSubscription(id, buy_date, start_date, final_date,training_count, price);

        dataBaseAdapter.updateAthlete(id, training_count);

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        dataBaseAdapter.close();
    }
}