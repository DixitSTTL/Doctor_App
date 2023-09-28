package com.app.doctorapp.businesslogic.viewmodels.fragment;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.view.activity.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.PhoneAuthProvider;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelOTP extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    @Inject
    public FragViewModelOTP(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    public void verify(WelcomeActivity mActivityWelcome, String finalOTP, String smsCode) {

        mAuth.signInWithCredential(PhoneAuthProvider.getCredential(smsCode, finalOTP))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            createUser();
                            Toast.makeText(mActivityWelcome, "success", Toast.LENGTH_LONG).show();

                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(mActivityWelcome, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void createUser() {

        mAuth.createUserWithEmailAndPassword(preferences.getString(R.string.user_email), preferences.getString(R.string.user_password)) //signup function
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }
}
