package com.nike.doctorapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.nike.doctorapp.R;
import com.nike.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.nike.doctorapp.businesslogic.viewmodels.fragment.FragViewModelWel1;
import com.nike.doctorapp.databinding.FragmentWelcome1Binding;
import com.nike.doctorapp.view.BaseFragment;

public class FragmentWelcome1 extends BaseFragment {

    FragViewModelWel1 mViewmodel;
    FragmentWelcome1Binding mBinding;


    public FragmentWelcome1() {
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
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_welcome1, container, false);
        mViewmodel = new ViewModelProvider(this).get(FragViewModelWel1.class);
        mBinding.setGeneralClickListener(generalClickListener);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    GeneralClickListener generalClickListener = view -> {

        mActivityWelcome.navigateWel2();

    };
}