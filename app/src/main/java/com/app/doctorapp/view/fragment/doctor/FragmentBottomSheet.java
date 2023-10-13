package com.app.doctorapp.view.fragment.doctor;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.doctor.FragViewModelPrescription;
import com.app.doctorapp.databinding.FragmentBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FragmentBottomSheet extends BottomSheetDialogFragment {


    private FragmentBottomSheetBinding mBinding;

    private FragViewModelPrescription mViewModel;

    public FragmentBottomSheet() {
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
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_bottom_sheet, container, false);
        mViewModel = new ViewModelProvider(getActivity()).get(FragViewModelPrescription.class);


        return mBinding.getRoot();
    }

    private GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            mViewModel.addItem(new GeneralClickListener() {
                @Override
                public void onClick(View view) {
                    getDialog().onBackPressed();
                    getDialog().dismiss();
                    getDialog().cancel();
                    dismiss();
                }
            });


        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.setMViewmodel(mViewModel);
        mBinding.setGeneralClickListener(generalClickListener);
    }

}