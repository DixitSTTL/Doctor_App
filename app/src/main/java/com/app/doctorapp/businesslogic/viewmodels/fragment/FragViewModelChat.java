package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static com.app.doctorapp.utils.ConstantData.COLLECTION_CHAT;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.ChatOuter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelChat extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableArrayList<ChatOuter> observeChatList = new ObservableArrayList<ChatOuter>();


    @Inject
    public FragViewModelChat(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void loadMyChat() {

        db.collection(COLLECTION_CHAT)
                .whereEqualTo("patient_uid", preferences.getString(R.string.user_uid))
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        observeChatList.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            ChatOuter data = document.toObject(ChatOuter.class);
                            observeChatList.add(data);
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                    }
                });

    }


}
