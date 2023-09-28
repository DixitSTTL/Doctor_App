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
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelSignUp;
import com.app.doctorapp.databinding.FragmentSignUpBinding;
import com.app.doctorapp.view.BaseFragment;

public class FragmentSignUp extends BaseFragment {

    FragmentSignUpBinding mBinding;
    FragViewModelSignUp mViewmodel;

    public FragmentSignUp() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_sign_up, container, false);
        mViewmodel = new ViewModelProvider(mActivityWelcome).get(FragViewModelSignUp.class);
        return mBinding.getRoot();
    }

    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBinding.signInBtn) {
                mActivityWelcome.navigateSignIn2();
            } else {

                mViewmodel.setOtp(mActivityWelcome);
//                mActivityWelcome.navigateOTP();

            }


        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setGeneralClickListener(generalClickListener);
        mBinding.setMViewmodel(mViewmodel);

    }
}