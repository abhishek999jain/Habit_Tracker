package com.chippy.letsandroid.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by chippy on 2017-01-15.
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create SQL query
        String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + "("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntry.COLUMN_HABIT + " TEXT NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_DESCRIPTION + " TEXT, "
                + HabitContract.HabitEntry.COLUMN_FREQUENCY + " INTEGER NOT NULL DEFAULT 0, "
                + HabitContract.HabitEntry.COLUMN_VALUE + " INTEGER NOT NULL DEFAULT -1);";
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //Insert Dummy Habits DATA
    public void insertHabit(Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT, "IN Gym");
        values.put(HabitContract.HabitEntry.COLUMN_DESCRIPTION, "Stay Fit");
        long newRowId = db.insert(HabitContract.HabitEntry.TABLE_NAME,null,values);
        Toast.makeText(context, "New Row ID: " + newRowId, Toast.LENGTH_SHORT).show();
    }
    // Returns cursor with all data
    public Cursor readAllHabitsData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(HabitContract.HabitEntry.TABLE_NAME,null,null,null,null,null,null);
    }
    //Delete all rows
    public void deleteAllHabits(Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HabitContract.HabitEntry.TABLE_NAME,null,null);
        Toast.makeText(context, "All rows deleted!Table is empty", Toast.LENGTH_SHORT).show();
    }
}
