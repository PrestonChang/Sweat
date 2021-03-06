package com.prestonchang.sweat;

import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

public class AddExercise extends ActionBarActivity  {

    private int mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
    }

    //TODO check if exercise object is empty
    //if empty display message
    public void onAddExercise(View v) {
        Exercise exercise = createExercise();
        DatabaseHandler handler = new DatabaseHandler(this,null, null, 1);
        handler.addExercise(exercise);
        Intent i = new Intent(this, Workout.class);
        i.putExtra("ExerciseObject", exercise);
        startActivity(i);

    }

    private Exercise createExercise() {
        EditText exerciseTextField = (EditText) findViewById(R.id.exerciseTextField);
        String exerciseName = exerciseTextField.getText().toString();

        EditText weightTextField = (EditText) findViewById(R.id.weightTextField);
        double exerciseWeight = Double.parseDouble(weightTextField.getText().toString());

        EditText setsTextField = (EditText) findViewById(R.id.setsTextField);
        int exerciseSets = Integer.parseInt(setsTextField.getText().toString());

        EditText repsTextField = (EditText) findViewById(R.id.repsTextField);
        int exerciseReps = Integer.parseInt(repsTextField.getText().toString());

        return new Exercise(exerciseName, exerciseWeight, exerciseSets, exerciseReps);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exercise, menu);
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


}



