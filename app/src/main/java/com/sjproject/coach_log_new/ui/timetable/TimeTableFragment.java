package com.sjproject.coach_log_new.ui.timetable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sjproject.coach_log_new.DBHelper;
import com.sjproject.coach_log_new.databinding.FragmentTimeTableBinding;

public class TimeTableFragment extends Fragment {

    private FragmentTimeTableBinding binding;

    DBHelper dbHelper;
    SQLiteDatabase db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TimeTableViewModel timeTableViewModel =
                new ViewModelProvider(this).get(TimeTableViewModel.class);

        binding = FragmentTimeTableBinding.inflate(inflater, container, false);

        binding.btnCreateTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TrainingAdapterActivity.class));
            }
        });

        takeDataBase();

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
        takeDataBase();
    }

    private void takeDataBase() {

        CalendarView v = binding.calendarView;
        v.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                            int month, int dayOfMonth) {
                dbHelper = new DBHelper(getActivity());

                db = dbHelper.getReadableDatabase();
                LinearLayout llTimeTable = binding.trainingLl;
                llTimeTable.removeAllViews();

                Cursor c = db.query("trainingTable", null, null, null,
                        null, null, null);

                if (c.moveToFirst()) {
                    int idColIndex = c.getColumnIndex("id");
                    int descriptionColIndex = c.getColumnIndex("training_name");
                    int dateColIndex = c.getColumnIndex("data");
                    int timeColIndex = c.getColumnIndex("time");

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);


                    do {
                        String cdate = (month + 1) + "/" + dayOfMonth + "/" + year;

                        String dateStr = c.getString(dateColIndex);


                        if (dateStr.equals(cdate)) {
                            Button newbtn = new Button(getActivity());
                            newbtn.setBackgroundColor(0xFFFFFF);
                            newbtn.setText(c.getString(descriptionColIndex) + " " + c.getString(dateColIndex) +
                                    " " + c.getString(timeColIndex));
                            newbtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(getActivity(),
                                            TrainingAdapterActivity.class));
                                    //передавать значения в edit text
                                }
                            });
                            llTimeTable.addView(newbtn);
                        }
                    } while (c.moveToNext());
                }

                c.close();

                dbHelper.close();
            }
        });
    }
}