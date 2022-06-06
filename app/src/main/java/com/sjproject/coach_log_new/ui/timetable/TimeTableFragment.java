package com.sjproject.coach_log_new.ui.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.DataBaseAdapter;
import com.sjproject.coach_log_new.databinding.FragmentTimeTableBinding;

import java.util.ArrayList;
import java.util.List;

public class TimeTableFragment extends Fragment {

    private FragmentTimeTableBinding binding;

    DataBaseAdapter dataBaseAdapter;
    TrainingAdapter trainingAdapter;
    List<Training> trainingList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TimeTableViewModel timeTableViewModel =
                new ViewModelProvider(this).get(TimeTableViewModel.class);

        binding = FragmentTimeTableBinding.inflate(inflater, container, false);

        binding.btnCreateTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CreateTrainingActivity.class));
            }
        });

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onStart() {
        super.onStart();
        takeDataBaseInRecycleView();
    }

    private void takeDataBaseInRecycleView() {

        CalendarView v = binding.calendarView;
        v.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                            int month, int dayOfMonth) {
                dataBaseAdapter = new DataBaseAdapter(getActivity());
                trainingList = dataBaseAdapter.getAllTraining();

                RecyclerView rvTimeTable = binding.rvTimeTable;

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                rvTimeTable.setLayoutManager(layoutManager);

                rvTimeTable.setHasFixedSize(true);

                String date = (month + 1) + "/" + dayOfMonth + "/" + year;

                trainingAdapter = new TrainingAdapter(getActivity(), trainingList, rvTimeTable, date);

                rvTimeTable.setAdapter(trainingAdapter);
            };
        });
    }
}