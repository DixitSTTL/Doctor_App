package com.app.doctorapp.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.ui.NavigationUI;

import com.app.doctorapp.R;
import com.app.doctorapp.databinding.ActivityMainBinding;
import com.app.doctorapp.view.BaseActivity;
import com.app.doctorapp.view.fragment.FragmentChatDirections;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends BaseActivity implements NavController.OnDestinationChangedListener {
    private ActivityMainBinding mBinding;
    private NavController navController;
    private ActionBarDrawerToggle mDrawerToggle;


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

    public void navigateChatCore(NavDirections action, Navigator.Extras extras) {

        navController.navigate(action,extras);
    }
    public void navigateToNotification() {
//        navController.navigate(R.id.action_fragmentWelcome1_to_fragmentWelcome2);
    }

    public void navigateDoctorDetails(NavDirections action, Navigator.Extras extras) {

        navController.navigate(action,extras);
    }

    public void navigatePrePayment(NavDirections action) {

        navController.navigate(action);
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

    public void setToolbar(MaterialToolbar toolbar) {
        mDrawerToggle = new ActionBarDrawerToggle(this, null, toolbar, R.string.navigationDrawerOpen, R.string.navigationDrawerClose) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawerToggle.setToolbarNavigationClickListener(v -> {
            onBackPressed();
        });
//        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


    }


}