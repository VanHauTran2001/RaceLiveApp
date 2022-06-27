package com.cuongpq.basemvp.view.ui.fragment_running;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRunningBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_racedetails.RaceDetailsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RunningFragment extends BaseFragmentMvp<FragmentRunningBinding,RunningPresenter> implements IRunningView ,RunningAdapter.IRunning{
    private Race race;
    private RunningAdapter adapter;
    public static final String TAG = RunningFragment.class.getName();
    @Override
    public int getMainLayout() {
        return R.layout.fragment_running;
    }

    @Override
    protected void initView() {
        super.initView();
        race = (Race) getArguments().getSerializable("race");
        presenter = new RunningPresenter(this);
        presenter.onInitPresenter();
    }

    @Override
    public void onClickListenner() {
        binding.imgBackRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        binding.txtNameRaceRunning.setText(race.getNameRace());
    }

    @Override
    public void initRecylerView() {
        adapter = new RunningAdapter(this);
        binding.recylerRunning.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recylerRunning.setAdapter(adapter);
    }
    @Override
    public int getCount() {
        return presenter.carAraayList().size();
    }

    @Override
    public Car getCar(int position) {
        return presenter.carAraayList().get(position);
    }

    @Override
    public void onClickRun(int position) {
        presenter.onClickRun(position,race.getIdRace());
        getDataCar();
        initRecylerView();
    }
    @Override
    public void getDataCar() {
        presenter.onDataCar(race.getIdRace());
        initRecylerView();
    }

    @Override
    public Context onGetContext() {
        return getActivity();
    }
}
