package com.app.doctorapp.businesslogic.viewmodels.activity;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ViewModelWelcome extends BaseViewModel {

    @Inject
    MyApplication myApplication;


    @Inject
    public ViewModelWelcome(MyApplication myApplication) {
        this.myApplication = myApplication;

    }
}
