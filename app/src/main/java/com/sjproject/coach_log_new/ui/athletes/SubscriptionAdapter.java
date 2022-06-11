package com.sjproject.coach_log_new.ui.athletes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.R;

import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.ViewHolder>{

    Context context;

    List<Subscription> subscriptionList;
    RecyclerView rvSub;

    final View.OnClickListener onClickListener = new SubscriptionAdapter.rOnClickListener();

    public SubscriptionAdapter(Context context, List<Subscription> subscriptionList, RecyclerView rvSub) {
        this.context = context;
        this.subscriptionList = subscriptionList;
        this.rvSub = rvSub;
    }

    @NonNull
    @Override
    public SubscriptionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_sub, parent, false);
        view.setOnClickListener(onClickListener);
        return new SubscriptionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subscription subscription = subscriptionList.get(position);
        holder.tv_buy_date_write.setText(subscription.getBuy_date());
        holder.tv_start_date.setText(subscription.getDate_start());
        holder.tv_finish_date.setText(subscription.getDate_finish());
        holder.tv_training_count_number.setText(subscription.getTraining_count() + "");
        holder.tv_price_number.setText(subscription.getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return subscriptionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_buy_date_write, tv_start_date, tv_finish_date, tv_training_count_number,
                tv_price_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_buy_date_write = itemView.findViewById(R.id.tv_buy_date_write);
            tv_start_date = itemView.findViewById(R.id.tv_start_date);
            tv_finish_date = itemView.findViewById(R.id.tv_finish_date);
            tv_training_count_number = itemView.findViewById(R.id.tv_training_count_number);
            tv_price_number = itemView.findViewById(R.id.tv_price_number);
        }
    }

    public class rOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}
