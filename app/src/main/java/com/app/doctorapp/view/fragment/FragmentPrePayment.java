package com.app.doctorapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelPrePayment;
import com.app.doctorapp.databinding.FragmentPrePaymentBinding;
import com.app.doctorapp.models.DateModel;
import com.app.doctorapp.models.DoctorDetailsModel;
import com.app.doctorapp.models.UserDoctorModel;
import com.app.doctorapp.view.BaseFragment;

public class FragmentPrePayment extends BaseFragment {

    private FragmentPrePaymentBinding mBinding;
    private FragViewModelPrePayment mViewModel;

    private String UID;
    private UserDoctorModel userDoctorModel;
    private DoctorDetailsModel doctorDetailsModel;
    private DateModel dateModel;
    private String timeSlot;

    public FragmentPrePayment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            FragmentPrePaymentArgs bundle = FragmentPrePaymentArgs.fromBundle(getArguments());
            UID = bundle.getUID();
            userDoctorModel = bundle.getUserDoctorModel();
            doctorDetailsModel = bundle.getDoctorDetailsModel();
            dateModel = bundle.getDateModel();
            timeSlot = bundle.getTime();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_pre_payment, container, false);
        mViewModel = new ViewModelProvider(mActivityMain).get(FragViewModelPrePayment.class);
        initToolbar();

        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.observeMainDetail.set(userDoctorModel);
        mViewModel.observeSecondaryDetail.set(doctorDetailsModel);
        mBinding.setUserDoctorModel(mViewModel.observeMainDetail.get());
        mBinding.setDoctorDetailsModel(mViewModel.observeSecondaryDetail.get());
        mBinding.setDateModel(dateModel);
        mBinding.setTimeSlot(timeSlot);
        mBinding.setGeneralListener(generalClickListener);
    }

    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {


            mViewModel.generateAppointment(timeSlot, dateModel);

        }
    };

    private void initToolbar() {

        mActivityMain.setSupportActionBar(mBinding.toolbar);
        mActivityMain.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back2);
        mActivityMain.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityMain.onBackPressed();
            }
        });
    }

}