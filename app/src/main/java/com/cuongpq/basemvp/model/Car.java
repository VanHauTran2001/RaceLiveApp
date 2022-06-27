package com.cuongpq.basemvp.model;
import java.io.Serializable;

public class Car implements Serializable {
    private int idCar;
    private String nameCar;
    private String namePerson;
    private int round;
    private String start;
    private String ss1;
    private String ss2;
    private String ss3;
    private String ss4;
    private String ss5;
    private String ss6;
    private String stop;

    public Car(int idCar, String nameCar, String namePerson, int round, String start, String ss1, String ss2, String ss3, String ss4, String ss5, String ss6, String stop) {
        this.idCar = idCar;
        this.nameCar = nameCar;
        this.namePerson = namePerson;
        this.round = round;
        this.start = start;
        this.ss1 = ss1;
        this.ss2 = ss2;
        this.ss3 = ss3;
        this.ss4 = ss4;
        this.ss5 = ss5;
        this.ss6 = ss6;
        this.stop = stop;
    }

    public Car() {
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getSs1() {
        return ss1;
    }

    public void setSs1(String ss1) {
        this.ss1 = ss1;
    }

    public String getSs2() {
        return ss2;
    }

    public void setSs2(String ss2) {
        this.ss2 = ss2;
    }

    public String getSs3() {
        return ss3;
    }

    public void setSs3(String ss3) {
        this.ss3 = ss3;
    }

    public String getSs4() {
        return ss4;
    }

    public void setSs4(String ss4) {
        this.ss4 = ss4;
    }

    public String getSs5() {
        return ss5;
    }

    public void setSs5(String ss5) {
        this.ss5 = ss5;
    }

    public String getSs6() {
        return ss6;
    }

    public void setSs6(String ss6) {
        this.ss6 = ss6;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }
}
