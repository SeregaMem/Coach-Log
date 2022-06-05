package com.sjproject.coach_log_new.ui.athletes;

public class Athletes {

    private int id;
    private String name;
    private String phone;
    private String bday;

    public Athletes(int id, String name, String phone, String bday) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.bday = bday;
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
}
