package com.app.doctorapp.businesslogic.viewmodels.fragment;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelWel1 extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    @Inject
    public FragViewModelWel1(MyApplication myApplication) {
        this.myApplication = myApplication;
    }
}
