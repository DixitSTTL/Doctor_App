package com.app.doctorapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderChatCardDoctorBinding;
import com.app.doctorapp.databinding.HolderPrescriptionBinding;
import com.app.doctorapp.models.ChatOuter;
import com.app.doctorapp.models.PrescripeModel;

import java.util.List;

public class AdapterPreDoctor extends RecyclerView.Adapter<AdapterPreDoctor.ViewHolder> {

    List<PrescripeModel> itemList;
    GeneralItemClickListener listener;

    public AdapterPreDoctor(List<PrescripeModel> itemList, GeneralItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterPreDoctor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderPrescriptionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_prescription, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPreDoctor.ViewHolder holder, int position) {

        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderPrescriptionBinding binding;

        public ViewHolder(@NonNull HolderPrescriptionBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind() {
            binding.setModel(itemList.get(getAdapterPosition()));
            binding.setGeneralItemListener(listener);
            binding.setCurrentPosition(getAdapterPosition());
        }
    }
}
