package com.cuongpq.basemvp.view.ui.fragment_racedetails;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.databinding.ItemListRaceBinding;
import com.cuongpq.basemvp.databinding.ItemRaceDetailsBinding;
import com.cuongpq.basemvp.model.Car;

public class RaceDetailsAdapter extends RecyclerView.Adapter<RaceDetailsAdapter.ReceDetailsViewHolder>{
    private final IRaceDetails iRaceDetails;

    public RaceDetailsAdapter(IRaceDetails iRaceDetails) {
        this.iRaceDetails = iRaceDetails;
    }

    @NonNull
    @Override
    public ReceDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRaceDetailsBinding binding = ItemRaceDetailsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ReceDetailsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceDetailsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Car car = iRaceDetails.getCar(position);
        holder.binding.txtIdCar.setText(car.getIdCar()+".");
        holder.binding.txtNameCar.setText(car.getNameCar());
        holder.binding.txtNamePerson.setText(car.getNamePerson());
        holder.binding.btnStart.setOnClickListener(view -> iRaceDetails.onClickStart(position));
    }

    @Override
    public int getItemCount() {
        return iRaceDetails.getCount();
    }
    public interface IRaceDetails{
        int getCount();
        Car getCar(int position);
        void onClickStart(int position);
    }
    public static class ReceDetailsViewHolder extends RecyclerView.ViewHolder{
        ItemRaceDetailsBinding binding;
        public ReceDetailsViewHolder(@NonNull ItemRaceDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
