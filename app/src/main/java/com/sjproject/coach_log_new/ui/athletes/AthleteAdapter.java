package com.sjproject.coach_log_new.ui.athletes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjproject.coach_log_new.DBHelper;
import com.sjproject.coach_log_new.R;


public class AthleteAdapter extends RecyclerView.Adapter<AthleteAdapter.AthleteViewHolder> {

    String LOG_TAG = "myLog";

    int moveToFirstCount = 0;

    private int countItems;

    private Context parent;

    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor c;

    int nameColIndex = 1;
    int bdayColIndex = 3;
    String name;
    String bday;

    public AthleteAdapter(int athletesCount, Context parent) {
        countItems = athletesCount;

        dbHelper = new DBHelper(parent);
        db = dbHelper.getWritableDatabase();

        c = db.query("athletesTable", null, null, null,
                null, null, null);

        c.moveToFirst();
        this.parent = parent;
    }

    @NonNull
    @Override
    public AthleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.item_athlete;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        AthleteViewHolder viewHolder = new AthleteViewHolder(view);

        if (moveToFirstCount == 0) {
            dbHelper = new DBHelper(context);
            db = dbHelper.getWritableDatabase();

            c = db.query("athletesTable", null, null, null,
                    null, null, null);

            c.moveToFirst();
        } else {
            c.moveToNext();
        }

        name = c.getString(nameColIndex);
        bday = c.getString(bdayColIndex);

        moveToFirstCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AthleteViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return countItems;
    }

    class AthleteViewHolder extends RecyclerView.ViewHolder {

        TextView athleteNameView, athletebdayView;

        public AthleteViewHolder(@NonNull View itemView) {
            super(itemView);
            athleteNameView = (TextView) itemView.findViewById(R.id.tv_athleteName);
            athletebdayView = (TextView) itemView.findViewById(R.id.tv_athlete_bday);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /* dbHelper = new DBHelper(parent);
                    db = dbHelper.getWritableDatabase();

                    Log.d(LOG_TAG, "delClick");
                    String deleteName = athleteNameView.getText().toString();
                    db.delete("athletesTable", "athlete_name = ?",
                            new String[]{String.valueOf(deleteName)});

                    db.close();
                    dbHelper.close(); */
                }
            });
        }

        void bind() {
            athleteNameView.setText(name);
            athletebdayView.setText(bday);

            db.close();
            dbHelper.close();
        }
    }
}
