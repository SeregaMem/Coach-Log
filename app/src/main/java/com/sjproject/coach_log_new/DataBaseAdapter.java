package com.sjproject.coach_log_new;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sjproject.coach_log_new.ui.athletes.Athletes;
import com.sjproject.coach_log_new.ui.timetable.Training;

import java.util.LinkedList;
import java.util.List;

public class DataBaseAdapter {

    DBHelper helper;
    SQLiteDatabase db;
    List<Athletes> athletesList = new LinkedList<>();
    List<Training> trainingList = new LinkedList<>();

    public DataBaseAdapter (Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
    }

    public List<Athletes> getAllAthlete() {
        String columns[] = {DBHelper.KEY_ROWID, DBHelper.KEY_NAME, DBHelper.KEY_PHONE, DBHelper.KEY_DATA};
        Cursor cursor = db.query(DBHelper.TABLE_NAME_ATHLETE, columns, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            int indexID = cursor.getColumnIndex(DBHelper.KEY_ROWID);
            int id = cursor.getInt(indexID);
            int indexNAME = cursor.getColumnIndex(DBHelper.KEY_NAME);
            String name = cursor.getString(indexNAME);
            int indexPhone = cursor.getColumnIndex(DBHelper.KEY_PHONE);
            String phone = cursor.getString(indexPhone);
            int indexBDAY = cursor.getColumnIndex(DBHelper.KEY_DATA);
            String bday = cursor.getString(indexBDAY);
            Athletes athlete = new Athletes(id, name, phone, bday);
            athletesList.add(athlete);
        }
        cursor.close();
        return athletesList;
    }

    public List<Training> getAllTraining() {
        String colums[] = {DBHelper.KEY_ROWID, DBHelper.KEY_NAME, DBHelper.KEY_DATE_TIMETABLE,
                DBHelper.KEY_TIME_TIMETABLE, DBHelper.KEY_ATHLETES_COUNT};
        Cursor cursor = db.query(DBHelper.TABLE_NAME_TIMETABLE, colums, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            int indexID = cursor.getColumnIndex(DBHelper.KEY_ROWID);
            int id = cursor.getInt(indexID);
            int indexName = cursor.getColumnIndex(DBHelper.KEY_NAME);
            String name = cursor.getString(indexName);
            int indexDate = cursor.getColumnIndex(DBHelper.KEY_DATE_TIMETABLE);
            String date = cursor.getString(indexDate);
            int indexTime = cursor.getColumnIndex(DBHelper.KEY_TIME_TIMETABLE);
            String time = cursor.getString(indexTime);
            int indexAthletesCount = cursor.getColumnIndex(DBHelper.KEY_ATHLETES_COUNT);
            int athletesCount = cursor.getInt(indexAthletesCount);
            Training training = new Training(id, name, date, time, athletesCount);
            trainingList.add(training);
        }
        cursor.close();
        return trainingList;
    }
}
