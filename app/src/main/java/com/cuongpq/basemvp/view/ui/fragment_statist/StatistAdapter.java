package com.cuongpq.basemvp.view.ui.fragment_statist;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.databinding.ItemListRaceBinding;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.ui.fragment_list_race.ListRaceAdapter;

public class StatistAdapter extends RecyclerView.Adapter<StatistAdapter.StatistViewHolder> {
    private IStatist istatist;

    public StatistAdapter(IStatist istatist) {
        this.istatist = istatist;
    }

    @NonNull
    @Override
    public StatistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListRaceBinding binding = ItemListRaceBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new StatistViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StatistViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Race race = istatist.getListRace(position);
        holder.binding.txtIdRace.setText(race.getIdRace()+".");
        holder.binding.txtNameRace.setText(race.getNameRace());
        holder.binding.txtDate.setText(race.getDateRace());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                istatist.onClickItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return istatist.getCount();
    }


    public interface IStatist{
        int getCount();
        Race getListRace(int position);
        void onClickItem(int position);
    }
    public class StatistViewHolder extends RecyclerView.ViewHolder{
        ItemListRaceBinding binding;
        public StatistViewHolder(@NonNull ItemListRaceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
