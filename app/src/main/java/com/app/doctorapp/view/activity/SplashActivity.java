package com.app.doctorapp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.splashscreen.SplashScreen;
import androidx.databinding.DataBindingUtil;

import com.app.doctorapp.R;
import com.app.doctorapp.databinding.ActivitySplashBinding;
import com.app.doctorapp.view.BaseActivity;

public class SplashActivity extends BaseActivity {

    ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }
}