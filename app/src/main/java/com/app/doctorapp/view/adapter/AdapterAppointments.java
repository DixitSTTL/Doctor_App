package com.app.doctorapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderAppointmentBinding;
import com.app.doctorapp.databinding.HolderChatCardDoctorBinding;
import com.app.doctorapp.models.AppointmentModel;
import com.app.doctorapp.models.ChatOuter;

import java.util.List;

public class AdapterAppointments extends RecyclerView.Adapter<AdapterAppointments.ViewHolder> {

    List<AppointmentModel> itemList;
    GeneralItemClickListener listener;

    public AdapterAppointments(List<AppointmentModel> itemList, GeneralItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterAppointments.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderAppointmentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_appointment, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAppointments.ViewHolder holder, int position) {

        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderAppointmentBinding binding;

        public ViewHolder(@NonNull HolderAppointmentBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind() {
            binding.setModel(itemList.get(getAdapterPosition()));
            binding.setGeneralItemListener(listener);
            binding.setCurrentPosition(getAdapterPosition());
            binding.cc.setTransitionName("container"+itemList.get(getAdapterPosition()).getPatient_uid());
        }
    }
}
