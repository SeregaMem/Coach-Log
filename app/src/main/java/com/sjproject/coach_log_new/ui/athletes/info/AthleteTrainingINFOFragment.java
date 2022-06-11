package com.sjproject.coach_log_new.ui.athletes.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.DataBaseAdapter;
import com.sjproject.coach_log_new.R;
import com.sjproject.coach_log_new.databinding.FragmentAthleteTrainingInfoBinding;
import com.sjproject.coach_log_new.ui.athletes.Subscription;
import com.sjproject.coach_log_new.ui.timetable.Training;
import com.sjproject.coach_log_new.ui.timetable.TrainingAdapter;
import com.sjproject.coach_log_new.ui.timetable.Training_athlete;

import java.util.ArrayList;
import java.util.List;

public class AthleteTrainingINFOFragment extends Fragment {

    FragmentAthleteTrainingInfoBinding binding;
    RecyclerView rv_training_athlete;

    DataBaseAdapter dataBaseAdapter;
    List<Training_athlete> training_athleteList = new ArrayList<>();

    TrainingAdapter trainingAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAthleteTrainingInfoBinding.inflate(inflater, container, false);

        rv_training_athlete = binding.rvTrainingAthlete;

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        takeDataBaseRV();
    }

    private void takeDataBaseRV() {
        dataBaseAdapter = new DataBaseAdapter(getActivity());
        training_athleteList = dataBaseAdapter.getAllTrainingAthlete();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_training_athlete.setLayoutManager(layoutManager);

        int id_check = AthleteDetails.athleteID;

        List<Training> trainingList = new ArrayList<>();
        trainingList = dataBaseAdapter.getAllTraining();

        List<Training> sortedTrainingList = new ArrayList<>();


        int i = 0;

        while (training_athleteList.size() > i) {
            Training_athlete training_athlete = training_athleteList.get(i);
            if (training_athlete.getAthlete_id() == id_check) {
                int j = 0;
                while (trainingList.size() > j) {
                    Training training = trainingList.get(j);
                    if (training_athlete.getTraining_id() == training.getId()) {
                        int id = training.getId();
                        String name_training = training.getName();
                        String date_training = training.getDate();
                        String time_training = training.getTime();
                        int athleteCount = training.getAthlete_count();
                        Training new_training = new Training(id, name_training, date_training,
                                time_training, athleteCount);
                        sortedTrainingList.add(new_training);
                    }
                    j++;
                }
            }
            i++;
        }
        trainingAdapter = new TrainingAdapter(getActivity(), sortedTrainingList, rv_training_athlete);

        rv_training_athlete.setAdapter(trainingAdapter);
    }
}
