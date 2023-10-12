package com.app.doctorapp.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.app.doctorapp.R;
import com.app.doctorapp.databinding.ActivityDoctorBinding;
import com.app.doctorapp.view.BaseActivity;
import com.google.android.material.appbar.MaterialToolbar;

public class DoctorActivity extends BaseActivity  implements NavController.OnDestinationChangedListener{

    private ActivityDoctorBinding mBinding;
    private NavController navController;

    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_doctor);
        setContentView(mBinding.getRoot());
        navController = Navigation.findNavController(this, R.id.frame);
        navController.addOnDestinationChangedListener(this);
        NavigationUI.setupWithNavController(mBinding.bottomNavigationView, navController);

    }

    public void navigateHome() {
        navController.navigate(R.id.fragmentAppointments);
    }
    public void navigateChat() {
        navController.navigate(R.id.fragmentChat);
    }

    public void navigateChatCore(String doctor_uid) {
        Bundle bundle = new Bundle();
        bundle.putString("patient_uid", doctor_uid);
        navController.navigate(R.id.action_fragmentChat_to_fragmentChatCore, bundle);
    }
    public void navigatePrescription(String doctor_uid) {
        Bundle bundle = new Bundle();
        bundle.putString("patient_uid", doctor_uid);
        navController.navigate(R.id.action_fragmentChat_to_fragmentChatCore, bundle);
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

    @Override
    public void onBackPressed() {
        if (navController.popBackStack()) {
            // Handle custom behavior if needed
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {

    }
}