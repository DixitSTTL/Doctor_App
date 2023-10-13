package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static com.app.doctorapp.utils.ConstantData.COLLECTION_APPOINTMENTS;
import static com.app.doctorapp.utils.ConstantData.COLLECTION_CHAT;
import static com.app.doctorapp.utils.Utils.generateAppointmentUID;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.AppointmentModel;
import com.app.doctorapp.models.ChatOuter;
import com.app.doctorapp.models.DateModel;
import com.app.doctorapp.models.DoctorDetailsModel;
import com.app.doctorapp.models.UserDoctorModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

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

    public void generateAppointment(String timeSlot, DateModel dateModel) {
        AppointmentModel appointmentModel = new AppointmentModel(
                new Date(),
                observeMainDetail.get().getUser_name(),
                observeMainDetail.get().getUser_uid(),
                observeMainDetail.get().getUser_image(),
                preferences.getString(R.string.user_name),
                preferences.getString(R.string.user_uid),
                preferences.getString(R.string.user_image),
                timeSlot,
                dateModel);

        db.collection(COLLECTION_APPOINTMENTS)
                .document(generateAppointmentUID(preferences.getString(R.string.user_uid), observeMainDetail.get().getUser_uid()))
                .set(appointmentModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(myApplication, "Appointment Generated", Toast.LENGTH_SHORT).show();

                        checkChatCollection();
                    }
                });


    }

    private void checkChatCollection() {
        db.collection(COLLECTION_CHAT)
                .whereEqualTo("doctor_uid", observeMainDetail.get().getUser_uid())
                .whereEqualTo("patient_uid", preferences.getString(R.string.user_uid))
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.size() == 0) {
                            createChatCollection();

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    }
                });


    }

    private void createChatCollection() {
        ChatOuter chatOuter = new ChatOuter(
                new Date(),
                preferences.getString(R.string.user_uid),
                preferences.getString(R.string.user_name),
                preferences.getString(R.string.user_image),
                observeMainDetail.get().getUser_uid(),
                observeMainDetail.get().getUser_name(),
                observeMainDetail.get().getUser_category(),
                observeMainDetail.get().getUser_image(),
                ""

        );
        db.collection(COLLECTION_CHAT)
                .add(chatOuter).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                    }
                });


    }


}
