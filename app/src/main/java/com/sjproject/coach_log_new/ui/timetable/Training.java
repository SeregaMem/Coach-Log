package com.sjproject.coach_log_new.ui.timetable;

public class Training {
    private int id;
    private String name;
    private String date;
    private String time;
    private int athlete_count;

    public Training(int id, String name, String date, String time, int athlete_count) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.athlete_count = athlete_count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getAthlete_count() {
        return athlete_count;
    }
}
