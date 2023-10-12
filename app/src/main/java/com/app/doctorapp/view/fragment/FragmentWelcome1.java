package com.app.doctorapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelWel1;
import com.app.doctorapp.databinding.FragmentWelcome1Binding;
import com.app.doctorapp.view.BaseFragment;

public class FragmentWelcome1 extends BaseFragment {

    private FragViewModelWel1 mViewmodel;
    private FragmentWelcome1Binding mBinding;
    private GeneralClickListener generalClickListener = view -> {

        mActivityWelcome.navigateWel2();

    };


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
}