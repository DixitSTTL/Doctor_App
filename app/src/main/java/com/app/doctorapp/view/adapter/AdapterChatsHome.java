package com.app.doctorapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderChatCardBinding;
import com.app.doctorapp.models.ChatOuter;

import java.util.List;

public class AdapterChatsHome extends RecyclerView.Adapter<AdapterChatsHome.ViewHolder> {

    List<ChatOuter> itemList;
    GeneralItemClickListener listener;

    public AdapterChatsHome(List<ChatOuter> itemList, GeneralItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterChatsHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderChatCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_chat_card, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChatsHome.ViewHolder holder, int position) {

        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderChatCardBinding binding;

        public ViewHolder(@NonNull HolderChatCardBinding itemView) {
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
