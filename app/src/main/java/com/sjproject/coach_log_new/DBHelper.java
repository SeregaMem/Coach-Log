package com.sjproject.coach_log_new;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.sjproject.coach_log_new.ui.athletes.Athletes;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DataBase";
    public static final String TABLE_NAME_ATHLETE = "athletesTable";
    public static final String TABLE_NAME_TIMETABLE = "trainingTable";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_ROWID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_DATA = "bday";
    public static final String KEY_TRAINING_TABLE = "trainingTable";
    public static final String KEY_DATE_TIMETABLE = "date";
    public static final String KEY_TIME_TIMETABLE = "time";
    public static final String KEY_ATHLETES_COUNT = "athletes_count";

    private static final String CREATE_TABLE_ATHLETES = "create table " + TABLE_NAME_ATHLETE +
            " (" + KEY_ROWID + " integer primary key autoincrement, " + KEY_NAME + " text, " +
            KEY_PHONE + " text, " + KEY_DATA + " text, " + KEY_TRAINING_TABLE + " integer, " +
            " FOREIGN key (trainingTable) REFERENCES trainingTable(id));";

    private static final String CREATE_TABLE_TIMETABLE = "create table " + TABLE_NAME_TIMETABLE +
            " (" + KEY_ROWID + " integer primary key autoincrement, " + KEY_NAME + " text, " +
            KEY_DATE_TIMETABLE + " text, " + KEY_TIME_TIMETABLE + " text, " + KEY_ATHLETES_COUNT +
            " integer);";

    private static final String DROP_TABLE = "drop table if exists ";
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_TIMETABLE);
            db.execSQL(CREATE_TABLE_ATHLETES);
        } catch (SQLException e) {
            Toast.makeText(context, "" + e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + TABLE_NAME_ATHLETE);
        onCreate(db);
    }
}
