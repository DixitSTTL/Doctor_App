package com.nike.doctorapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.nike.doctorapp.R;
import com.nike.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.nike.doctorapp.businesslogic.viewmodels.fragment.FragViewModelWel2;
import com.nike.doctorapp.databinding.FragmentWelcome2Binding;
import com.nike.doctorapp.utils.EnumUser;
import com.nike.doctorapp.view.BaseFragment;

public class FragmentWelcome2 extends BaseFragment {

    FragViewModelWel2 mViewmodel;
    FragmentWelcome2Binding mBinding;


    public FragmentWelcome2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_welcome2, container, false);
        mViewmodel = new ViewModelProvider(getActivity()).get(FragViewModelWel2.class);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.setMViewmodel(mViewmodel);
        mBinding.setGeneralClickListener(generalClickListener);
    }

    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            if (view == mBinding.llDoctor) {
                mViewmodel.observeUser.set(EnumUser.DOCTOR);
            } else if (view == mBinding.llPatient) {
                mViewmodel.observeUser.set(EnumUser.PATIENT);
            } else {
                mActivityWelcome.navigateWel3();
            }
        }
    };
}