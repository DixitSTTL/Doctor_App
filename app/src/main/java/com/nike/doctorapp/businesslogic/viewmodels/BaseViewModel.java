package com.nike.doctorapp.businesslogic.viewmodels;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;


import com.nike.doctorapp.MyApplication;
import com.nike.doctorapp.businesslogic.rx.SchedulerProvider;
import com.nike.doctorapp.utils.EnumGender;
import com.nike.doctorapp.utils.EnumUser;
import com.nike.doctorapp.utils.preference.UtilsSharedPreferences;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;

@HiltViewModel
public class BaseViewModel extends ViewModel {

    @Inject
    protected MyApplication mApplication;

    @Inject
    protected Context context;

    @Inject
    protected UtilsSharedPreferences preferences;

    @Inject
    protected SchedulerProvider mSchedulers;

    public ObservableField<String> observerSnackBarString = new ObservableField<>("");
    public ObservableField<EnumUser> observeUser = new ObservableField<>(EnumUser.PATIENT);
    public ObservableField<EnumGender> observeGender = new ObservableField<>();

    @Inject
    public BaseViewModel() {
    }


    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }


}
