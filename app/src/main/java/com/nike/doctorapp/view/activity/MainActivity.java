package com.nike.doctorapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nike.doctorapp.R;
import com.nike.doctorapp.view.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}