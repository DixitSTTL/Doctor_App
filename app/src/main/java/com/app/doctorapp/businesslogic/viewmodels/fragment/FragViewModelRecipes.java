package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static com.app.doctorapp.utils.ConstantData.COLLECTION_APPOINTMENTS;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.AppointmentModel;
import com.app.doctorapp.models.PrescripeModel;
import com.app.doctorapp.utils.EnumVisibility;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;

@HiltViewModel
public class FragViewModelRecipes extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableArrayList<AppointmentModel> observeAppoList = new ObservableArrayList<AppointmentModel>();
    public List<AppointmentModel> appoList = new ArrayList<>();

    public ListenerRegistration registration;

    @Inject
    public FragViewModelRecipes(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void loadAllAppointments() {
        observeVisibility.set(EnumVisibility.LOADING);
        db.collection(COLLECTION_APPOINTMENTS)
                .whereEqualTo("patient_uid", preferences.getString(R.string.user_uid))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        observeVisibility.set(EnumVisibility.VISIBLE);

                        observeAppoList.clear();
                        appoList.clear();

                        for (int i = 0; i < value.size(); i++) {
                            DocumentSnapshot document = value.getDocuments().get(i);
                            AppointmentModel data = document.toObject(AppointmentModel.class);
                            data.setPrescribed(new ArrayList<>());

                            callSubList(i, document.getReference());
                            appoList.add(data);
                        }
                        observeAppoList.addAll(appoList);


                    }
                });



    }

    private void callSubList(int i, DocumentReference reference) {
        reference.collection("prescribed")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        List<PrescripeModel> list = new ArrayList<>();

                        for (QueryDocumentSnapshot document : value) {
                            PrescripeModel subData = document.toObject(PrescripeModel.class);
                            list.add(subData);

                        }
                        observeAppoList.get(i).setPrescribed(list);

                    }
                });
    }


}
