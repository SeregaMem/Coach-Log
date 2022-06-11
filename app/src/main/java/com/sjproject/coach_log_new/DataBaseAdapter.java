package com.sjproject.coach_log_new;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sjproject.coach_log_new.ui.athletes.Athletes;
import com.sjproject.coach_log_new.ui.athletes.Subscription;
import com.sjproject.coach_log_new.ui.timetable.Training;
import com.sjproject.coach_log_new.ui.timetable.Training_athlete;

import java.util.LinkedList;
import java.util.List;

public class DataBaseAdapter {

    DBHelper helper;
    SQLiteDatabase db;
    List<Athletes> athletesList = new LinkedList<>();
    List<Training> trainingList = new LinkedList<>();
    List<Training_athlete> training_athletesList = new LinkedList<>();
    List<Subscription> subscriptionList = new LinkedList<>();

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
        String columns[] = {DBHelper.KEY_ROWID, DBHelper.KEY_NAME, DBHelper.KEY_DATE_TIMETABLE
                , DBHelper.KEY_TIME_TIMETABLE, DBHelper.KEY_ATHLETES_COUNT};
        Cursor cursor = db.query(DBHelper.TABLE_NAME_TIMETABLE, columns, null,
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
            int indexAthleteCount = cursor.getColumnIndex(DBHelper.KEY_ATHLETES_COUNT);
            int athleteCount = cursor.getInt(indexAthleteCount);
            Training training = new Training(id, name, date, time, athleteCount);
            trainingList.add(training);
        }
        cursor.close();
        return trainingList;
    }

    public List<Training_athlete> getAllTrainingAthlete() {
        String columns[] = {DBHelper.KEY_ID_ATHLETE, DBHelper.KEY_ID_TRAINING};
        Cursor cursor = db.query(DBHelper.TABLE_NAME_TRAINING_ATHLETES, columns, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            int indexAthleteID = cursor.getColumnIndex(DBHelper.KEY_ID_ATHLETE);
            int athlete_id = cursor.getInt(indexAthleteID);
            int indexTrainingID = cursor.getColumnIndex(DBHelper.KEY_ID_TRAINING);
            int training_id = cursor.getInt(indexTrainingID);
            Training_athlete training_athlete = new Training_athlete(athlete_id, training_id);
            training_athletesList.add(training_athlete);
        }
        cursor.close();
        return training_athletesList;
    }

    public List<Subscription> getAllSubscription() {
        String columns[] = {DBHelper.KEY_ROWID, DBHelper.KEY_BUY_DATE, DBHelper.KEY_DATE_START,
        DBHelper.KEY_DATE_FINISH , DBHelper.KEY_TRAINING_COUNT, DBHelper.KEY_PRICE};
        Cursor cursor = db.query(DBHelper.TABLE_NAME_SUBSCRIPTION, columns,null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            int indexID = cursor.getColumnIndex(DBHelper.KEY_ROWID);
            int id = cursor.getInt(indexID);
            int indexBuyDate = cursor.getColumnIndex(DBHelper.KEY_BUY_DATE);
            String buyDate = cursor.getString(indexBuyDate);
            int indexDateStart = cursor.getColumnIndex(DBHelper.KEY_DATE_START);
            String startDate = cursor.getString(indexDateStart);
            int indexDateFinish = cursor.getColumnIndex(DBHelper.KEY_DATE_FINISH);
            String finishDate = cursor.getString(indexDateFinish);
            int indexTrainingCount = cursor.getColumnIndex(DBHelper.KEY_TRAINING_COUNT);
            int trainingCount = cursor.getInt(indexTrainingCount);
            int indexPrice = cursor.getColumnIndex(DBHelper.KEY_PRICE);
            int price = cursor.getInt(indexPrice);
            Subscription subscription = new Subscription(id, buyDate, startDate, finishDate,
                    trainingCount, price);
            subscriptionList.add(subscription);
        }
        cursor.close();
        return subscriptionList;
    }
}
