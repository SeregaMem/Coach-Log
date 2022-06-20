package com.sjproject.coach_log_new.object;

public class Athletes {

    private int id;
    private String name;
    private String phone;
    private String bday;
    private int training_count;

    public Athletes(int id, String name, String phone, String bday, int training_count) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.bday = bday;
        this.training_count = training_count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getBday() {
        return bday;
    }

    public int getTraining_count() {
        return training_count;
    }
}
