package com.cuongpq.basemvp.view.ui.fragment_racedetails;

import android.database.Cursor;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RaceDetailsPresenter extends BasePresenter implements IRaceDetailsPresenter {
    private IRaceDetailsView view;
    private FirebaseUser firebaseUser;
    private SQLiteHelper sqLiteHelper;
    private String idAccount;
    private ArrayList<Car> carArrayList;

    public RaceDetailsPresenter(IRaceDetailsView view) {
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
        idAccount = firebaseUser.getUid();
//        Cursor cursor = sqLiteHelper.getData("SELECT * FROM Car1 WHERE IdAccount ='"+idAccount+"' AND IdRace = '"+idRace+"' "+
//        "AND Round = '0' ");
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

    @Override
    public void onClickStart(int position,int idRace,String time) {
        int idCar = carArrayList.get(position).getIdCar();
        sqLiteHelper.QueryData("UPDATE Car1 SET Round = '1' , Start = '"+time+
                "' WHERE IdAccount = '"+idAccount +
                "' AND IdCar = '" + idCar +
                "' AND IdRace = '" + idRace+"' ");
        carArrayList.remove(position);
    }
}
