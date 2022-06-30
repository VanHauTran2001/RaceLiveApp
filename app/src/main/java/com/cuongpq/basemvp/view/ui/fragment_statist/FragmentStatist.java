package com.cuongpq.basemvp.view.ui.fragment_statist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentStatistBinding;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_home.HomeFragment;
import com.cuongpq.basemvp.view.ui.fragment_racedetails.RaceDetailsFragment;
import com.cuongpq.basemvp.view.ui.fragment_statist_details.FragmentStatistDetails;
import com.cuongpq.basemvp.view.ui.login.activity.LoginActivity;

public class FragmentStatist extends BaseFragmentMvp<FragmentStatistBinding,FragmentStatistPresenter> implements IStatistView,StatistAdapter.IStatist {
    private StatistAdapter adapter;
    @Override
    public int getMainLayout() {
        return R.layout.fragment_statist;
    }

    @Override
    protected void initView() {
        super.initView();
        disableLoading();
        presenter = new FragmentStatistPresenter(this);
        presenter.initPresenter();
    }

    @Override
    public void onClickListenner() {
        binding.btnBack.setOnClickListener(view -> startActivity(new Intent(getActivity(), LoginActivity.class)));
    }

    @Override
    public void initRecylerView() {
        adapter = new StatistAdapter(this);
        binding.recylerListRace.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recylerListRace.setAdapter(adapter);
    }

    @Override
    public Context onContext() {
        return getActivity();
    }

    @Override
    public int getCount() {
        return presenter.getListRace().size();
    }

    @Override
    public Race getListRace(int position) {
        return presenter.getListRace().get(position);
    }

    @Override
    public void onClickItem(int position) {
        FragmentStatistDetails fragmentStatistDetails = new FragmentStatistDetails();
        Bundle bundle = new Bundle();
        bundle.putSerializable("race",presenter.getListRace().get(position));
        fragmentStatistDetails.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragmentStatistDetails);
        fragmentTransaction.addToBackStack(FragmentStatistDetails.TAG);
        fragmentTransaction.commit();
    }
}
