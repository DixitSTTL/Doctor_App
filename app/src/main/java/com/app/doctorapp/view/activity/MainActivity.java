package com.app.doctorapp.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.app.doctorapp.R;
import com.app.doctorapp.databinding.ActivityMainBinding;
import com.app.doctorapp.models.DateModel;
import com.app.doctorapp.models.DoctorDetailsModel;
import com.app.doctorapp.models.UserDoctorModel;
import com.app.doctorapp.view.BaseActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends BaseActivity implements NavController.OnDestinationChangedListener {
    ActivityMainBinding mBinding;
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

    public void navigateDoctorDetails(String UID) {
        Bundle bundle = new Bundle();
        bundle.putString("UID", UID);
        navController.navigate(R.id.action_fragmentHome_to_fragmentDoctorInfo, bundle);
    }

    public void navigatePrePayment(ObservableField<UserDoctorModel> observeMainDetail, ObservableField<DoctorDetailsModel> observeSecondaryDetail, DateModel observeSelectedDate, String observeSelectedTime, String UID) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("observeMainDetail", observeMainDetail.get());
        bundle.putParcelable("observeSecondaryDetail", observeSecondaryDetail.get());
        bundle.putParcelable("observeSelectedDate", observeSelectedDate);
        bundle.putString("observeSelectedTime", observeSelectedTime);
        bundle.putString("UID", UID);
        navController.navigate(R.id.action_fragmentDoctorInfo_to_fragmentPrePayment, bundle);
    }


    public void navigateToNotification() {
//        navController.navigate(R.id.action_fragmentWelcome1_to_fragmentWelcome2);
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