package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static com.app.doctorapp.utils.ConstantData.COLLECTION_CHAT;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.ChatOuter;
import com.app.doctorapp.utils.EnumVisibility;
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
public class FragViewModelChat extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableArrayList<ChatOuter> observeChatList = new ObservableArrayList<ChatOuter>();
    public List<ChatOuter> chatList = new ArrayList<>();

    public ListenerRegistration registration;

    @Inject
    public FragViewModelChat(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void loadMyChat(Activity activity) {
        observeVisibility.set(EnumVisibility.LOADING);
        registration = db.collection(COLLECTION_CHAT)

                .whereEqualTo("patient_uid", preferences.getString(R.string.user_uid))
                .addSnapshotListener(activity, new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        observeVisibility.set(EnumVisibility.VISIBLE);

                        observeChatList.clear();
                        chatList.clear();
                        for (QueryDocumentSnapshot document : value) {
                            ChatOuter data = document.toObject(ChatOuter.class);
                            Log.d("loadChatOutSide", " onSuccess " + data.getTime());

                            chatList.add(data);
                        }

                        observeChatList.addAll(chatList);

                    }
                });


    }


}
