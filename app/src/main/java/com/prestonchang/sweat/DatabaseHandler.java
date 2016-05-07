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
        String query = "CREATE TABLE " + TABLE_EXERCISES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXERCISENAME + " TEXT, " +
                COLUMN_WEIGHT + " REAL, " +
                COLUMN_SETS + " INTEGER, " +
                COLUMN_REPS + " INTEGER" +
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

    public void deleteExercise(Exercise exercise) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_EXERCISES, COLUMN_ID + " = ?",
                new String[] { String.valueOf(exercise.getId()) });
        db.close();
    }

    public Exercise getExercise(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_EXERCISES, new String[] { COLUMN_ID, COLUMN_EXERCISENAME, COLUMN_WEIGHT
                , COLUMN_SETS, COLUMN_REPS}, COLUMN_ID + "=?",
                new String[] {String.valueOf(id) }, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Exercise exercise = new Exercise(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                Double.parseDouble(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)));
        return exercise;
    }

    //Somethings not working here
    public ArrayList<Exercise> getAllExercises() {
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EXERCISES;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Exercise exercise = new Exercise(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        Double.parseDouble(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)));
                exerciseList.add(exercise);
            } while(cursor.moveToNext());
        }


        return exerciseList;
    }
}
