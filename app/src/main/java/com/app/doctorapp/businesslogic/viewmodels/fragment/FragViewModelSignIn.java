package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static com.app.doctorapp.utils.ConstantData.USER_DOCTOR;
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
            mAuth.signInWithEmailAndPassword(observeEmail.get(), observePass.get())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d("TAG", "onComplete: " + "onSuccess");

                            saveAllUserDetails(authResult);

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

    private void saveAllUserDetails(AuthResult authResult) {
        db.collection(USER_DOCTOR)
                .document((authResult.getUser().getUid()))
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value.getData() == null) {


                        } else {
                            UserDoctorModel model = value.toObject(UserDoctorModel.class);
                            preferences.setString(R.string.user_email, observeEmail.get());
                            preferences.setString(R.string.user_password, observePass.get());
                            preferences.setString(R.string.user_mobile, model.getUser_mobile());
                            preferences.setString(R.string.user_gender, model.getUser_gender());
                            preferences.setString(R.string.user_type, model.getUser_type());
                            preferences.setString(R.string.user_birthdate, model.getUser_birthdate());

                            preferences.setString(R.string.user_category, model.getUser_category());
                            preferences.setString(R.string.user_Rating, String.valueOf(model.getUser_Rating()));
                            preferences.setString(R.string.user_review_count, (String.valueOf(model.getUser_review_count())));

                            context.startActivity(new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                        }


                    }


                });


        db.collection(USER_PATIENT)
                .document((authResult.getUser().getUid()))
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (value.getData() == null) {


                        } else {

                            Log.d("nvjksnjvnjsv","  "+value);
                            UserPatientModel model = value.toObject(UserPatientModel.class);

                            preferences.setString(R.string.user_email, observeEmail.get());
                            preferences.setString(R.string.user_password, observePass.get());
                            preferences.setString(R.string.user_mobile, model.getUser_mobile());
                            preferences.setString(R.string.user_gender, model.getUser_gender());
                            preferences.setString(R.string.user_type, model.getUser_type());
                            preferences.setString(R.string.user_birthdate, model.getUser_birthdate());

                            context.startActivity(new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                        }
                    }
                });


//
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
