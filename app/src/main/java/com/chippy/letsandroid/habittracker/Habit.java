package com.chippy.letsandroid.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.chippy.letsandroid.habittracker.data.HabitContract.HabitEntry;
import com.chippy.letsandroid.habittracker.data.HabitDbHelper;

public class Habit extends AppCompatActivity {
    private HabitDbHelper mHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        mHelper = new HabitDbHelper(this);
    }
// Displays number of rows and all data in rows
    private void displayDatabaseInfo(){
        Cursor cursor = mHelper.readAllHabitsData();
        String id;
        String name;
        String description;
        String frequency;
        String value;
        try {
            TextView rows = (TextView) findViewById(R.id.rows);
            TextView data = (TextView) findViewById(R.id.data);
            rows.setText("Number of rows in the table: " + cursor.getCount());
            data.setText("");
            for (int i=0; i<cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                id = String.valueOf(cursor.getInt(cursor.getColumnIndex(HabitEntry._ID)));
                name = cursor.getString(cursor.getColumnIndex(HabitEntry.COLUMN_HABIT));
                description = cursor.getString(cursor.getColumnIndex(HabitEntry.COLUMN_DESCRIPTION));
                frequency = String.valueOf(cursor.getInt(cursor.getColumnIndex(HabitEntry.COLUMN_FREQUENCY)));
                value = String.valueOf(cursor.getInt(cursor.getColumnIndex(HabitEntry.COLUMN_VALUE)));
                data.append("\n"+ id + " " + name + " " + description + " " + frequency + " " + value);
            }
        }finally {
            cursor.close();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insert:
                mHelper.insertHabit(this);
                displayDatabaseInfo();
                return true;
            case R.id.delete:
                mHelper.deleteAllHabits(this);
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}