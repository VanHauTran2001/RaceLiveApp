package com.cuongpq.basemvp.view.ui.fragment_car;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentCarBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.DBManager;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;

import java.util.ArrayList;
import java.util.List;

public class CarFragment extends BaseFragmentMvp<FragmentCarBinding,CarPresenter> implements ICarView,CarAdapter.ICar {
    private CarAdapter adapter;
    private Race race = new Race();
    private ArrayList<Car> carList = new ArrayList<>();
    public static final String TAG = CarFragment.class.getName();
    private DBManager db = DBManager.getInstance(getContext());
    public ArrayList<Car> getCarList() {
        return carList;
    }

    public void setCarList(ArrayList<Car> carList) {
        this.carList = carList;
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_car;
    }

    @Override
    protected void initView() {
        super.initView();
        viewLoading();
        if (getArguments()!=null){
            race = (Race) getArguments().getSerializable("race");
            carList = getArguments().getParcelableArrayList("listCar");
            binding.txtDateRace.setText(race.getDateRace());
        }
        if(db!=null){
            carList = (ArrayList<Car>) db.getCars();
        }
        initRecylerView();
        presenter = new CarPresenter(this);
        presenter.onInitPresenter();
    }

    @Override
    public int getCount() {
//        if (presenter.getListCars()==null || presenter.getListCars().size()==0){
//            return 0;
//        }
//        return presenter.getListCars().size();
        return carList.size();
    }

    @Override
    public Car getData(int position) {
//        return presenter.getListCars().get(position);
        return carList.get(position);
    }

    @Override
    public void onClickListener() {

    }

    @Override
    public void initRecylerView() {
        adapter = new CarAdapter(this);
        binding.recylerCar.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recylerCar.setAdapter(adapter);
    }

    @Override
    public void onInitToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolBarCar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolBarCar.setTitle(race.getNameRace());
        binding.toolBarCar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }
//    @Override
//    public void getListCarSuccess() {
//        DBManager db = DBManager.getInstance(getContext());
//        if (carList.size() == 0){
//            carList = (ArrayList<Car>) db.getCars();
//            setCarList((ArrayList<Car>) carList);
//        }
//        binding.recylerCar.getAdapter().notifyDataSetChanged();
//        disableLoading();
//    }
}
