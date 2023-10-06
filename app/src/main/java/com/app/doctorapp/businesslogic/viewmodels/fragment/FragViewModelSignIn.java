package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static com.app.doctorapp.utils.ConstantData.USER_DOCTOR;
import static com.app.doctorapp.utils.ConstantData.USER_LOGIN;
import static com.app.doctorapp.utils.ConstantData.USER_PATIENT;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.UserDoctorModel;
import com.app.doctorapp.models.UserPatientModel;
import com.app.doctorapp.utils.EnumVisibility;
import com.app.doctorapp.view.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

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
            observeVisibility.set(EnumVisibility.LOADING);
            mAuth.signInWithEmailAndPassword(observeEmail.get(), observePass.get())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("TAG", "onComplete: " + "onSuccess");
                            observeVisibility.set(EnumVisibility.VISIBLE);

                            saveAllUserDetails(authResult);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onComplete: " + "onFailure");
                            observeVisibility.set(EnumVisibility.VISIBLE);

                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("TAG", "onComplete: " + "onComplete");
                            observeVisibility.set(EnumVisibility.VISIBLE);

                        }
                    });
        }
    }

    private void saveAllUserDetails(AuthResult authResult) {
        observeVisibility.set(EnumVisibility.LOADING);

        db.collection(USER_DOCTOR)
                .document((authResult.getUser().getUid()))
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        observeVisibility.set(EnumVisibility.VISIBLE);

                        if (value.getData() == null) {

                        } else {
                            preferences.setString(R.string.user_login, USER_LOGIN);

                            UserDoctorModel model = value.toObject(UserDoctorModel.class);
                            preferences.setString(R.string.user_email, observeEmail.get());
                            preferences.setString(R.string.user_password, observePass.get());
                            preferences.setString(R.string.user_uid, model.getUser_uid());
                            preferences.setString(R.string.user_name, model.getUser_name());
                            preferences.setString(R.string.user_mobile, model.getUser_mobile());
                            preferences.setString(R.string.user_gender, model.getUser_gender());
                            preferences.setString(R.string.user_type, model.getUser_type());
                            preferences.setString(R.string.user_birthdate, model.getUser_birthdate());

                            preferences.setString(R.string.user_category, model.getUser_category());
                            preferences.setString(R.string.user_Rating, String.valueOf(model.getUser_Rating()));
                            preferences.setString(R.string.user_review_count, (String.valueOf(model.getUser_review_count())));

                            context.startActivity(new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                            observerSnackBarString.set("Login Successfully");
                        }


                    }


                });


        db.collection(USER_PATIENT)
                .document((authResult.getUser().getUid()))
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        observeVisibility.set(EnumVisibility.VISIBLE);

                        if (value.getData() == null) {


                        } else {
                            preferences.setString(R.string.user_login, USER_LOGIN);

                            UserPatientModel model = value.toObject(UserPatientModel.class);
                            preferences.setString(R.string.user_uid, model.getUser_uid());
                            preferences.setString(R.string.user_email, observeEmail.get());
                            preferences.setString(R.string.user_password, observePass.get());
                            preferences.setString(R.string.user_mobile, model.getUser_mobile());
                            preferences.setString(R.string.user_gender, model.getUser_gender());
                            preferences.setString(R.string.user_type, model.getUser_type());
                            preferences.setString(R.string.user_birthdate, model.getUser_birthdate());

                            context.startActivity(new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            observerSnackBarString.set("Login Successfully");

                        }
                    }
                });


//
    }

    private boolean validation() {
        if (TextUtils.isEmpty(observeEmail.get())) {
            observerSnackBarString.set("Please enter email address");

            return false;
        } else if (TextUtils.isEmpty(observePass.get())) {
            observerSnackBarString.set("Please enter password");

            return false;
        }

        return true;
    }
}
