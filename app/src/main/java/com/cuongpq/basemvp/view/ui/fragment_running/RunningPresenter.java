package com.cuongpq.basemvp.view.ui.fragment_running;

import android.database.Cursor;
import android.widget.Toast;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.cuongpq.basemvp.view.ui.fragment_racedetails.RaceDetailsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RunningPresenter extends BasePresenter implements  IRunningPresenter {
    private IRunningView view;
    private ArrayList<Car> carArrayList;
    private SQLiteHelper sqLiteHelper;
    private String idAccount;
    private FirebaseUser firebaseUser;

    public RunningPresenter(IRunningView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        carArrayList = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(view.onGetContext(),"Database.sqlite",null,1);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAccount = firebaseUser.getUid();
        view.onClickListenner();
        view.initRecylerView();
        view.getDataCar();
    }

    @Override
    public ArrayList<Car> carAraayList() {
        return carArrayList;
    }

    @Override
    public void onClickRun(int position,int idRace) {
        int idCar = carArrayList.get(position).getIdCar();
        int round = carArrayList.get(position).getRound();
        if (round==1){
            sqLiteHelper.QueryData("UPDATE Car1 SET Round = '2',SS1 = '"+ RaceDetailsFragment.getTime() +"' WHERE IdAccount = '" +idAccount+ "' AND IdCar = '" + idCar+ "' AND IdRace = '"+ idRace+"' ");
        }else if (round==2){
            sqLiteHelper.QueryData("UPDATE Car1 SET Round = '3',SS2 = '"+ RaceDetailsFragment.getTime() +"' WHERE IdAccount = '" +idAccount+ "' AND IdCar = '" + idCar+ "' AND IdRace = '"+ idRace+"' ");
        }else if (round==3){
            sqLiteHelper.QueryData("UPDATE Car1 SET Round = '4',SS3 = '"+ RaceDetailsFragment.getTime() +"' WHERE IdAccount = '" +idAccount+ "' AND IdCar = '" + idCar+ "' AND IdRace = '"+ idRace+"' ");
        }else if (round==4){
            sqLiteHelper.QueryData("UPDATE Car1 SET Round = '5',SS4 = '"+ RaceDetailsFragment.getTime() +"' WHERE IdAccount = '" +idAccount+ "' AND IdCar = '" + idCar+ "' AND IdRace = '"+ idRace+"' ");
        }else if (round==5){
            sqLiteHelper.QueryData("UPDATE Car1 SET Round = '6',SS5 = '"+ RaceDetailsFragment.getTime() +"' WHERE IdAccount = '" +idAccount+ "' AND IdCar = '" + idCar+ "' AND IdRace = '"+ idRace+"' ");
        }else if (round==6){
            sqLiteHelper.QueryData("UPDATE Car1 SET Round = '7',SS6 = '"+ RaceDetailsFragment.getTime() +"' WHERE IdAccount = '" +idAccount+ "' AND IdCar = '" + idCar+ "' AND IdRace = '"+ idRace+"' ");
        }else if (round==7){
            sqLiteHelper.QueryData("UPDATE Car1 SET Round = '8',Stop = '"+ RaceDetailsFragment.getTime() +"' WHERE IdAccount = '" +idAccount+ "' AND IdCar = '" + idCar+ "' AND IdRace = '"+ idRace+"' ");
        }else if (round==8){
            Toast.makeText(view.onGetContext(), "Finish", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDataCar(int idRace) {
        carArrayList.clear();
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM Car1 WHERE IdAccount = '" + idAccount +"' "+"AND IdRace = '"+idRace+ "' ");
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
