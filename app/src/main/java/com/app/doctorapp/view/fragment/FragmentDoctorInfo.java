package com.app.doctorapp.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelDoctorDetails;
import com.app.doctorapp.databinding.FragmentDoctorInfoBinding;
import com.app.doctorapp.view.BaseFragment;
import com.google.android.material.appbar.MaterialToolbar;

public class FragmentDoctorInfo extends BaseFragment {

    private FragmentDoctorInfoBinding mBinding;
    private FragViewModelDoctorDetails mViewModel;

    String UID;

    public FragmentDoctorInfo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            UID = getArguments().getString("UID");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_doctor_info, container, false);
        mViewModel = new ViewModelProvider(mActivityMain).get(FragViewModelDoctorDetails.class);

        initToolbar();
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }
    private void initToolbar() {

        mActivityMain.setSupportActionBar(mBinding.toolbar);

        mActivityMain.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        mActivityMain.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityMain.onBackPressed();
            }
        });
    }


    GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {

        }
    };

    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setGeneralItemListener(generalItemClickListener);
        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralListener(generalClickListener);
        mViewModel.loadData(UID);

    }
}