package com.app.doctorapp.view.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.viewmodels.fragment.FragViewModelWel3;
import com.app.doctorapp.databinding.FragmentWelcome3Binding;
import com.app.doctorapp.model.DateModel;
import com.app.doctorapp.utils.EnumGender;
import com.app.doctorapp.view.BaseFragment;

import java.util.Calendar;

public class FragmentWelcome3 extends BaseFragment {

    FragViewModelWel3 mViewmodel;
    FragmentWelcome3Binding mBinding;
    GeneralClickListener generalClickListener = new GeneralClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBinding.llmale) {
                mViewmodel.observeGender.set(EnumGender.MALE);
                preferences.setString(R.string.user_gender, "MALE");

            } else if (view == mBinding.llFemale) {
                mViewmodel.observeGender.set(EnumGender.FEMALE);
                preferences.setString(R.string.user_gender, "FEMALE");

            } else if (view == mBinding.ccBirthday) {

                showDatePicker();

            } else {
                mActivityWelcome.navigateSignIn();
            }
        }
    };


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
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_welcome3, container, false);
        mViewmodel = new ViewModelProvider(getActivity()).get(FragViewModelWel3.class);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setMViewmodel(mViewmodel);
        mBinding.setGeneralClickListener(generalClickListener);
        preferences.setString(R.string.user_birthdate, mViewmodel.observeDate.get().getYear()+""+mViewmodel.observeDate.get().getMonth()+""+mViewmodel.observeDate.get().getDay());

    }

    private void showDatePicker() {

        final Calendar c = Calendar.getInstance();
        // on below line we are getting
        // our day, month and year.
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                // on below line we are passing context.
                mContext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        DateModel model = new DateModel(String.valueOf(dayOfMonth), String.valueOf(monthOfYear + 1), String.valueOf(year));
                        mViewmodel.observeDate.set(model);
                        preferences.setString(R.string.user_birthdate, model.getYear()+""+model.getMonth()+""+model.getDay());

                    }
                },

                year, month, day);

        datePickerDialog.show();

    }
}