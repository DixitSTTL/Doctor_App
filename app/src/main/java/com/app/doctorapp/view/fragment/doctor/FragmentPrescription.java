package com.app.doctorapp.view.fragment.doctor;

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
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.doctor.FragViewModelPrescription;
import com.app.doctorapp.databinding.FragmentPrescriptionBinding;
import com.app.doctorapp.view.BaseFragment;


public class FragmentPrescription extends BaseFragment {

    private FragmentPrescriptionBinding mBinding;
    FragViewModelPrescription mViewModel;
    String patient_uid;

    public FragmentPrescription() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            patient_uid = getArguments().getString("patient_uid");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_prescription, container, false);
        mViewModel = new ViewModelProvider(mActivityDoc).get(FragViewModelPrescription.class);


        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            if (view == mBinding.addBtn) {

                mActivityDoc.navigateBottomSheet();
            } else {

            }

        }
    };
    GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.loadMyAppointments(patient_uid);
        mBinding.setGeneralClickListener(generalClickListener);
        mBinding.setGeneralItemClickListener(generalItemClickListener);
        mBinding.setMViewmodel(mViewModel);

    }
}