package com.prestonchang.sweat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseRowAdapter extends ArrayAdapter<Exercise> {

    ExerciseRowAdapter(Context context, ArrayList<Exercise> exercises) {
        super(context, R.layout.exercise_row, exercises);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater exerciseInflater = LayoutInflater.from(getContext());
        View exerciseView = exerciseInflater.inflate(R.layout.exercise_row, parent, false);

        Exercise exercise = getItem(position);

        TextView exerciseName = (TextView) exerciseView.findViewById(R.id.exerciseName);
        TextView exerciseWeight = (TextView) exerciseView.findViewById(R.id.exerciseWeight);
        TextView exerciseSets = (TextView) exerciseView.findViewById(R.id.exerciseSets);
        TextView exerciseReps = (TextView) exerciseView.findViewById(R.id.exerciseRepetitions);

        exerciseName.setText(exercise.getName());
        exerciseWeight.setText(String.valueOf(exercise.getWeight()));
        exerciseSets.setText(String.valueOf(exercise.getSets()));
        exerciseReps.setText(String.valueOf(exercise.getReps()));


        return exerciseView;

    }
}
