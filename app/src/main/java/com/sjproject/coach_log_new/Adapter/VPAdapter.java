package com.sjproject.coach_log_new.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sjproject.coach_log_new.ui.athletes.athlete_info.AthleteINFOFragment;
import com.sjproject.coach_log_new.ui.athletes.athlete_info.AthleteSubscriptionINFO;
import com.sjproject.coach_log_new.ui.athletes.athlete_info.AthleteTrainingINFOFragment;

public class VPAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;

    public VPAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AthleteINFOFragment();
            case 1:
                return new AthleteTrainingINFOFragment();
            case 2:
                return new AthleteSubscriptionINFO();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
