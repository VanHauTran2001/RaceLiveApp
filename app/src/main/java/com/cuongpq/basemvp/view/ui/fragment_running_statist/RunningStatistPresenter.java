package com.cuongpq.basemvp.view.ui.fragment_running_statist;

import android.database.Cursor;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RunningStatistPresenter extends BasePresenter implements IRunningStatistPresenter {
    private IRunningStatistView view;
    private ArrayList<Car> carArrayList;
    private SQLiteHelper sqLiteHelper;

    public RunningStatistPresenter(IRunningStatistView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        carArrayList = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(view.onGetContext(),"Database.sqlite",null,1);
        view.onClickListenner();
        view.initRecylerView();
        view.getDataCar();
    }

    @Override
    public ArrayList<Car> carAraayList() {
        return carArrayList;
    }

    @Override
    public void onDataCar(int idRace) {
        carArrayList.clear();
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM Car1 WHERE IdRace = '"+idRace+ "' ");
        while (cursor.moveToNext()){
            int idCar = cursor.getInt(3);
            String nameCar = cursor.getString(4);
            String namePerson = cursor.getString(5);
            int round = cursor.getInt(6);
            String start = cursor.getString(7);
            String ss1 = cursor.getString(8);
            String ss2 = cursor.getString(9);
            String ss3 = cursor.getString(10);
            String ss4 = cursor.getString(11);
            String ss5 = cursor.getString(12);
            String ss6 = cursor.getString(13);
            String stop = cursor.getString(14);
            if (round != 0){
                carArrayList.add(new Car(idCar,nameCar,namePerson,round,start,ss1,ss2,ss3,ss4,ss5,ss6,stop));
            }
        }
    }
}
