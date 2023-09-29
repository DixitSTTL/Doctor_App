package com.app.doctorapp.view.activity;

import static com.app.doctorapp.utils.ConstantData.USER_LOGIN;

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

        if (preferences.getString(R.string.user_login).equals(USER_LOGIN)) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, WelcomeActivity.class));
        }

        finish();
    }
}