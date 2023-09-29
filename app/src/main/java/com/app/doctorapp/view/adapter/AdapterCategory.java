package com.app.doctorapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderCategoryBinding;
import com.app.doctorapp.models.CategoryModel;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    List<CategoryModel> itemList;
    GeneralItemClickListener listener;

    public AdapterCategory(List<CategoryModel> itemList, GeneralItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HolderCategoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_category, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategory.ViewHolder holder, int position) {

        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderCategoryBinding binding;

        public ViewHolder(@NonNull HolderCategoryBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind() {
            binding.setModel(itemList.get(getAdapterPosition()));
        }
    }
}
