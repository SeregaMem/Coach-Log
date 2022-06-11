package com.sjproject.coach_log_new.ui.timetable;

public class Training_athlete {
    private int athlete_id;
    private int training_id;

    public Training_athlete(int athlete_id, int training_id) {
        this.athlete_id = athlete_id;
        this.training_id = training_id;
    }

    public int getAthlete_id() {
        return athlete_id;
    }

    public int getTraining_id() {
        return training_id;
    }
}
