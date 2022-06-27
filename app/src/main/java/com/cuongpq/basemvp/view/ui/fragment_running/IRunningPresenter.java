package com.cuongpq.basemvp.view.ui.fragment_running;

import com.cuongpq.basemvp.model.Car;

import java.util.ArrayList;

public interface IRunningPresenter {
    void onInitPresenter();
    ArrayList<Car> carAraayList();
    void onClickRun(int position,int idRace);
    void onDataCar(int idRace);
}
