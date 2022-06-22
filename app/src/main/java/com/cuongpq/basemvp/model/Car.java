package com.cuongpq.basemvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Car implements Parcelable, Serializable {
    private String id;
    private String nameCar;
    private String namePerson;

    public Car(String id, String nameCar,String namePerson) {
        this.id = id;
        this.nameCar = nameCar;
        this.namePerson = namePerson;
    }

    public Car() {
    }

    protected Car(Parcel in) {
        id = in.readString();
        nameCar = in.readString();
        namePerson = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nameCar);
        parcel.writeString(namePerson);
    }
}
