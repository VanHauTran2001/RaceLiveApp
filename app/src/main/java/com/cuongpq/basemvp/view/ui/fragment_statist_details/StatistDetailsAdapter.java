package com.cuongpq.basemvp.view.ui.fragment_statist_details;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.databinding.ItemRaceDetailsBinding;
import com.cuongpq.basemvp.databinding.ItemStatistDetailsBinding;
import com.cuongpq.basemvp.model.Car;

public class StatistDetailsAdapter extends RecyclerView.Adapter<StatistDetailsAdapter.StatistDetailsViewHolder>{
    private IStatistDetails iStatistDetails;

    public StatistDetailsAdapter(IStatistDetails iStatistDetails) {
        this.iStatistDetails = iStatistDetails;
    }

    @NonNull
    @Override
    public StatistDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStatistDetailsBinding binding = ItemStatistDetailsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new StatistDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StatistDetailsViewHolder holder, int position) {
        Car car = iStatistDetails.getCar(position);
        holder.binding.txtIdCar.setText(car.getIdCar()+".");
        holder.binding.txtNameCar.setText(car.getNameCar());
        holder.binding.txtNamePerson.setText(car.getNamePerson());
    }
    @Override
    public int getItemCount() {
        return iStatistDetails.getCount();
    }

    public interface IStatistDetails{
        int getCount();
        Car getCar(int position);
    }
    public static class StatistDetailsViewHolder extends RecyclerView.ViewHolder{
        ItemStatistDetailsBinding binding;
        public StatistDetailsViewHolder(@NonNull ItemStatistDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
