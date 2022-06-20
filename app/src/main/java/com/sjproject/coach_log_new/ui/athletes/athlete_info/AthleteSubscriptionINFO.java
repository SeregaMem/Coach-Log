package com.sjproject.coach_log_new.ui.athletes.athlete_info;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.DataBase.DataBaseAdapter;
import com.sjproject.coach_log_new.databinding.FragmentAthleteSubscriptionInfoBinding;
import com.sjproject.coach_log_new.object.Subscription;
import com.sjproject.coach_log_new.Adapter.SubscriptionAdapter;

import java.util.ArrayList;
import java.util.List;

public class AthleteSubscriptionINFO extends Fragment {

    private FragmentAthleteSubscriptionInfoBinding binding;
    private Button btn_add_sub_fragment;

    private RecyclerView rv_sub;

    private DataBaseAdapter dataBaseAdapter;
    private SubscriptionAdapter subscriptionAdapter;
    private List<Subscription> subscriptionList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAthleteSubscriptionInfoBinding.inflate(inflater, container,
                false);
        dataBaseAdapter = new DataBaseAdapter(getActivity());
        btn_add_sub_fragment = binding.btnAddSub;
        btn_add_sub_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddSubActivity.class));
            }
        });

        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        takeDataBaseRV();
    }

    private void takeDataBaseRV(){
        dataBaseAdapter = new DataBaseAdapter(getActivity());

        subscriptionList = dataBaseAdapter.getAllSubscription();

        rv_sub = binding.rvSub;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_sub.setLayoutManager(layoutManager);

        int id_check = AthleteDetails.athleteID;

        List<Subscription> sortedSubscriptionList = new ArrayList<>();

        int i = 0;

        while (subscriptionList.size() > i) {
            Subscription subscription = subscriptionList.get(i);
            if (subscription.get_id() == id_check) {
                int id = subscription.get_id();
                String buy_date = subscription.getBuy_date();
                String start_date = subscription.getDate_start();
                String finish_date = subscription.getDate_finish();
                int trainingCount = subscription.getTraining_count();
                int price = subscription.getPrice();
                Subscription new_subscription = new Subscription(id, buy_date, start_date,
                        finish_date, trainingCount, price);
                sortedSubscriptionList.add(new_subscription);
            }
            i++;
        }
        subscriptionAdapter = new SubscriptionAdapter(getActivity(), sortedSubscriptionList,
                rv_sub);

        rv_sub.setAdapter(subscriptionAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        subscriptionList = null;
        dataBaseAdapter.close();
    }
}
