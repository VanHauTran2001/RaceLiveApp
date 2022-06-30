package com.cuongpq.basemvp.view.ui.fragment_list_race;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentListRaceBinding;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.activity.Ultils;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_home.HomeFragment;
import com.cuongpq.basemvp.view.ui.fragment_racedetails.RaceDetailsFragment;
import com.cuongpq.basemvp.view.ui.fragment_statist_details.FragmentStatistDetails;

public class ListRaceFragment extends BaseFragmentMvp<FragmentListRaceBinding,ListRacePresenter> implements IListRaceView,ListRaceAdapter.IListRace  {
    private ListRaceAdapter listRaceAdapter;
    @Override
    public int getMainLayout() {
        return R.layout.fragment_list_race;
    }

    @Override
    protected void initView() {
        super.initView();
        disableLoading();
        presenter = new ListRacePresenter(this);
        presenter.initPresenter();
    }

    @Override
    public void onClickListenner() {
        binding.btnBack.setOnClickListener(view -> getFragmentManager().popBackStack());
    }

    @Override
    public void initRecylerView() {
        listRaceAdapter = new ListRaceAdapter(this);
        binding.recylerListRace.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recylerListRace.setAdapter(listRaceAdapter);
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
        if (Ultils.permision==0){
            RaceDetailsFragment raceDetailsFragment = new RaceDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("race",presenter.getListRace().get(position));
            raceDetailsFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content,raceDetailsFragment);
            fragmentTransaction.addToBackStack(HomeFragment.TAG);
            fragmentTransaction.commit();
        }
        if (Ultils.permision==1){
            FragmentStatistDetails fragmentStatistDetails = new FragmentStatistDetails();
            Bundle bundle = new Bundle();
            bundle.putSerializable("race",presenter.getListRace().get(position));
            fragmentStatistDetails.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content,fragmentStatistDetails);
            fragmentTransaction.addToBackStack(FragmentStatistDetails.TAG);
            fragmentTransaction.commit();
        }

    }
}
