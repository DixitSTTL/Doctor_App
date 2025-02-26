package com.app.doctorapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderDateBinding;
import com.app.doctorapp.models.DateModel;

import java.util.List;

public class AdapterDate extends RecyclerView.Adapter<AdapterDate.ViewHolder> {

    List<DateModel> itemList;
    GeneralItemClickListener listener;
    int position;

    public AdapterDate(List<DateModel> itemList, GeneralItemClickListener listener, int position) {
        this.itemList = itemList;
        this.listener = listener;
        this.position = position;
    }

    @NonNull
    @Override
    public AdapterDate.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderDateBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_date, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDate.ViewHolder holder, int position) {
        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setPoistion(int position) {
        this.position = position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderDateBinding binding;

        public ViewHolder(@NonNull HolderDateBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind() {

            binding.setSelected(position == getAdapterPosition() ? true : false);
            binding.setModel(itemList.get(getAdapterPosition()));
            binding.setGeneralItemListener(listener);
            binding.setCurrentPosition(getAdapterPosition());
        }
    }
}
