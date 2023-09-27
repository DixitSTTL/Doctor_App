package com.nike.doctorapp.businesslogic.viewmodels.fragment;

import androidx.databinding.ObservableField;

import com.nike.doctorapp.MyApplication;
import com.nike.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.nike.doctorapp.model.DateModel;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelWel3 extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableField<DateModel> observeDate = new ObservableField<>();

    @Inject
    public FragViewModelWel3(MyApplication myApplication) {
        this.myApplication = myApplication;

        setCurrentDate();


    }

    private void setCurrentDate() {
       int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
       int month = Calendar.getInstance().get(Calendar.MONTH)+1;
       int year = Calendar.getInstance().get(Calendar.YEAR);
        DateModel model = new DateModel(String.valueOf(day),String.valueOf(month),String.valueOf(year));
        observeDate.set(model);
    }
}
