package com.prestonchang.sweat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import java.io.File;
import org.apache.commons.io.FileUtils;


public class Workout extends ActionBarActivity {
    private ArrayList<String> exercises;
    private ArrayAdapter<String> exercisesAdapter;
    private ListView exerciseItems;

    //Create class of exercises
    //Have arrayList of exercises

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        exerciseItems = (ListView) findViewById(R.id.exerciseItems);
        readItems();
        exercisesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exercises);

        exerciseItems.setAdapter(exercisesAdapter);
        //Setup remove listener method call
        setupListViewListener();

    }

    //Attaches a long click listener to the listView
    private void setupListViewListener() {
        exerciseItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        //Remove the item within array at postion
                        exercises.remove(position);
                        //Refresh the adapter
                        exercisesAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                }
        );
    }

    public void onAddItem(View v) {
        EditText etNewExercise = (EditText) findViewById(R.id.etNewExercise);
        String exerciseText = etNewExercise.getText().toString();
        exercisesAdapter.add(exerciseText);
        etNewExercise.setText("");
        writeItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File workoutFile = new File(filesDir, "workout.txt");
        try {
            exercises = new ArrayList<String>(FileUtils.readLines(workoutFile));
        } catch (IOException e) {
            exercises = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File workoutFile = new File(filesDir, "workout.txt");
        try {
            FileUtils.writeLines(workoutFile, exercises);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
