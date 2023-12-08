package com.app.doctorapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderChatCardBinding;
import com.app.doctorapp.databinding.HolderChatCardDoctorBinding;
import com.app.doctorapp.models.ChatOuter;

import java.util.List;

public class AdapterChatsDoctor extends RecyclerView.Adapter<AdapterChatsDoctor.ViewHolder> {

    List<ChatOuter> itemList;
    GeneralItemClickListener listener;

    public AdapterChatsDoctor(List<ChatOuter> itemList, GeneralItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterChatsDoctor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderChatCardDoctorBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_chat_card_doctor, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChatsDoctor.ViewHolder holder, int position) {

        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderChatCardDoctorBinding binding;

        public ViewHolder(@NonNull HolderChatCardDoctorBinding itemView) {
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
