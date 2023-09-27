package com.nike.doctorapp.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.nike.doctorapp.R;
import com.nike.doctorapp.databinding.ActivityWelcomeBinding;
import com.nike.doctorapp.view.BaseActivity;

public class WelcomeActivity extends BaseActivity implements NavController.OnDestinationChangedListener {

    private NavController navController;
    ActivityWelcomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        setContentView(mBinding.getRoot());
        navController = Navigation.findNavController(this, R.id.frame);
        navController.addOnDestinationChangedListener(this);

    }

    public void navigateWel2() {
        navController.navigate(R.id.action_fragmentWelcome1_to_fragmentWelcome2);
    }

    public void navigateWel3() {
        navController.navigate(R.id.action_fragmentWelcome2_to_fragmentWelcome3);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {

    }


    @Override
    public void onBackPressed() {
        if (navController.popBackStack()) {
            // Handle custom behavior if needed
        } else {
            super.onBackPressed();
        }
    }

}