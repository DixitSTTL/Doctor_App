package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static android.content.ContentValues.TAG;
import static com.app.doctorapp.utils.ConstantData.USER_DOCTOR;
import static com.app.doctorapp.utils.ConstantData.USER_LOGIN;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.UserDoctorModel;
import com.app.doctorapp.models.UserPatientModel;
import com.app.doctorapp.view.activity.MainActivity;
import com.app.doctorapp.view.activity.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Date;
import java.util.Random;

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
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        uploadUserDetails(authResult.getUser().getUid());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }

    private void uploadUserDetails(String uid) {
        Object obj = null;
        if (preferences.getString(R.string.user_type).equals(USER_DOCTOR)) {
            UserDoctorModel model = new UserDoctorModel(
                    preferences.getString(R.string.user_name),
                    preferences.getString(R.string.user_email),
                    preferences.getString(R.string.user_birthdate),
                    preferences.getString(R.string.user_gender),
                    preferences.getString(R.string.user_type),
                    preferences.getString(R.string.user_mobile),
                    "https://firebasestorage.googleapis.com/v0/b/doctor-app-1e65e.appspot.com/o/doctors_image%2Fimage%20Dr.%20Floyd%20Miles.png?alt=media&token=ee7d27bd-aa32-4a36-87ae-27c8e8b713a4",
                    (new Random().nextInt(5)) + 0.5f,
                    new Random().nextInt(200),
                    "Cardiologyst",
                    new Date());
            obj = model;
        } else {
            UserPatientModel model = new UserPatientModel(
                    preferences.getString(R.string.user_name),
                    preferences.getString(R.string.user_email),
                    preferences.getString(R.string.user_birthdate),
                    preferences.getString(R.string.user_gender),
                    preferences.getString(R.string.user_type),
                    preferences.getString(R.string.user_mobile)
                    , new Date());
            obj = model;
        }


        db.collection(preferences.getString(R.string.user_type))
                .document(uid)
                .set(obj)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        preferences.setString(R.string.user_login, USER_LOGIN);
                        Toast.makeText(myApplication, "User created successfully", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w(TAG, "Error writing document", e);
                    }
                });


    }


}
