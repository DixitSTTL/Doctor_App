package com.nike.doctorapp;


import android.annotation.SuppressLint;
import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {


    @SuppressLint("ScheduleExactAlarm")
    @Override
    public void onCreate() {
        super.onCreate();


    }

}
