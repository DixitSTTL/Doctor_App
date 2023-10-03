package com.app.doctorapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelPrePayment;
import com.app.doctorapp.databinding.FragmentPrePaymentBinding;
import com.app.doctorapp.view.BaseFragment;

public class FragmentPrePayment extends BaseFragment {

    private FragmentPrePaymentBinding mBinding;
    private FragViewModelPrePayment mViewModel;

    public FragmentPrePayment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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