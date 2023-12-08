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
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelSignIn;
import com.app.doctorapp.databinding.FragmentSignInBinding;
import com.app.doctorapp.view.BaseFragment;
import com.google.android.material.transition.MaterialSharedAxis;

public class FragmentSignIn extends BaseFragment {

    private FragmentSignInBinding mBinding;
    private FragViewModelSignIn mViewmodel;

    public FragmentSignIn() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_sign_in, container, false);
        mViewmodel = new ViewModelProvider(mActivityWelcome).get(FragViewModelSignIn.class);


        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    private GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {

            if (view == mBinding.button) {
                mViewmodel.signInUser();
            } else if (view == mBinding.signUpBtn) {
                mActivityWelcome.navigateSignUp();
            }

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setMViewmodel(mViewmodel);
        mBinding.setGeneralClickListener(generalClickListener);

        setEnterTransition(new MaterialSharedAxis(MaterialSharedAxis.X,true));
        setReenterTransition(new MaterialSharedAxis(MaterialSharedAxis.X,false));
    }
}