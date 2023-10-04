package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static android.content.ContentValues.TAG;
import static com.app.doctorapp.utils.ConstantData.DOCTOR_DETAILS;
import static com.app.doctorapp.utils.ConstantData.USER_DOCTOR;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

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
public class FragViewModelDoctorDetails extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableField<UserDoctorModel> observeMainDetail = new ObservableField<>();
    public ObservableField<DoctorDetailsModel> observeSecondaryDetail = new ObservableField<>();
    public ObservableArrayList<DateModel> observeDateList = new ObservableArrayList<>();
    public ObservableArrayList<String> observeTimeSlot = new ObservableArrayList<>();
    public ObservableInt observeSelectedDate = new ObservableInt(-1);
    public ObservableInt observeSelectedTime = new ObservableInt(-1);


    @Inject
    public FragViewModelDoctorDetails(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void loadData(String UID) {

        loadCurrentDate();
        db.collection(USER_DOCTOR)
                .document(UID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        observeMainDetail.set(documentSnapshot.toObject(UserDoctorModel.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w(TAG, "Error writing document", e);
                    }
                });


        db.collection(DOCTOR_DETAILS)
                .document(UID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        observeSecondaryDetail.set(documentSnapshot.toObject(DoctorDetailsModel.class));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


    }

    private void loadCurrentDate() {
        observeDateList.clear();
        observeTimeSlot.clear();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.HOUR, 24);
            observeDateList.add(new DateModel(
                    String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
                    changeFormat(calendar),
                    String.valueOf(calendar.get(Calendar.YEAR))));
        }

        observeTimeSlot.add("9:00");
        observeTimeSlot.add("11:00");
        observeTimeSlot.add("13:00");
        observeTimeSlot.add("15:00");
        observeTimeSlot.add("17:00");

    }

    private String changeFormat(Calendar calendar) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM");

        return simpleDateFormat.format(calendar.getTimeInMillis());
    }


}
