package com.sjproject.coach_log_new.object;

public class Subscription {
    int id;
    String buy_date;
    String date_start;
    String date_finish;
    int training_count;
    int price;

    public Subscription(int id, String buy_date, String date_start, String date_finish,
                        int training_count, int price) {
        this.id = id;
        this.buy_date = buy_date;
        this.date_start = date_start;
        this.date_finish = date_finish;
        this.training_count = training_count;
        this.price = price;
    }

    public int get_id() {
        return id;
    }

    public String getBuy_date() {
        return buy_date;
    }

    public String getDate_start() {
        return date_start;
    }

    public String getDate_finish() {
        return date_finish;
    }

    public int getTraining_count() {
        return training_count;
    }

    public int getPrice() {
        return price;
    }
}
