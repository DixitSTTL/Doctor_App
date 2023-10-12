package com.app.doctorapp.businesslogic.viewmodels.fragment;

import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.utils.EnumVisibility;
import com.app.doctorapp.view.activity.WelcomeActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelSignUp extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableBoolean observeCheck = new ObservableBoolean(false);
    public ObservableField<String> observeName = new ObservableField<>();
    public ObservableField<String> observeEmail = new ObservableField<>();
    public ObservableField<String> observeMobile = new ObservableField<>();
    public ObservableField<String> observePass = new ObservableField<>();
    @Inject
    public FragViewModelSignUp(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void setOtp(WelcomeActivity mActivityWelcome) {

        if (validation()) {
            observeVisibility.set(EnumVisibility.LOADING);

            PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    observeVisibility.set(EnumVisibility.VISIBLE);

                    mActivityWelcome.navigateOTP(s);

                    setLocalData();


                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {

                }
            };

            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber("+91" + observeMobile.get())            // Phone number to verify
                            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                            .setCallbacks(mCallBack)
                            .setActivity(mActivityWelcome)// OnVerificationStateChangedCallbacks
                            .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        }


    }

    private boolean validation() {

        if (TextUtils.isEmpty(observeName.get())) {
            observerSnackBarString.set("Please enter name");

            return false;
        } else if (TextUtils.isEmpty(observeMobile.get())) {
            observerSnackBarString.set("Please enter mobile number");

            return false;
        } else if (observeMobile.get().length() != 10) {
            observerSnackBarString.set("Please enter proper mobile number");

            return false;
        } else if (TextUtils.isEmpty(observeEmail.get())) {
            observerSnackBarString.set("Please enter email address");

            return false;
        } else if (observePass.get().length() < 6) {
            observerSnackBarString.set("Please enter password");

            return false;
        } else if (!observeCheck.get()) {
            observerSnackBarString.set("Please accept terms and privacy policies");

            return false;
        }

        return true;
    }

    private void setLocalData() {
        preferences.setString(R.string.user_mobile, observeMobile.get());
        preferences.setString(R.string.user_email, observeEmail.get());
        preferences.setString(R.string.user_name, observeName.get());
        preferences.setString(R.string.user_password, observePass.get());
        preferences.setString(R.string.user_image, "https://firebasestorage.googleapis.com/v0/b/doctor-app-1e65e.appspot.com/o/doctors_image%2Fimage%20Dr.%20Floyd%20Miles.png?alt=media&token=ee7d27bd-aa32-4a36-87ae-27c8e8b713a4");
    }


}
