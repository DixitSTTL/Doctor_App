package com.nike.doctorapp.businesslogic.viewmodels.fragment;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.nike.doctorapp.MyApplication;
import com.nike.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.nike.doctorapp.utils.EnumUser;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelWel2 extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    @Inject
    public FragViewModelWel2(MyApplication myApplication) {
        this.myApplication = myApplication;
    }
}
