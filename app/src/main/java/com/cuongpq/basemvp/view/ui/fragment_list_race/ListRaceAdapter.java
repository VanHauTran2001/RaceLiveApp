package com.cuongpq.basemvp.view.ui.fragment_list_race;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.databinding.ItemListRaceBinding;
import com.cuongpq.basemvp.model.Race;

public class ListRaceAdapter extends RecyclerView.Adapter<ListRaceAdapter.ListRaceViewHolder> {
    private IListRace iListRace;

    public ListRaceAdapter(IListRace iListRace) {
        this.iListRace = iListRace;
    }

    @NonNull
    @Override
    public ListRaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListRaceBinding binding = ItemListRaceBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ListRaceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRaceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Race race = iListRace.getListRace(position);
        holder.binding.txtIdRace.setText(race.getIdRace()+".");
        holder.binding.txtNameRace.setText(race.getNameRace());
        holder.binding.txtDate.setText(race.getDateRace());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iListRace.onClickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iListRace.getCount();
    }

    public interface IListRace{
        int getCount();
        Race getListRace(int position);
        void onClickItem(int position);
    }
    public class ListRaceViewHolder extends RecyclerView.ViewHolder{
        ItemListRaceBinding binding;
        public ListRaceViewHolder(@NonNull ItemListRaceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
