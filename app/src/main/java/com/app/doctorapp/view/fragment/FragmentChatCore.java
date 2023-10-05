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
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelChatCore;
import com.app.doctorapp.databinding.FragmentChatCoreBinding;
import com.app.doctorapp.view.BaseFragment;

public class FragmentChatCore extends BaseFragment {

    FragViewModelChatCore mViewModel;
    FragmentChatCoreBinding mBinding;
    String doctor_uid;

    public FragmentChatCore() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            doctor_uid = getArguments().getString("doctor_uid");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_chat_core, container, false);
        mViewModel = new ViewModelProvider(mActivityMain).get(FragViewModelChatCore.class);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralItemListener(generalItemClickListener);
        mBinding.setGeneralListener(generalClickListener);
        mViewModel.loadChatOuter(doctor_uid);
    }

    GeneralItemClickListener generalItemClickListener = new GeneralItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object item) {

        }
    };

    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            if (view == mBinding.sendBtn) {
                mViewModel.sendMessage();

            }

        }
    };
}