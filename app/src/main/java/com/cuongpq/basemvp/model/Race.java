package com.cuongpq.basemvp.model;
import java.io.Serializable;

public class Race implements Serializable{
    private int idRace;
    private String nameRace;
    private String dateRace;


    public Race(int idRace, String nameRace, String dateRace) {
        this.idRace = idRace;
        this.nameRace = nameRace;
        this.dateRace = dateRace;
    }

    public Race() {

    }

    public int getIdRace() {
        return idRace;
    }

    public void setIdRace(int idRace) {
        this.idRace = idRace;
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
}
