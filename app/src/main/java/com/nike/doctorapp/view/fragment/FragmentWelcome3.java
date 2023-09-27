package com.nike.doctorapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nike.doctorapp.R;
import com.nike.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.nike.doctorapp.businesslogic.viewmodels.fragment.FragViewModelWel3;
import com.nike.doctorapp.databinding.FragmentWelcome3Binding;
import com.nike.doctorapp.utils.EnumGender;
import com.nike.doctorapp.utils.EnumUser;

public class FragmentWelcome3 extends Fragment {

    FragViewModelWel3 mViewmodel;
    FragmentWelcome3Binding mBinding;
    public FragmentWelcome3() {
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
        mBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_welcome3, container, false);
        mViewmodel = new ViewModelProvider(getActivity()).get(FragViewModelWel3.class);
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
            if (view == mBinding.llmale) {
                mViewmodel.observeGender.set(EnumGender.MALE);
            } else if (view == mBinding.llFemale) {
                mViewmodel.observeGender.set(EnumGender.FEMALE);
            } else {

            }
        }
    };
}