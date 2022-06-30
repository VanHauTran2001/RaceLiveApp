package com.cuongpq.basemvp.view.ui.fragment_statist_details;

import android.database.Cursor;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class StatistDetailsPresenter extends BasePresenter implements IStatistDetailsPresenter {
    private IStatistDetailsView view;
    private FirebaseUser firebaseUser;
    private SQLiteHelper sqLiteHelper;
    private ArrayList<Car> carArrayList;

    public StatistDetailsPresenter(IStatistDetailsView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        carArrayList = new ArrayList<>();

        view.initRecylerView();
        view.onClickListenner();
        view.getDataListCar();
        view.check();
    }

    @Override
    public ArrayList<Car> getCarArrayList() {
        return carArrayList;
    }

    @Override
    public void onDataListCar(int idRace) {
        carArrayList.clear();
        sqLiteHelper = new SQLiteHelper(view.onGetContext(),"Database.sqlite",null,1);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM Car1 WHERE IdRace = '"+idRace+"' "+
                "AND Round = '0' ");
        while (cursor.moveToNext()){
            int idCar = cursor.getInt(3);
            String nameCar = cursor.getString(4);
            String namePerson = cursor.getString(5);
            int round = cursor.getInt(6);
            carArrayList.add(new Car(idCar,nameCar,namePerson,round,null,null,null,null,null,null,null,null));
        }
    }
}
