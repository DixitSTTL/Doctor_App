package com.app.doctorapp.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.models.CategoryModel;
import com.app.doctorapp.models.DateModel;
import com.app.doctorapp.models.UserDoctorModel;
import com.app.doctorapp.models.UserPatientModel;
import com.app.doctorapp.view.adapter.AdapterCategory;
import com.app.doctorapp.view.adapter.AdapterDate;
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
    @BindingAdapter(value = {"setAdapterDate", "setOnItemClickListener"})
    public static void setAdapterDate(RecyclerView recyclerView, ObservableArrayList<DateModel> list, GeneralItemClickListener generalItemClickListener) {
        AdapterDate adapterDate = new AdapterDate(list, generalItemClickListener);
        recyclerView.setAdapter(adapterDate);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));

    }


}
