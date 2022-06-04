package com.sjproject.coach_log_new.ui.athletes.info;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sjproject.coach_log_new.ui.athletes.info.AthleteINFOFragment;
import com.sjproject.coach_log_new.ui.athletes.info.AthleteSubscriptionINFO;
import com.sjproject.coach_log_new.ui.athletes.info.AthleteTrainingINFOFragment;

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
        //return (AthleteINFOFragment.getInstance(position));
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
