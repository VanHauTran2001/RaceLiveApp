package com.cuongpq.basemvp.view.ui.fragment_running;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRunningBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;

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
        initRecylerView();
    }

    @Override
    public void onClickListenner() {
        binding.imgBackRunning.setOnClickListener(view -> getFragmentManager().popBackStack());
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
        //initRecylerView();
    }
    @Override
    public void getDataCar() {
        presenter.onDataCar(race.getIdRace());
        binding.recylerRunning.getAdapter().notifyDataSetChanged();

    }

    @Override
    public Context onGetContext() {
        return getActivity();
    }
}
