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
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelRecipes;
import com.app.doctorapp.databinding.FragmentReceiptBinding;
import com.app.doctorapp.view.BaseFragment;

public class FragmentReceipt extends BaseFragment {

    private FragViewModelRecipes mViewModel;
    private FragmentReceiptBinding mBinding;

    public FragmentReceipt() {
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
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_receipt, container, false);
        mViewModel = new ViewModelProvider(mActivityMain).get(FragViewModelRecipes.class);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
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

        mViewModel.loadAllAppointments();
        mBinding.setGeneralItemListener(generalItemClickListener);
        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralListener(generalClickListener);

    }
}