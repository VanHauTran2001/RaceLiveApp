package com.cuongpq.basemvp.view.ui.fragment_statist_details;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.view.base.presenter.IBasePresenter;

import java.util.ArrayList;

public interface IStatistDetailsPresenter extends IBasePresenter {
    void initPresenter();
    ArrayList<Car> getCarArrayList();
    void onDataListCar(int idRace);
}
