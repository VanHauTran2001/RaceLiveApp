package com.cuongpq.basemvp.view.ui.fragment_race;

import android.content.Intent;
import android.util.Log;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.DBManager;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.cuongpq.basemvp.view.ui.fragment_home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RacePresenter extends BasePresenter implements IRacePresenter {
    private IRaceView view;
    private ArrayList<Race> listRace;
    private ArrayList<Car> listCar;
    private Race race ;
    private String date;
    public RacePresenter(IRaceView view) {
        this.view = view;
    }


    @Override
    public void onCreateRace(String nameRace) {
        if (nameRace.isEmpty()){
            view.onError("Name Race is not empty !");
        }else if (listCar.size()==0) {
            view.onError("Car is not null");
        }else {
            view.onSucessfull("Create Race successfully !");
            race = new Race(nameRace,date,listCar);
            listRace.add(race);
//             onInsertCarToDb(idCar,namCar,"","");
        }
    }

    @Override
    public void onAddCar(String idCar, String namCar,String namePerson) {

        if (idCar.isEmpty()){
            view.onError("ID Car is not empty !");
        }else if (namCar.isEmpty()){
            view.onError("Name Car is not empty !");
        } else {
            view.onSucessfull("Add successfully !");
            listCar.add(new Car(idCar,namCar,namePerson));
        }
    }

    @Override
    public void onGetDate() {
        Calendar calendar = Calendar.getInstance();
        Date d = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = simpleDateFormat.format(d);
        view.onSetDate(date);
    }

    @Override
    public void onInit() {
        listCar = new ArrayList<>();
        listRace = new ArrayList<>();
        race = new Race();
        view.onClickListener();
        onGetDate();
        view.onInitToolbar();
    }

    @Override
    public ArrayList<Race> getListRace() {
        return listRace;
    }

    @Override
    public void setListRace(ArrayList<Race> listRaces) {
        this.listRace = listRaces;
    }

    @Override
    public ArrayList<Car> getListCar() {
        return listCar;
    }

    @Override
    public void setListCar(ArrayList<Car> listCars) {
        this.listCar = listCars;
    }

    @Override
    public Race getRace() {
        return race;
    }

    @Override
    public void onInsertCarToDb(String idCar, String nameCar, String namPerson) {
        DBManager db = DBManager.getInstance(view.onContext());
        if (listCar.size()==0){
            List<Car> listCarsDB = db.getCars();
            setListCar((ArrayList<Car>) listCarsDB);
        }else{
            db.insertListCar(listCar);
            view.onSucessfull("Insert list car to database successfully !");
        }
    }
}
