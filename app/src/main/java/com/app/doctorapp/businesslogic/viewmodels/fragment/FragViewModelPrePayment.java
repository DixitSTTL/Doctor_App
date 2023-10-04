package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static android.content.ContentValues.TAG;
import static com.app.doctorapp.utils.ConstantData.DOCTOR_DETAILS;
import static com.app.doctorapp.utils.ConstantData.USER_DOCTOR;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.DateModel;
import com.app.doctorapp.models.DoctorDetailsModel;
import com.app.doctorapp.models.UserDoctorModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelPrePayment extends BaseViewModel {
    @Inject
    MyApplication myApplication;
    public ObservableField<UserDoctorModel> observeMainDetail = new ObservableField<>();
    public ObservableField<DoctorDetailsModel> observeSecondaryDetail = new ObservableField<>();
    @Inject
    public FragViewModelPrePayment(MyApplication myApplication) {
        this.myApplication = myApplication;

    }



}
