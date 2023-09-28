package com.app.doctorapp.businesslogic.viewmodels.fragment;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelSignIn extends BaseViewModel {
    @Inject
    MyApplication myApplication;


    @Inject
    public FragViewModelSignIn(MyApplication myApplication) {
        this.myApplication = myApplication;

    }


    public void signInUser() {

        if (validation()) {
            mAuth.signInWithEmailAndPassword(observeEmail.get(), observePass.get())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("TAG", "onComplete: " + "onSuccess");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onComplete: " + "onFailure");

                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("TAG", "onComplete: " + "onComplete");

                        }
                    });
        }
    }

    private boolean validation() {
        if (TextUtils.isEmpty(observeEmail.get())) {
            return false;
        } else if (TextUtils.isEmpty(observePass.get())) {
            return false;
        }

        return true;
    }
}
