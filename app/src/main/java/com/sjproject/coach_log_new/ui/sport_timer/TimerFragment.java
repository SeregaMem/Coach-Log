package com.sjproject.coach_log_new.ui.sport_timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sjproject.coach_log_new.databinding.FragmentTimerBinding;


public class TimerFragment extends Fragment {

    private FragmentTimerBinding binding;

    private Button btn_timer;

    private TextView tv_rounds, tv_timer_info;

    private EditText getWorkTime, getChillTime, getRounds;
    private long timeWork, timeChill, rounds;
    private Chronometer chronometerCountDown;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTimerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        getWorkTime = binding.workTime;
        getChillTime = binding.chillTime;
        getRounds = binding.rounds;

        tv_rounds = binding.tvRoundsView;
        tv_timer_info = binding.timerInfo;

        chronometerCountDown = binding.chronometerCountDown;
        chronometerCountDown.setFormat("%s");

        btn_timer = binding.btnTimer;

        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_btn();
            }

        });

        chronometerCountDown.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                tv_rounds.setText(rounds + "");
                if (timeWork > -1) {
                    tv_timer_info.setText("Тренируемся!");
                } else if (timeChill > -1) {
                    tv_timer_info.setText("Отдыхаем!");
                }
                onChronometerTickHandler();
            }
        });

        return root;
    }

    private void onChronometerTickHandler() {
        if (rounds == 0) doStop();
        else {
            if (timeWork != -1) {
                chronometerCountDown.setText(timeWork + "");
                timeWork--;
            } else if (timeChill != -1) {
                chronometerCountDown.setText(timeChill + "");
                timeChill--;
            }
            if ((timeWork == -1) && (timeChill == -1)) {
                rounds = rounds - 1;
                timeWork = Long.parseLong(getWorkTime.getText().toString());
                timeChill = Long.parseLong(getChillTime.getText().toString());
            }
        }
    }

    private void doStart() {
        chronometerCountDown.start();
        btn_timer.setText("Стоп");
        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStop();
            }
        });
    }

    private void doStop() {
        chronometerCountDown.stop();
        btn_timer.setText("Старт");
        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_btn();
            }
        });
        tv_timer_info.setText("");
        tv_rounds.setText("");
        chronometerCountDown.setText("0:00");
    }

    private void start_btn() {
        rounds = Long.parseLong(getRounds.getText().toString());
        timeWork = Long.parseLong(getWorkTime.getText().toString());
        timeChill = Long.parseLong(getChillTime.getText().toString());
        doStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}