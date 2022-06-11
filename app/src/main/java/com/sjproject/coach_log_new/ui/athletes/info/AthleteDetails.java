package com.sjproject.coach_log_new.ui.athletes.info;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sjproject.coach_log_new.R;

public class AthleteDetails extends AppCompatActivity {

    String[] tabName = new String[] {"Инфо", "Тренировки", "Абонемент"};

    public static String athleteID_string, athleteNAME, athletePHONE, athleteBDAY;
    public static int athleteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_details);

        savedInstanceState = getIntent().getExtras();

        athleteID_string = savedInstanceState.get("athleteID").toString();
        athleteID = Integer.parseInt(athleteID_string);
        athleteNAME = savedInstanceState.get("athleteNAME").toString();
        athletePHONE = savedInstanceState.get("athletePHONE").toString();
        athleteBDAY = savedInstanceState.get("athleteBDAY").toString();

        ViewPager2 viewPager = (ViewPager2) findViewById(R.id.athleteDetails_viewPager);
        FragmentStateAdapter pageAdapter = new VPAdapter(this);
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.athleteDetails_tabLayout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(tabName[position]);
                    }
                });
        tabLayoutMediator.attach();

    }
}