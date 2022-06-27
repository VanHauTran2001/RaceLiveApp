package com.cuongpq.basemvp.view.ui.fragment_race;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRaceBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_racedetails.RaceDetailsFragment;


public class RaceFragment extends BaseFragmentMvp<FragmentRaceBinding,RacePresenter> implements IRaceView {
    @Override
    public int getMainLayout() {
        return R.layout.fragment_race;
    }

    @Override
    protected void initView() {
        super.initView();
        presenter = new RacePresenter(this);
        presenter.onInit();
    }

    @Override
    public void onClickListener() {
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namRace = binding.edtNameRace.getText().toString().trim();
                String txtIdRace = binding.edtIDRace.getText().toString().trim();
                int idRace = Integer.parseInt(txtIdRace);
                presenter.onCreateRace(idRace,namRace);
            }
        });
    }

    @Override
    public void onSucessfull(String mess) {
        Toast.makeText(getActivity(),mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String mess) {
        Toast.makeText(getActivity(),mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetDate(String date) {
        binding.txtDate.setText(date);
    }

    @Override
    public void onInitToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolBarRace);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolBarRace.setTitle("Create Race");
        binding.toolBarRace.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public Context onContext() {
        return getActivity();
    }

    @Override
    public void onMoveToRace() {
        RaceDetailsFragment raceDetailsFragment = new RaceDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("race",presenter.getRace());
        raceDetailsFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, raceDetailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
