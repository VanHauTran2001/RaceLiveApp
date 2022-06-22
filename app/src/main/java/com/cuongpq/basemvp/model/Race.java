package com.cuongpq.basemvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Race implements Serializable{
    private String nameRace;
    private String dateRace;
    private ArrayList<Car> listCar;

    public Race(String nameRace, String dateRace, ArrayList<Car> listCar) {
        this.nameRace = nameRace;
        this.dateRace = dateRace;
        this.listCar = listCar;
    }

    public Race() {
    }



    public String getNameRace() {
        return nameRace;
    }

    public void setNameRace(String nameRace) {
        this.nameRace = nameRace;
    }

    public String getDateRace() {
        return dateRace;
    }

    public void setDateRace(String dateRace) {
        this.dateRace = dateRace;
    }

    public ArrayList<Car> getListCar() {
        return listCar;
    }

    public void setListCar(ArrayList<Car> listCar) {
        this.listCar = listCar;
    }

}
