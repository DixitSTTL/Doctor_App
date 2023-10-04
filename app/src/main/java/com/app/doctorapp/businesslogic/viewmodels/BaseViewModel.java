package com.app.doctorapp.businesslogic.viewmodels;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.businesslogic.rx.SchedulerProvider;
import com.app.doctorapp.utils.EnumGender;
import com.app.doctorapp.utils.EnumUser;
import com.app.doctorapp.utils.EnumVisibility;
import com.app.doctorapp.utils.preference.UtilsSharedPreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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
    protected FirebaseAuth mAuth;

    @Inject
    protected FirebaseFirestore db;
    @Inject
    protected UtilsSharedPreferences preferences;
    @Inject
    protected SchedulerProvider mSchedulers;

    public ObservableField<String> observerSnackBarString = new ObservableField<>("");
    public ObservableField<EnumUser> observeUser = new ObservableField<>(EnumUser.PATIENT);
    public ObservableField<EnumGender> observeGender = new ObservableField<>();

    public ObservableField<String> observeName = new ObservableField<>();
    public ObservableField<String> observeEmail = new ObservableField<>();
    public ObservableField<String> observeMobile = new ObservableField<>();
    public ObservableField<String> observePass = new ObservableField<>();
    public ObservableField<EnumVisibility> observeVisibility = new ObservableField<EnumVisibility>();

    @Inject
    public BaseViewModel() {
    }


    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }


}
