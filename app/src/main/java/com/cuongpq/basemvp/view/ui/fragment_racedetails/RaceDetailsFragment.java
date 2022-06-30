package com.cuongpq.basemvp.view.ui.fragment_racedetails;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRaceDetailsBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_car.Fragment_Car;
import com.cuongpq.basemvp.view.ui.fragment_running_statist.FragmentRunningStatist;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RaceDetailsFragment extends BaseFragmentMvp<FragmentRaceDetailsBinding,RaceDetailsPresenter> implements IRaceDetailsView , RaceDetailsAdapter.IRaceDetails {
    private Race race;
    private RaceDetailsAdapter adapter;
    @Override
    public int getMainLayout() {
        return R.layout.fragment_race_details;
    }

    @Override
    protected void initView() {
        super.initView();
        race = (Race) getArguments().getSerializable("race");
        binding.txtNameRaceDetails.setText(race.getNameRace());
        presenter = new RaceDetailsPresenter(this);
        presenter.initPresenter();
    }

    @Override
    public void onClickListenner() {
        binding.btnAddCarDetails.setOnClickListener(view -> {
            Fragment_Car carFragment = new Fragment_Car();
            Bundle bundle = new Bundle();
            bundle.putInt("raceID",race.getIdRace());
            carFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content,carFragment);
            fragmentTransaction.addToBackStack(Fragment_Car.TAG);
            fragmentTransaction.commit();
        });
        binding.imgBack.setOnClickListener(view -> getFragmentManager().popBackStack());
        binding.btnRunning.setOnClickListener(view -> {
//            RunningFragment runningFragment = new RunningFragment();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("race",race);
//            runningFragment.setArguments(bundle);
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.content,runningFragment);
//            fragmentTransaction.addToBackStack(RunningFragment.TAG);
//            fragmentTransaction.commit();
            FragmentRunningStatist fragmentRunningStatist = new FragmentRunningStatist();
            Bundle bundle = new Bundle();
            bundle.putSerializable("race",race);
            fragmentRunningStatist.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content,fragmentRunningStatist);
            fragmentTransaction.addToBackStack(FragmentRunningStatist.TAG);
            fragmentTransaction.commit();
        });
    }

    @Override
    public void initRecylerView() {
        adapter = new RaceDetailsAdapter(this);
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
    public void Toat(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context onGetContext() {
        return getActivity();
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        return simpleDateFormat.format(date);
    }

    @Override
    public int getCount() {
        return presenter.getCarArrayList().size();
    }

    @Override
    public Car getCar(int position) {
        return presenter.getCarArrayList().get(position);
    }

    @Override
    public void onClickStart(int position) {
        presenter.onClickStart(position,race.getIdRace(),getTime());
        Toat("Start successfully !");
        initRecylerView();
    }

}
