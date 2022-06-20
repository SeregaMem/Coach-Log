package com.sjproject.coach_log_new.ui.timetable.timetable_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.Adapter.TrainingAdapter;
import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.databinding.FragmentTimeTableBinding;
import com.sjproject.coach_log_new.ui.timetable.timetable_create_training.CreateTrainingActivity;
import com.sjproject.coach_log_new.object.Training;

import java.util.ArrayList;
import java.util.List;

public class TimeTableFragment extends Fragment {

    private FragmentTimeTableBinding binding;

    private DataBaseAdapter dataBaseAdapter;
    private TrainingAdapter trainingAdapter;
    private List<Training> trainingList = new ArrayList<>();

    CalendarView calendarView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTimeTableBinding.inflate(inflater, container, false);


        binding.btnCreateTimeTable.setOnClickListener(view -> startActivity(new Intent(getActivity(),
                CreateTrainingActivity.class)));

        calendarView = binding.calendarView;


        takeDataBaseInRecycleView();

        calendarView.setDate(System.currentTimeMillis(),false,true);

        View root = binding.getRoot();

        return root;
    }

    private void takeDataBaseInRecycleView() {

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                            int month, int dayOfMonth) {
                dataBaseAdapter = new DataBaseAdapter(getActivity());
                trainingList = dataBaseAdapter.getAllTraining();

                RecyclerView rvTimeTable = binding.rvTimeTable;

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                rvTimeTable.setLayoutManager(layoutManager);

                String date;

                if (((month + 1) / 10) == 1)
                    date = dayOfMonth + "/" + (month + 1) + "/" + year;
                else date = dayOfMonth + "/0" + (month + 1) + "/" + year;
                ;

                List<Training> sortedTrainingList = new ArrayList<>();

                int i = 0;

                while (trainingList.size() > i) {
                    Training training = trainingList.get(i);
                    if (date.equals(training.getDate())) {
                        int id = training.getId();
                        String name_training = training.getName();
                        String date_training = training.getDate();
                        String time_training = training.getTime();
                        int athleteCount = training.getAthlete_count();
                        Training new_training = new Training(id, name_training, date_training,
                                time_training, athleteCount);
                        sortedTrainingList.add(new_training);
                    }
                    i++;
                }
                trainingAdapter = new TrainingAdapter(getActivity(), sortedTrainingList,
                        rvTimeTable);

                rvTimeTable.setAdapter(trainingAdapter);
                dataBaseAdapter.close();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        trainingList = null;
    }

    public void onStart() {
        super.onStart();
        takeDataBaseInRecycleView();
    }
}