package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static android.content.ContentValues.TAG;
import static com.app.doctorapp.utils.ConstantData.COLLECTION_CATEGORIES;
import static com.app.doctorapp.utils.ConstantData.USER_DOCTOR;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.CategoryModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelHome extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableArrayList<CategoryModel> observeCategoryList = new ObservableArrayList<CategoryModel>();
    public ObservableArrayList<QueryDocumentSnapshot> observeDoctorList = new ObservableArrayList<QueryDocumentSnapshot>();


    @Inject
    public FragViewModelHome(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void loadCategories() {

        db.collection(COLLECTION_CATEGORIES)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        observeCategoryList.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            CategoryModel data = document.toObject(CategoryModel.class);
                            observeCategoryList.add(data);
                            Log.d("vdfbdnbdb", "  " + data.getName() + "  " + data.getImage());

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w(TAG, "Error writing document", e);
                    }
                });


    }

    public void loadDoctors() {

        db.collection(USER_DOCTOR)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        observeDoctorList.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            observeDoctorList.add(document);
                        }

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
