package com.sjproject.coach_log_new.ui.sport_timer;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.FragmentStatisticBinding;
import com.sjproject.coach_log_new.databinding.FragmentTimerBinding;

import org.w3c.dom.Text;

public class TimerFragment extends Fragment {

    FragmentTimerBinding binding;

    String LOG_TAG = "myLOg";

    EditText getTimer;
    TextView timer;
    Button btn_timer;
    int time, INTERVAL = 1000;
    CountDownTimer myTimer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTimerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        getTimer = binding.sendTime;
        timer = binding.tvDigitalClock;
        btn_timer = binding.btnTimer;

        myTimer = new CountDownTimer(time, INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d(LOG_TAG,"ON TICK");
                btn_timer.setText("Стоп");
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                String secondsFinal = "";
                if (seconds <= 9)
                    secondsFinal = "0" + seconds;
                else secondsFinal = "" + seconds;
                timer.setText(minutes + ":" + secondsFinal);
            }

            @Override
            public void onFinish() {
                btn_timer.setText("Старт");
            }
        };

        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "onClick");
                time = Integer.parseInt(getTimer.getText().toString());
                time = time * 1000;
                myTimer.start();
                Log.d(LOG_TAG,"start TIMER");
            }
        });

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }


}