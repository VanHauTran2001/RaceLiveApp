package com.cuongpq.basemvp.view.ui.fragment_car;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ItemCarBinding;
import com.cuongpq.basemvp.model.Car;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>{
    private ICar iCar;

    public CarAdapter(ICar iCar) {
        this.iCar = iCar;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCarBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_car,parent,false);
        return new CarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = iCar.getData(position);
        holder.binding.txtIdCar.setText(car.getId());
        holder.binding.txtNameCar.setText(car.getNameCar());
        holder.binding.txtNamePerson.setText(car.getNamePerson());

    }

    @Override
    public int getItemCount() {
        return iCar.getCount();
    }
    public interface ICar{
        int getCount();
        Car getData(int position);
    }
    public class CarViewHolder extends RecyclerView.ViewHolder{
        private ItemCarBinding binding;
        public CarViewHolder(@NonNull ItemCarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
