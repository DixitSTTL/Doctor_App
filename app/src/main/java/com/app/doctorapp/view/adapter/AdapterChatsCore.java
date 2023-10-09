package com.app.doctorapp.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderChatLeftBinding;
import com.app.doctorapp.databinding.HolderChatRightBinding;
import com.app.doctorapp.models.ChatInSide;

import java.util.List;

public class AdapterChatsCore extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ChatInSide> itemList;
    GeneralItemClickListener listener;

    String sender_id;

    public AdapterChatsCore(List<ChatInSide> itemList, GeneralItemClickListener listener, String sender_id) {
        this.itemList = itemList;
        this.listener = listener;
        this.sender_id = sender_id;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            HolderChatLeftBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_chat_left, parent, false);
            return new LeftHolder(binding);

        }
        HolderChatRightBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_chat_right, parent, false);

        return new RightHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LeftHolder) {
            ((LeftHolder) holder).onBind();
        } else if (holder instanceof RightHolder) {
            ((RightHolder) holder).onBind();
        }


    }

    @Override
    public int getItemViewType(int position) {

        //sender_id === opposite id

        if (itemList.get(position).getSender_uid().equalsIgnoreCase(sender_id)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class LeftHolder extends RecyclerView.ViewHolder {
        HolderChatLeftBinding binding;

        public LeftHolder(@NonNull HolderChatLeftBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind() {
            binding.setModel(itemList.get(getAdapterPosition()));
            binding.setGeneralItemListener(listener);
            binding.setCurrentPosition(getAdapterPosition());
        }
    }

    public class RightHolder extends RecyclerView.ViewHolder {
        HolderChatRightBinding binding;

        public RightHolder(@NonNull HolderChatRightBinding itemView) {
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
