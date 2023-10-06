package com.app.doctorapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.app.doctorapp.R;
import com.app.doctorapp.databinding.FragmentPayOptionBinding;
import com.app.doctorapp.view.BaseFragment;

public class FragmentPayOption extends BaseFragment {
    FragmentPayOptionBinding mBinding;

    public FragmentPayOption() {
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

        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_pay_option, container, false);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }
}