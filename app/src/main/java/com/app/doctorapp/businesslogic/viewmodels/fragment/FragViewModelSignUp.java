package com.app.doctorapp.businesslogic.viewmodels.fragment;

import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
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

    @Inject
    public FragViewModelSignUp(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void setOtp(WelcomeActivity mActivityWelcome) {

        if (validation()) {

            PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
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
            Toast.makeText(myApplication, "Please enter name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(observeMobile.get())) {
            Toast.makeText(myApplication, "Please enter mobile number", Toast.LENGTH_SHORT).show();

            return false;
        } else if (observeMobile.get().length() != 10) {
            Toast.makeText(myApplication, "Please enter proper mobile number", Toast.LENGTH_SHORT).show();

            return false;
        } else if (TextUtils.isEmpty(observeEmail.get())) {
            Toast.makeText(myApplication, "Please enter email address", Toast.LENGTH_SHORT).show();

            return false;
        } else if (observePass.get().length() < 6) {
            Toast.makeText(myApplication, "Please enter password", Toast.LENGTH_SHORT).show();

            return false;
        } else if (!observeCheck.get()) {
            Toast.makeText(myApplication, "Please accept terms and privacy policies", Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;
    }

    private void setLocalData() {
        preferences.setString(R.string.user_mobile, observeMobile.get());
        preferences.setString(R.string.user_email, observeEmail.get());
        preferences.setString(R.string.user_name, observeName.get());
        preferences.setString(R.string.user_password, observePass.get());
    }


}
