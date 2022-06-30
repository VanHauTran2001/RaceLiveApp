package com.cuongpq.basemvp.view.ui.fragment_running_statist;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.view.base.presenter.IBasePresenter;

import java.util.ArrayList;

public interface IRunningStatistPresenter extends IBasePresenter {
    void onInitPresenter();
    ArrayList<Car> carAraayList();
    void onDataCar(int idRace);
}
