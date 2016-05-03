package com.prestonchang.sweat;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "exercises.db";
    public static final String TABLE_EXERCISES = "exercises";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EXERCISENAME = "exercisename";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_SETS = "sets";
    public static final String COLUMN_REPS = "reps";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE" + TABLE_EXERCISES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_EXERCISENAME + " TEXT " +
                COLUMN_WEIGHT + " REAL " +
                COLUMN_SETS + " INTEGER " +
                COLUMN_REPS + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_EXERCISES);
        onCreate(db);
    }

    //Add new row to database
    public void addExercise(Exercise exercise) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_EXERCISENAME, exercise.getName());
        values.put(COLUMN_WEIGHT, exercise.getWeight());
        values.put(COLUMN_SETS, exercise.getSets());
        values.put(COLUMN_REPS, exercise.getReps());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EXERCISES, null, values);
        db.close();
    }

    //Delete exercise from database
    public void deleteExercise(String exerciseName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EXERCISES + " WHERE " + COLUMN_EXERCISENAME + "=\"" + exerciseName + "\";");
    }

    //Print out database as a String
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_EXERCISES + " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()) {
            if(c.getString(c.getColumnIndex("exercisename")) != null) {
                dbString += c.getString(c.getColumnIndex("exercisename"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }

//    public ArrayList<String> getAllValues(String table, String[] column) {
//        ArrayList<String> list = new ArrayList<String>();
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.query(table, column, null, null, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                list.add(cursor.getString(0));
//            } while(cursor.moveToNext());
//        }
//    }

}
