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
import com.sjproject.coach_log_new.databinding.FragmentAthletesBinding;

public class AthletesFragment extends Fragment {

    private FragmentAthletesBinding binding;

    private static DBHelper dbHelper;
    private SQLiteDatabase db;

    private AthleteAdapter athleteAdapter;

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
        dbHelper = new DBHelper(getActivity());

        db = dbHelper.getReadableDatabase();

        RecyclerView rvAthletes = binding.rvAthletesList;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvAthletes.setLayoutManager(layoutManager);

        rvAthletes.setHasFixedSize(true);

        athleteAdapter = new AthleteAdapter(getAthletesCount(), getActivity());

        rvAthletes.setAdapter(athleteAdapter);
    }

    public static int getAthletesCount () {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int count = (int) DatabaseUtils.queryNumEntries(db, "athletesTable");
        db.close();
        return count;
    }
}