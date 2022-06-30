package com.cuongpq.basemvp.view.ui.fragment_statist_details;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentStatistDetailsBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.activity.Ultils;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_running.RunningFragment;
import com.cuongpq.basemvp.view.ui.fragment_running_statist.FragmentRunningStatist;

public class FragmentStatistDetails extends BaseFragmentMvp<FragmentStatistDetailsBinding,StatistDetailsPresenter> implements IStatistDetailsView,StatistDetailsAdapter.IStatistDetails {
    private Race race;
    private StatistDetailsAdapter adapter;
    public static String TAG = FragmentStatistDetails.class.getName();
    @Override
    public int getMainLayout() {
        return R.layout.fragment_statist_details;
    }

    @Override
    protected void initView() {
        super.initView();
        race = (Race) getArguments().getSerializable("race");
        binding.txtNameRaceDetails.setText(race.getNameRace());
        presenter = new StatistDetailsPresenter(this);
        presenter.initPresenter();
    }

    @Override
    public void onClickListenner() {
        binding.imgBack.setOnClickListener(view -> getFragmentManager().popBackStack());
        binding.btnRunning.setOnClickListener(view -> {
            if (Ultils.permision==2){
                FragmentRunningStatist fragmentRunningStatist = new FragmentRunningStatist();
                Bundle bundle = new Bundle();
                bundle.putSerializable("race",race);
                fragmentRunningStatist.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragmentRunningStatist);
                fragmentTransaction.addToBackStack(FragmentRunningStatist.TAG);
                fragmentTransaction.commit();
            }
            if (Ultils.permision==1){
                RunningFragment runningFragment = new RunningFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("race",race);
                runningFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame,runningFragment);
                fragmentTransaction.addToBackStack(RunningFragment.TAG);
                fragmentTransaction.commit();
            }

        });
    }

    @Override
    public void initRecylerView() {
        adapter = new StatistDetailsAdapter(this);
        binding.recylerRaceDetails.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recylerRaceDetails.setAdapter(adapter);
    }

    @Override
    public void getDataListCar() {
        presenter.onDataListCar(race.getIdRace());
        initRecylerView();
    }

    @Override
    public void check() {
        if (presenter.getCarArrayList().size()==0){
            binding.txtThongBaoDetails.setVisibility(View.VISIBLE);
        }else {
            binding.txtThongBaoDetails.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public Context onGetContext() {
        return getActivity();
    }

    @Override
    public int getCount() {
        return presenter.getCarArrayList().size();
    }

    @Override
    public Car getCar(int position) {
        return presenter.getCarArrayList().get(position);
    }
}
