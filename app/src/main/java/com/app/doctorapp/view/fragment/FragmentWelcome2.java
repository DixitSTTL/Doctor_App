package com.app.doctorapp.view.fragment;

import static com.app.doctorapp.utils.ConstantData.USER_DOCTOR;
import static com.app.doctorapp.utils.ConstantData.USER_PATIENT;

import android.graphics.Path;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelWel2;
import com.app.doctorapp.databinding.FragmentWelcome2Binding;
import com.app.doctorapp.utils.EnumUser;
import com.app.doctorapp.view.BaseFragment;
import com.google.android.material.transition.MaterialContainerTransform;
import com.google.android.material.transition.MaterialSharedAxis;

public class FragmentWelcome2 extends BaseFragment {

    private FragViewModelWel2 mViewmodel;
    private FragmentWelcome2Binding mBinding;


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
        preferences.setString(R.string.user_type, USER_PATIENT);

        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.X,true));
        setReenterTransition(new MaterialSharedAxis(MaterialSharedAxis.X,false));
    }

    private GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            if (view == mBinding.llDoctor) {
                mViewmodel.observeUser.set(EnumUser.DOCTOR);
                preferences.setString(R.string.user_type, USER_DOCTOR);

            } else if (view == mBinding.llPatient) {
                mViewmodel.observeUser.set(EnumUser.PATIENT);
                preferences.setString(R.string.user_type, USER_PATIENT);

            } else {

                mActivityWelcome.navigateWel3();
            }
        }
    };
}