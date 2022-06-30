package com.cuongpq.basemvp.view.ui.fragment_running;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ItemRunningBinding;
import com.cuongpq.basemvp.model.Car;
public class RunningAdapter extends RecyclerView.Adapter<RunningAdapter.RunningViewHolder>{
    private final IRunning iRunning;
    private boolean showInfo = true;

    public RunningAdapter(IRunning iRunning) {
        this.iRunning = iRunning;
    }

    @NonNull
    @Override
    public RunningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRunningBinding binding = ItemRunningBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RunningViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RunningViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Car car = iRunning.getCar(position);
        holder.binding.txtIdCarRunning.setText(car.getIdCar()+".");
        holder.binding.txtNameCarRunning.setText(car.getNameCar());
        holder.binding.txtNamePersonRunning.setText(car.getNamePerson());
        if (car.getRound()==1){
            holder.binding.txtRound.setText("SS1");
            holder.binding.txtTimeRunning.setText("Start : "+ car.getStart());
        }
        else if (car.getRound()==2){
            holder.binding.txtRound.setText("SS2");
            holder.binding.txtTimeRunning.setText("SS1 : "+ car.getSs1());
        }
        else if (car.getRound()==3){
            holder.binding.txtRound.setText("SS3");
            holder.binding.txtTimeRunning.setText("SS2 : "+ car.getSs2());
        }
        else if (car.getRound()==4){
            holder.binding.txtRound.setText("SS4");
            holder.binding.txtTimeRunning.setText("SS3 : "+ car.getSs3());
        }
        else if (car.getRound()==5){
            holder.binding.txtRound.setText("SS5");
            holder.binding.txtTimeRunning.setText("SS4 : "+ car.getSs4());
        }
        else if (car.getRound()==6){
            holder.binding.txtRound.setText("SS6");
            holder.binding.txtTimeRunning.setText("SS5 : "+ car.getSs5());
        }
        else if (car.getRound()==7){
            holder.binding.txtRound.setText("Finnish");
            holder.binding.txtTimeRunning.setText("SS6 : "+ car.getSs6());
        }
        else if (car.getRound()==8){
            holder.binding.txtRound.setText("Finish");
            holder.binding.txtTimeRunning.setText("Finish");
        }
        holder.binding.btnRun.setOnClickListener(view -> iRunning.onClickRun(position));
        holder.binding.txtStart.setText(car.getStart());
        holder.binding.txtSS1.setText(car.getSs1());
        holder.binding.txtSS2.setText(car.getSs2());
        holder.binding.txtSS3.setText(car.getSs3());
        holder.binding.txtSS4.setText(car.getSs4());
        holder.binding.txtSS5.setText(car.getSs5());
        holder.binding.txtSS6.setText(car.getSs6());
        holder.binding.txtStop.setText(car.getStop());
        holder.binding.tableInfo.setVisibility(View.GONE);
        holder.binding.imgDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showInfo==true){
                    holder.binding.tableInfo.setVisibility(View.VISIBLE);
                    holder.binding.imgDrop.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                    showInfo = false;
                }else {
                    holder.binding.tableInfo.setVisibility(View.GONE);
                    holder.binding.imgDrop.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                    showInfo=true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return iRunning.getCount();
    }
    public interface IRunning{
        int getCount();
        Car getCar(int position);
        void onClickRun(int position);
    }
    public static class RunningViewHolder extends RecyclerView.ViewHolder{
        ItemRunningBinding binding;
        public RunningViewHolder(@NonNull ItemRunningBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
