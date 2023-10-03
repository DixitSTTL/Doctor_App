package com.app.doctorapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderTimeBinding;

import java.util.List;

public class AdapterTimeSlot extends RecyclerView.Adapter<AdapterTimeSlot.ViewHolder> {

    List<String> itemList;
    GeneralItemClickListener listener;
    int position;

    public AdapterTimeSlot(List<String> itemList, GeneralItemClickListener listener, int position) {
        this.itemList = itemList;
        this.listener = listener;
        this.position = position;

    }

    @NonNull
    @Override
    public AdapterTimeSlot.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderTimeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_time, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTimeSlot.ViewHolder holder, int position) {
        holder.onBind();
    }

    public void setPoistion(int position) {
        this.position = position;
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderTimeBinding binding;

        public ViewHolder(@NonNull HolderTimeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind() {
            binding.setSelected(position == getAdapterPosition() ? true : false);
            binding.setTime(itemList.get(getAdapterPosition()));
            binding.setGeneralItemListener(listener);
            binding.setCurrentPosition(getAdapterPosition());
        }
    }
}
