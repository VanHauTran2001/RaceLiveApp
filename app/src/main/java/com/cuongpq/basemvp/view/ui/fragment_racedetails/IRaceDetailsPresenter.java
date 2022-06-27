package com.cuongpq.basemvp.view.ui.fragment_racedetails;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.view.base.presenter.IBasePresenter;

import java.util.ArrayList;

public interface IRaceDetailsPresenter extends IBasePresenter {
    void initPresenter();
    ArrayList<Car> getCarArrayList();
    void onDataListCar(int idRace);
    void onClickStart(int position,int idRace,String time);
}
