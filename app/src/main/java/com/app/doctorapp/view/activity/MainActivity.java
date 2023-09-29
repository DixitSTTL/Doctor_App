package com.app.doctorapp.view.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.app.doctorapp.R;
import com.app.doctorapp.databinding.ActivityMainBinding;
import com.app.doctorapp.view.BaseActivity;

public class MainActivity extends BaseActivity implements NavController.OnDestinationChangedListener {
    ActivityMainBinding mBinding;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(mBinding.getRoot());

        navController = Navigation.findNavController(this, R.id.frame);
        navController.addOnDestinationChangedListener(this);
        NavigationUI.setupWithNavController(mBinding.bottomNavigationView, navController);
    }

    public void navigateHome() {
        navController.navigate(R.id.fragmentHome);
    }

    public void navigateReceipt() {
        navController.navigate(R.id.fragmentReceipt);
    }

    public void navigateChat() {
        navController.navigate(R.id.fragmentChat);
    }

    public void navigateProfile() {
        navController.navigate(R.id.fragmentProfile);
    }


    public void navigateToNotification() {
//        navController.navigate(R.id.action_fragmentWelcome1_to_fragmentWelcome2);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {

    }
}