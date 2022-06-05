package com.sjproject.coach_log_new.ui.athletes;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.DBHelper;
import com.sjproject.coach_log_new.DataBaseAdapter;
import com.sjproject.coach_log_new.databinding.FragmentAthletesBinding;

import java.util.ArrayList;
import java.util.List;

public class AthletesFragment extends Fragment {

    private FragmentAthletesBinding binding;

    DataBaseAdapter dataBaseAdapter;

    AthleteAdapter athleteAdapter;
    List<Athletes> athletesList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AthletesViewModel athletesViewModel =
                new ViewModelProvider(this).get(AthletesViewModel.class);

        binding = FragmentAthletesBinding.inflate(inflater, container, false);


        binding.btnAddAthletes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddAthletesActivity.class));
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

    public void onStart () {
        super.onStart();
        takeDataBase();
    }

    private void takeDataBase () {
        dataBaseAdapter = new DataBaseAdapter(getActivity());
        athletesList = dataBaseAdapter.getAllAthlete();

        RecyclerView rvAthletes = binding.rvAthletesList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvAthletes.setLayoutManager(layoutManager);

        rvAthletes.setHasFixedSize(true);

        athleteAdapter = new AthleteAdapter(getActivity(), athletesList, rvAthletes);
        rvAthletes.setAdapter(athleteAdapter);
    }

}