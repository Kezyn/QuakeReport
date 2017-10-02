package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by Marc on 30.09.2017.
 */

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private String mDate;

    public Earthquake(double magnitude, String location, String date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        return mDate;
    }
}
