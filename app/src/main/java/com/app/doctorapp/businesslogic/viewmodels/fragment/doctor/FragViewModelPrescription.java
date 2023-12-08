package com.app.doctorapp.businesslogic.viewmodels.fragment.doctor;

import static com.app.doctorapp.utils.ConstantData.COLLECTION_APPOINTMENTS;
import static com.app.doctorapp.utils.Utils.generateAppointmentUID;

import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentActivity;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralClickListener;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.PrescripeModel;
import com.app.doctorapp.utils.EnumVisibility;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelPrescription extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableArrayList<PrescripeModel> observePreList = new ObservableArrayList<PrescripeModel>();
    public ObservableField<String> observeMName = new ObservableField<String>();
    public ObservableField<String> observeMDesc = new ObservableField<String>();
    public ObservableField<String> observeMQty = new ObservableField<String>();
    public List<PrescripeModel> preList = new ArrayList<>();

    String patient_uid;

    @Inject
    public FragViewModelPrescription(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void loadMyAppointments(String patient_uid) {
        this.patient_uid = patient_uid;
        observeVisibility.set(EnumVisibility.LOADING);
        db.collection(COLLECTION_APPOINTMENTS).document(generateAppointmentUID(patient_uid, preferences.getString(R.string.user_uid))).collection("prescribed")

                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        observeVisibility.set(EnumVisibility.VISIBLE);
                        observePreList.clear();
                        preList.clear();
                        for (QueryDocumentSnapshot document : value) {
                            PrescripeModel data = document.toObject(PrescripeModel.class);

                            preList.add(data);
                        }

                        observePreList.addAll(preList);
                    }
                });


    }


    public void addItem(GeneralClickListener generalClickListener) {

        if (validation()) {

            PrescripeModel prescripeModel = new PrescripeModel(observeMName.get(), observeMDesc.get(), Integer.parseInt(observeMQty.get()));
            db.collection(COLLECTION_APPOINTMENTS)
                    .document(generateAppointmentUID(patient_uid, preferences.getString(R.string.user_uid)))
                    .collection("prescribed")
                    .add(prescripeModel)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {

                    observerSnackBarString.set("Added Successfully");
                    generalClickListener.onClick(null);

                }
            });
        }


    }

    private boolean validation() {

        if (TextUtils.isEmpty(observeMName.get())) {
            observerSnackBarString.set("Please enter medicine name");
            return false;
        } else if (TextUtils.isEmpty(observeMDesc.get())) {
            observerSnackBarString.set("Please enter description");
            return false;
        } else if (TextUtils.isEmpty(observeMQty.get())) {
            observerSnackBarString.set("Please enter quantity");
            return false;
        }

        return true;
    }
}
