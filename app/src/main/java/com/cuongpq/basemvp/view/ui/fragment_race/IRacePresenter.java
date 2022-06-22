package com.cuongpq.basemvp.view.ui.fragment_race;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.model.User;
import com.cuongpq.basemvp.view.base.presenter.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

public interface IRacePresenter extends IBasePresenter {
    void onCreateRace(String nameRace);
    void onAddCar(String idCar,String namCar,String namePerson);
    void onGetDate();
    void onInit();
    ArrayList<Race> getListRace();
    void setListRace(ArrayList<Race> listRaces);
    ArrayList<Car> getListCar();
    void setListCar(ArrayList<Car> listCars);
    Race getRace();
    void onInsertCarToDb(String idCar , String nameCar , String namePerson);
}
