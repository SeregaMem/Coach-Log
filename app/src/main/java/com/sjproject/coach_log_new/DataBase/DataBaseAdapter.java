package com.sjproject.coach_log_new.DataBase;

import static com.sjproject.coach_log_new.DataBase.DBHelper.KEY_ROWID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sjproject.coach_log_new.object.Athletes;
import com.sjproject.coach_log_new.object.Subscription;
import com.sjproject.coach_log_new.object.Training;
import com.sjproject.coach_log_new.object.Training_athlete;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataBaseAdapter {

    private DBHelper helper;
    private SQLiteDatabase db;

    public List<Athletes> athletesList = new LinkedList<>();
    public List<Training> trainingList = new LinkedList<>();
    public List<Training_athlete> training_athletesList = new LinkedList<>();
    public List<Subscription> subscriptionList = new LinkedList<>();

    public List<Training> local_trainingList = new ArrayList<>();
    public List<Athletes> local_athleteList = new ArrayList<>();
    public List<Subscription> local_subscriptionList = new ArrayList<>();
    public List<Training_athlete> local_training_athleteList = new ArrayList<>();

    public DataBaseAdapter (Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
    }

    public List<Athletes> getAllAthlete() {
        String columns[] = {KEY_ROWID, DBHelper.KEY_NAME, DBHelper.KEY_PHONE, DBHelper.KEY_DATA,
                DBHelper.KEY_ATHLETE_TRAINING_COUNT};
        Cursor cursor = db.query(DBHelper.TABLE_NAME_ATHLETE, columns, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            int indexID = cursor.getColumnIndex(KEY_ROWID);
            int id = cursor.getInt(indexID);
            int indexNAME = cursor.getColumnIndex(DBHelper.KEY_NAME);
            String name = cursor.getString(indexNAME);
            int indexPhone = cursor.getColumnIndex(DBHelper.KEY_PHONE);
            String phone = cursor.getString(indexPhone);
            int indexBDAY = cursor.getColumnIndex(DBHelper.KEY_DATA);
            String bday = cursor.getString(indexBDAY);
            int indexTrainingCount = cursor.getColumnIndex(DBHelper.KEY_ATHLETE_TRAINING_COUNT);
            int training_count = cursor.getInt(indexTrainingCount);
            Athletes athlete = new Athletes(id, name, phone, bday, training_count);
            athletesList.add(athlete);
        }
        cursor.close();
        return athletesList;
    }

    public List<Training> getAllTraining() {
        String columns[] = {KEY_ROWID, DBHelper.KEY_NAME, DBHelper.KEY_DATE_TIMETABLE
                , DBHelper.KEY_TIME_TIMETABLE, DBHelper.KEY_ATHLETES_COUNT};
        Cursor cursor = db.query(DBHelper.TABLE_NAME_TIMETABLE, columns, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            int indexID = cursor.getColumnIndex(KEY_ROWID);
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
        String columns[] = {KEY_ROWID, DBHelper.KEY_BUY_DATE, DBHelper.KEY_DATE_START,
        DBHelper.KEY_DATE_FINISH , DBHelper.KEY_TRAINING_COUNT, DBHelper.KEY_PRICE};
        Cursor cursor = db.query(DBHelper.TABLE_NAME_SUBSCRIPTION, columns,null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            int indexID = cursor.getColumnIndex(KEY_ROWID);
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

    public int getTrainingID(String name, String date, String time) {
        local_trainingList = getAllTraining();
        int i = 0;
        while (local_trainingList.size() > i) {
            Training training = local_trainingList.get(i);
            if (training.getName().equals(name) &&
                    training.getDate().equals(date) &&
                    training.getTime().equals(time))
                return training.getId();
            i++;
        }
        return 0;
    }

    public void addTraining(String nameTraining, String dateTraining, String timeTraining,
                            List<Training_athlete> training_athleteList) {

        ContentValues cv = new ContentValues();

        cv.put(DBHelper.KEY_NAME, nameTraining);
        cv.put(DBHelper.KEY_DATE_TIMETABLE, dateTraining);
        cv.put(DBHelper.KEY_TIME_TIMETABLE, timeTraining);
        cv.put(DBHelper.KEY_ATHLETES_COUNT, 0);

        db.insert(DBHelper.TABLE_NAME_TIMETABLE, null, cv);

        int training_ID = getTrainingID(nameTraining, dateTraining, timeTraining);

        int i = 0;
        while (training_athleteList.size() > i) {
            ContentValues contentValues = new ContentValues();
            Training_athlete training_athlete = training_athleteList.get(i);
            int ATHLETE_ID = training_athlete.getAthlete_id();

            contentValues.put(DBHelper.KEY_ID_ATHLETE, ATHLETE_ID);
            contentValues.put(DBHelper.KEY_ID_TRAINING, training_ID);

            db.insert(DBHelper.TABLE_NAME_TRAINING_ATHLETES, null, contentValues);

            ContentValues cv1 = new ContentValues();

            cv1.put(DBHelper.KEY_ATHLETE_TRAINING_COUNT ,training_athlete.getTraining_id());

            db.update(DBHelper.TABLE_NAME_ATHLETE, cv1,
                    KEY_ROWID + " = " + ATHLETE_ID, null);

            i++;
        }
        int athletes_count = training_athleteList.size();

        updateTraining(training_ID, nameTraining, dateTraining, timeTraining, athletes_count);
    }

    public void addAthlete(String name, String phone, String date) {
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.KEY_NAME, name);
        cv.put(DBHelper.KEY_PHONE, phone);
        cv.put(DBHelper.KEY_DATA, date);
        cv.put(DBHelper.KEY_ATHLETE_TRAINING_COUNT, 0);

        db.insert(DBHelper.TABLE_NAME_ATHLETE, null, cv);
    }

    public void addSubscription(int id_athlete, String buyDate,String startDate,String finishDate,
                                int training_count, int price) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_ROWID, id_athlete);
        cv.put(DBHelper.KEY_BUY_DATE, buyDate);
        cv.put(DBHelper.KEY_DATE_START, startDate);
        cv.put(DBHelper.KEY_DATE_FINISH, finishDate);
        cv.put(DBHelper.KEY_TRAINING_COUNT, training_count);
        cv.put(DBHelper.KEY_PRICE, price);

        db.insert(DBHelper.TABLE_NAME_SUBSCRIPTION, null, cv);
    }

    public void updateTraining(int id, String name, String date, String time, int athletes_count) {
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.KEY_NAME, name);
        cv.put(DBHelper.KEY_DATE_TIMETABLE, date);
        cv.put(DBHelper.KEY_TIME_TIMETABLE, time);
        cv.put(DBHelper.KEY_ATHLETES_COUNT, athletes_count);

        db.update(DBHelper.TABLE_NAME_TIMETABLE, cv,
                KEY_ROWID + "=" + id, null);
    }

    public void updateAthleteCount(int id) {

    }

    public void updateAthlete(int id, int trainingCount) {
        ContentValues cv = new ContentValues();

        cv.put(DBHelper.KEY_ATHLETE_TRAINING_COUNT, trainingCount);

        db.update(DBHelper.TABLE_NAME_ATHLETE, cv,
                KEY_ROWID + " = " + id, null);
    }

    public void deleteAthlete(int athlete_id) {
        db.delete(DBHelper.TABLE_NAME_ATHLETE, "id = " + athlete_id, null);
        db.delete(DBHelper.TABLE_NAME_TRAINING_ATHLETES,
                DBHelper.KEY_ID_ATHLETE + " = " + athlete_id, null);
        db.delete(DBHelper.TABLE_NAME_SUBSCRIPTION, KEY_ROWID + " = " + athlete_id,
                null);
    }

    public void deleteTraining(int training_id) {
        db.delete(DBHelper.TABLE_NAME_TIMETABLE, "id = " + training_id, null);
        db.delete(DBHelper.TABLE_NAME_TRAINING_ATHLETES,
                DBHelper.KEY_ID_TRAINING + " = " + training_id, null);
    }
}
