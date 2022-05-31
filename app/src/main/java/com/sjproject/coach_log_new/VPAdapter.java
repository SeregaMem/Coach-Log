package com.sjproject.coach_log_new;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sjproject.coach_log_new.ui.athletes.AthleteDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class VPAdapter extends FragmentStateAdapter {


    public VPAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return (AthleteDetailsFragment.getInstance(position));
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
