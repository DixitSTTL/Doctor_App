package com.app.doctorapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderDoctorBinding;
import com.app.doctorapp.models.UserDoctorModel;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;

public class AdapterDoctors extends RecyclerView.Adapter<AdapterDoctors.ViewHolder> {

    List<QueryDocumentSnapshot> itemList;
    GeneralItemClickListener listener;

    public AdapterDoctors(List<QueryDocumentSnapshot> itemList, GeneralItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterDoctors.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderDoctorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_doctor, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDoctors.ViewHolder holder, int position) {

        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderDoctorBinding binding;

        public ViewHolder(@NonNull HolderDoctorBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind() {
            UserDoctorModel userDoctorModel = itemList.get(getAdapterPosition()).toObject(UserDoctorModel.class);
            binding.setModel(userDoctorModel);
            binding.setGeneralItemListener(listener);
            binding.setCurrentPosition(getAdapterPosition());
            binding.cc.setTransitionName("container"+userDoctorModel.getUser_uid());
        }
    }
}
