package com.app.doctorapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.doctorapp.R;
import com.app.doctorapp.businesslogic.interfaces.GeneralItemClickListener;
import com.app.doctorapp.databinding.HolderRecipesBinding;
import com.app.doctorapp.models.AppointmentModel;

import java.util.List;

public class AdapterRecipes extends RecyclerView.Adapter<AdapterRecipes.ViewHolder> {

    List<AppointmentModel> itemList;
    GeneralItemClickListener listener;

    public AdapterRecipes(List<AppointmentModel> itemList, GeneralItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterRecipes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        HolderRecipesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.holder_recipes, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecipes.ViewHolder holder, int position) {
        holder.onBind();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        HolderRecipesBinding binding;

        public ViewHolder(@NonNull HolderRecipesBinding itemView) {
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
