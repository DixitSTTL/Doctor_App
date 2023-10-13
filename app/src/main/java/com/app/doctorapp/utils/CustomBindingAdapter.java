package com.app.doctorapp.utils;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.models.AppointmentModel;
import com.app.doctorapp.models.CategoryModel;
import com.app.doctorapp.models.PrescripeModel;
import com.app.doctorapp.view.adapter.AdapterCategory;
import com.app.doctorapp.view.adapter.AdapterDoctors;
import com.app.doctorapp.view.adapter.AdapterPreDoctor;
import com.app.doctorapp.view.adapter.AdapterRecipes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Date;
import java.util.List;


public class CustomBindingAdapter {

    public static int[] array_colors = new int[]{R.color.sec_color, R.color.sec_pink, R.color.sec_blue, R.color.sec_orange};

    @BindingAdapter(value = {"setGlideImage"})
    public static void setGlideImage(ImageView imageView, String url) {
        if (imageView != null) {
            Glide.with(imageView.getContext()).load(url)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(imageView);

        }
    }

    @BindingAdapter(value = {"setFormattedDate"})
    public static void setGlideImage(TextView textView, Date date) {
        if (textView != null) {

            String s = "";
            int hour = date.getHours();
            int minuit = date.getMinutes();
            if (minuit < 10) {
                s = "0";
            }
            textView.setText("" + hour + ":" + s + minuit);

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

    @BindingAdapter(value = {"setAdapterPrescription", "setOnItemClickListener"})
    public static void setAdapterPrescription(RecyclerView recyclerView, ObservableArrayList<PrescripeModel> list, GeneralItemClickListener generalItemClickListener) {
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        AdapterPreDoctor adapterPreDoctor = new AdapterPreDoctor(list, generalItemClickListener);
        recyclerView.setAdapter(adapterPreDoctor);
        recyclerView.setRecycledViewPool(viewPool);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));

    }

    @BindingAdapter(value = {"setAdapterPresPatient", "setOnItemClickListener"})
    public static void setAdapterPresPatient(RecyclerView recyclerView, List<PrescripeModel> list, GeneralItemClickListener generalItemClickListener) {
        Log.d("setAdapterPresPatient", "  " + list);
        if (list == null) {
            return;
        }
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        AdapterPreDoctor adapterPreDoctor = new AdapterPreDoctor(list, generalItemClickListener);
        recyclerView.setAdapter(adapterPreDoctor);
        recyclerView.setRecycledViewPool(viewPool);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));

    }

    @BindingAdapter(value = {"setBackgroundColor"})
    public static void setBackgroundColor(ShapeableImageView imageView, int currentPos) {
        if (imageView != null) {
            imageView.setBackgroundResource(array_colors[currentPos % 4]);
        }

    }

    @BindingAdapter(value = {"setAdapterRecipes", "setOnItemClickListener"})
    public static void setAdapterRecipes(RecyclerView recyclerView, ObservableArrayList<AppointmentModel> list, GeneralItemClickListener generalItemClickListener) {
        AdapterRecipes adapterRecipes = new AdapterRecipes(list, generalItemClickListener);
        recyclerView.setAdapter(adapterRecipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));

    }


//    @BindingAdapter(value = {"setAdapterChat", "setOnItemClickListener"})
//    public static void setAdapterChat(RecyclerView recyclerView, ObservableArrayList<ChatOuter> list, GeneralItemClickListener generalItemClickListener) {
//        AdapterChatsHome adapterHome = new AdapterChatsHome(list, generalItemClickListener);
//        recyclerView.setAdapter(adapterHome);
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
//
//    }

//    @BindingAdapter(value = {"setAdapterChatCore", "setSenderUId", "setOnItemClickListener"})
//    public static void setAdapterChatCore(RecyclerView recyclerView, ObservableArrayList<ChatInSide> list, String sender_id, GeneralItemClickListener generalItemClickListener) {
//        LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
//        manager.setStackFromEnd(true);
//        AdapterChatsCore adapterChatsCore = new AdapterChatsCore(list, generalItemClickListener, sender_id);
//        recyclerView.setAdapter(adapterChatsCore);
//        recyclerView.setLayoutManager(manager);
//
//    }


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
