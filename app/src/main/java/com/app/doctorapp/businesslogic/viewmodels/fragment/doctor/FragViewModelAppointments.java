package com.app.doctorapp.businesslogic.viewmodels.fragment.doctor;

import static com.app.doctorapp.utils.ConstantData.COLLECTION_APPOINTMENTS;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.AppointmentModel;
import com.app.doctorapp.utils.EnumVisibility;
import com.app.doctorapp.utils.Logger;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelAppointments extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableArrayList<AppointmentModel> observeAppoList = new ObservableArrayList<AppointmentModel>();
    public List<AppointmentModel> appoList = new ArrayList<>();

    public ListenerRegistration registration;

    @Inject
    public FragViewModelAppointments(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void loadMyAppointments() {

        observeVisibility.set(EnumVisibility.LOADING);
        registration = db.collection(COLLECTION_APPOINTMENTS)
                .whereEqualTo("doctor_uid", preferences.getString(R.string.user_uid))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        observeVisibility.set(EnumVisibility.VISIBLE);
                        isPageLoaded.set(true);
                        observeAppoList.clear();
                        appoList.clear();
                        for (QueryDocumentSnapshot document : value) {
                            document.getId();
                            AppointmentModel data = document.toObject(AppointmentModel.class);

                            appoList.add(data);
                        }

                        observeAppoList.addAll(appoList);

                    }
                });


    }


}
