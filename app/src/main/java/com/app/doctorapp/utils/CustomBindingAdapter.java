package com.app.doctorapp.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.models.CategoryModel;
import com.app.doctorapp.models.ChatInSide;
import com.app.doctorapp.models.ChatOuter;
import com.app.doctorapp.view.adapter.AdapterCategory;
import com.app.doctorapp.view.adapter.AdapterChatsCore;
import com.app.doctorapp.view.adapter.AdapterChatsHome;
import com.app.doctorapp.view.adapter.AdapterDoctors;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class CustomBindingAdapter {

    @BindingAdapter(value = {"setGlideImage"})
    public static void setGlideImage(ImageView imageView, String url) {
        if (imageView != null) {
            Glide.with(imageView.getContext()).load(url)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(imageView);

        }
    }

    @BindingAdapter(value = {"setAdapterCategory", "setOnItemClickListener"})
    public static void setAdapterCategory(RecyclerView recyclerView, ObservableArrayList<CategoryModel> list, GeneralItemClickListener generalItemClickListener) {
        AdapterCategory adapterHome = new AdapterCategory(list, generalItemClickListener);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @BindingAdapter(value = {"setAdapterHomeDoctors", "setOnItemClickListener"})
    public static void setAdapterHomeDoctors(RecyclerView recyclerView, ObservableArrayList<QueryDocumentSnapshot> list, GeneralItemClickListener generalItemClickListener) {
        AdapterDoctors adapterHome = new AdapterDoctors(list, generalItemClickListener);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));

    }

    @BindingAdapter(value = {"setAdapterChat", "setOnItemClickListener"})
    public static void setAdapterChat(RecyclerView recyclerView, ObservableArrayList<ChatOuter> list, GeneralItemClickListener generalItemClickListener) {
        AdapterChatsHome adapterHome = new AdapterChatsHome(list, generalItemClickListener);
        recyclerView.setAdapter(adapterHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));

    }

    @BindingAdapter(value = {"setAdapterChatCore", "setSenderUId","setOnItemClickListener"})
    public static void setAdapterChatCore(RecyclerView recyclerView, ObservableArrayList<ChatInSide> list, String sender_id, GeneralItemClickListener generalItemClickListener) {
        AdapterChatsCore adapterChatsCore = new AdapterChatsCore(list, generalItemClickListener,sender_id);
        recyclerView.setAdapter(adapterChatsCore);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));

    }


    @BindingAdapter(value = {"showSnackBarString"}, requireAll = false)
    public static void showSnackBar(View viewLayout, ObservableField<String> snackMessageString) {
        String message = "";
        if (snackMessageString != null && !TextUtils.isEmpty(snackMessageString.get())) {
            message = snackMessageString.get().trim();
            snackMessageString.set("");
        }
        if (!TextUtils.isEmpty(message)) {
            Utils.showSnackBar(viewLayout, message);
        }
    }


}
