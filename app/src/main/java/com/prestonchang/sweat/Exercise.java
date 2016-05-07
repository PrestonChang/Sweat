package com.prestonchang.sweat;

import android.os.Parcelable;
import android.os.Parcel;

public class Exercise implements Parcelable {

    private int id;
    private String name;
    private double weight;
    private int sets;
    private int reps;
    private int mData;
    //private String comment;

    public Exercise (int id, String name, double weight, int sets, int reps) {
        this.id = id;
        this.name= name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    public Exercise (String name, double weight, int sets, int reps) {
        this.name= name;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setName(String exerciseName) {
        name = exerciseName;
    }

    private void setWeight(double exerciseWeight) {
        weight = exerciseWeight;
    }

    private void setSets(int exerciseSets) {
        sets = exerciseSets;
    }

    private void setReps(int exerciseReps) {
        reps = exerciseReps;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeDouble(weight);
        out.writeInt(sets);
        out.writeInt(reps);

    }

    public static final Parcelable.Creator<Exercise> CREATOR
            = new Parcelable.Creator<Exercise>() {
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    private Exercise(Parcel in) {
        name = in.readString();
        weight = in.readDouble();
        sets = in.readInt();
        reps = in.readInt();
    }

}