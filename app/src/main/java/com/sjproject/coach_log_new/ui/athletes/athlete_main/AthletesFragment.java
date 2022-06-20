package com.sjproject.coach_log_new.ui.athletes.athlete_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.Adapter.AthleteAdapter;
import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.databinding.FragmentAthletesBinding;
import com.sjproject.coach_log_new.object.Athletes;
import com.sjproject.coach_log_new.ui.athletes.athlete_create_athlete.AddAthletesActivity;

import java.util.ArrayList;
import java.util.List;

public class AthletesFragment extends Fragment {

    private FragmentAthletesBinding binding;

    private DataBaseAdapter dataBaseAdapter;

    private RecyclerView rvAthletes;
    private AthleteAdapter athleteAdapter;
    private List<Athletes> athletesList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAthletesBinding.inflate(inflater, container, false);

        rvAthletes = binding.rvAthletesList;

        binding.btnAddAthletes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddAthletesActivity.class));
            }
        });

        View root = binding.getRoot();
        return root;
    }

    public void onStart () {
        super.onStart();
        takeDataBaseInRecycleView();
    }


    private void takeDataBaseInRecycleView () {
        dataBaseAdapter = new DataBaseAdapter(getActivity());
        athletesList = dataBaseAdapter.getAllAthlete();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvAthletes.setLayoutManager(layoutManager);

        rvAthletes.setHasFixedSize(true);

        athleteAdapter = new AthleteAdapter(getActivity(), athletesList, rvAthletes);
        rvAthletes.setAdapter(athleteAdapter);
        dataBaseAdapter.close();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        athletesList = null;
    }
}