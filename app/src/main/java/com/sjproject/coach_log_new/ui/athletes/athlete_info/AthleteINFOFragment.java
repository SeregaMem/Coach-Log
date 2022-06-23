package com.sjproject.coach_log_new.ui.athletes.athlete_info;

import static com.sjproject.coach_log_new.ui.athletes.athlete_info.AthleteDetails.athleteID;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.MainActivity;
import com.sjproject.coach_log_new.databinding.FragmentAthleteInfoBinding;


public class AthleteINFOFragment extends Fragment {

    private FragmentAthleteInfoBinding binding;

    private TextView athleteInfoNAME, athleteInfo_PHONE, athleteInfoBDAY;
    private Button btnDeleteAthlete;

    private DataBaseAdapter dataBaseAdapter;

    final int DIALOG_EXIT = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAthleteInfoBinding.inflate(inflater, container, false);

        athleteInfoNAME = binding.tvAthleteInfoName;
        athleteInfoNAME.setText(AthleteDetails.athleteNAME);

        athleteInfo_PHONE = binding.tvAthleteInfoPhone;
        athleteInfo_PHONE.setText(AthleteDetails.athletePHONE);

        athleteInfoBDAY = binding.tvAthleteInfoBday;
        athleteInfoBDAY.setText(AthleteDetails.athleteBDAY);

        dataBaseAdapter = new DataBaseAdapter(getActivity());

        btnDeleteAthlete = binding.btnDeleteAthlete;
        btnDeleteAthlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog();
            }
        });

        View root = binding.getRoot();

        return root;
    }

    protected void onCreateDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Внимание!");
        adb.setMessage("Вы уверены, что хотите удалить запись о спортсмене?");
        adb.setNegativeButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataBaseAdapter.deleteAthlete(athleteID);
                Intent intent = new Intent(getActivity(), AthleteDetails.class);
                startActivity(intent);
            }
        });
        adb.setPositiveButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = adb.create();
        dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        dataBaseAdapter.close();
    }
}