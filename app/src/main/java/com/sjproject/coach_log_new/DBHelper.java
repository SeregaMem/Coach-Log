package com.sjproject.coach_log_new;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "DataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table IF NOT EXISTS trainingTable ( id INTEGER primary key autoincrement, " +
                "training_name text, data text, time text, athletes_count integer);");

        db.execSQL("create table IF NOT EXISTS athletesTable ( id INTEGER primary key autoincrement, " +
                "athlete_name text, phoneNumber text, bday text, trainingTable integer," +
                " FOREIGN key (trainingTable) REFERENCES trainigTable(id));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
