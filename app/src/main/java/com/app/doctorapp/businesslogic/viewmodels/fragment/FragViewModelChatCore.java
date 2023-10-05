package com.app.doctorapp.businesslogic.viewmodels.fragment;

import static com.app.doctorapp.utils.ConstantData.COLLECTION_CHAT;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.app.doctorapp.MyApplication;
import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.viewmodels.BaseViewModel;
import com.app.doctorapp.models.ChatInSide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FragViewModelChatCore extends BaseViewModel {
    @Inject
    MyApplication myApplication;

    public ObservableArrayList<ChatInSide> observeChatList = new ObservableArrayList<ChatInSide>();
    public ObservableField<String> observeChatText = new ObservableField<String>();
    public ObservableField<String> observeUsername = new ObservableField<String>();
    public ObservableField<String> observeUserImage = new ObservableField<String>();

    public String doctor_uid;
    String chatList_uid;

    @Inject
    public FragViewModelChatCore(MyApplication myApplication) {
        this.myApplication = myApplication;

    }

    public void sendMessage() {

        if (TextUtils.isEmpty(observeChatText.get())) {
            observerSnackBarString.set("Please enter message");
        } else {
            ChatInSide chat = new ChatInSide(new Date(), preferences.getString(R.string.user_uid), observeChatText.get());
            db.collection(COLLECTION_CHAT)
                    .document(chatList_uid)
                    .collection("messages")
                    .add(chat)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            observeChatText.set("");


                        }
                    });
        }

    }


    public void loadChatOuter(String doctor_uid) {
        this.doctor_uid = doctor_uid;

      /*  Query query1 = db.collection("CHAT").whereEqualTo("sender_email", "dixit@mechodal.com");
        Query query2 = db.collection("CHAT").whereEqualTo("receiver_email", "dixit@mechodal.com");

        Task<QuerySnapshot> task1 = query1.get();
        Task<QuerySnapshot> task2 = query2.get();

        Tasks.whenAllSuccess(task1, task2).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
            @Override
            public void onSuccess(List<Object> objects) {
                QuerySnapshot doc = (QuerySnapshot) objects.get(0);
                QuerySnapshot doc2 = (QuerySnapshot) objects.get(1);

                for (QueryDocumentSnapshot document : doc) {
//                    Log.d("chatTesting", "  " + document.get("sender_email") + "  " + document.get("receiver_email") + "  ");
//                    Log.d("chatTesting", "  " + document.get("sender_name") + "  " + document.get("receiver_name"));
//                    Log.d("chatTesting", "  " + document.get("sender_image") + "  " + document.get("receiver_image"));
//                    Log.d("chatTesting", "  " + document.get("created"));
//                    Log.d("chatTesting", "  " + document.getDocuments().get(0));

                }

                for (QueryDocumentSnapshot document : doc2) {
//                    Log.d("chatTesting", "  " + document.get("sender_email") + "  " + document.get("receiver_email") + "  ");
//                    Log.d("chatTesting", "  " + document.get("sender_name") + "  " + document.get("receiver_name"));
//                    Log.d("chatTesting", "  " + document.get("sender_image") + "  " + document.get("receiver_image"));
//                    Log.d("chatTesting", "  " + document.get("created"));
//                    Log.d("chatTesting", "  " + document.getDocuments().get(0));

                }
            }
        });*/

        db.collection(COLLECTION_CHAT)
                .whereEqualTo("patient_uid", preferences.getString(R.string.user_uid))
                .whereEqualTo("doctor_uid", doctor_uid)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        chatList_uid = queryDocumentSnapshots.getDocuments().get(0).getId();
                        observeUsername.set((String) queryDocumentSnapshots.getDocuments().get(0).get("doctor_name"));
                        observeUserImage.set((String) queryDocumentSnapshots.getDocuments().get(0).get("doctor_image"));
                        loadChatInSide();

                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {

                            Log.d("chatTesting", "  " + document.get("sender_email") + "  " + document.get("receiver_email") + "  ");
                            Log.d("chatTesting", "  " + document.get("sender_name") + "  " + document.get("receiver_name"));
                            Log.d("chatTesting", "  " + document.get("sender_image") + "  " + document.get("receiver_image"));
                            Log.d("chatTesting", "  " + document.get("created"));

                        }


                    }
                });


    }

    private void loadChatInSide() {

        db.collection(COLLECTION_CHAT)
                .document(chatList_uid)
                .collection("messages")
                .orderBy("time", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        Log.d("loadChatInSide", " onSuccess ");

                        observeChatList.clear();
                        for (QueryDocumentSnapshot documentSnapshot : value) {
                            ChatInSide model = documentSnapshot.toObject(ChatInSide.class);
                            Log.d("loadChatInSide", " onSuccess " + model.getMessage());

                            observeChatList.add(model);
                        }
                    }
                });
    }


}
