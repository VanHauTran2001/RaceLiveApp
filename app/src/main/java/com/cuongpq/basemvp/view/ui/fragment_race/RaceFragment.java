package com.cuongpq.basemvp.view.ui.fragment_race;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRaceBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_car.CarFragment;
import com.cuongpq.basemvp.view.ui.fragment_home.HomeFragment;

import java.util.ArrayList;

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
        binding.btnAndCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idCar = binding.edtIDCar.getText().toString().trim();
                String nameCar = binding.edtNameCar.getText().toString().trim();
                String namePerson = binding.edtNamePerson.getText().toString().trim();
                presenter.onAddCar(idCar,nameCar,namePerson);
                presenter.onInsertCarToDb(idCar,nameCar,namePerson);
            }
        });
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namRace = binding.edtNameRace.getText().toString().trim();
                presenter.onCreateRace(namRace);
                //Send data
                CarFragment carFragment = new CarFragment();
                Bundle bundle = new Bundle();
                if(presenter.getListCar()!=null){
                    bundle.putParcelableArrayList("listCar",presenter.getListCar());
                }
                if(presenter.getRace()!=null){
                    bundle.putSerializable("race",presenter.getRace());
                }
                carFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, carFragment);
                fragmentTransaction.addToBackStack(CarFragment.TAG);
                fragmentTransaction.commit();
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

}
