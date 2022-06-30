package com.cuongpq.basemvp.view.ui.fragment_running_statist;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRunningBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;

public class FragmentRunningStatist extends BaseFragmentMvp<FragmentRunningBinding,RunningStatistPresenter> implements IRunningStatistView,RunningStatistAdapter.IRunningStatist{
    private Race race;
    private RunningStatistAdapter adapter;
    public static final String TAG = FragmentRunningStatist.class.getName();
    @Override
    public int getMainLayout() {
        return R.layout.fragment_running;
    }

    @Override
    protected void initView() {
        super.initView();
        race = (Race) getArguments().getSerializable("race");
        presenter = new RunningStatistPresenter(this);
        presenter.onInitPresenter();
    }

    @Override
    public void onClickListenner() {
        binding.imgBackRunning.setOnClickListener(view -> getFragmentManager().popBackStack());
        binding.txtNameRaceRunning.setText(race.getNameRace());
    }

    @Override
    public void initRecylerView() {
        adapter = new RunningStatistAdapter(this);
        binding.recylerRunning.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recylerRunning.setAdapter(adapter);
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

    @Override
    public int getCount() {
        return presenter.carAraayList().size();
    }

    @Override
    public Car getCar(int position) {
        return presenter.carAraayList().get(position);
    }
}
