package com.sjproject.coach_log_new.ui.athletes;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sjproject.coach_log_new.AddAthletesActivity;
import com.sjproject.coach_log_new.DBHelper;
import com.sjproject.coach_log_new.databinding.FragmentAthletesBinding;

public class AthletesFragment extends Fragment {

    private FragmentAthletesBinding binding;

    private DBHelper dbHelper;
    private SQLiteDatabase db;

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

        LinearLayout llathletes = binding.mainll;
        llathletes.removeAllViews();

        Cursor c = db.query("athletesTable", null, null, null,
                null, null, null);

        if (c.moveToFirst()) {
            int idCcolIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("athlete_name");
            int phoneColIndex = c.getColumnIndex("phoneNumber");
            int bdayColIndex = c.getColumnIndex("bday");

            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT);

            do {
                Button btnNew = new Button(getActivity());
                btnNew.setBackgroundColor(0xFFFFFF);
                btnNew.setText(c.getString(nameColIndex) + "   т." + c.getString(phoneColIndex));
                btnNew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), AthleteDetails.class));
                    }
                });
                llathletes.addView(btnNew);
            } while (c.moveToNext());
        }

        c.close();

        dbHelper.close();
    }
}