package com.cuongpq.basemvp.view.ui.fragment_list_race;

import android.database.Cursor;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;

public class ListRacePresenter extends BasePresenter implements IListRacePresenter {
    private IListRaceView view;
    private ArrayList<Race> raceArrayList;
    private FirebaseUser firebaseUser;
    private SQLiteHelper sqLiteHelper;
    private String idAccount;

    public ListRacePresenter(IListRaceView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        raceArrayList = new ArrayList<>();
        view.initRecylerView();
        view.onClickListenner();
        getDataRace();
    }
    @Override
    public void getDataRace() {
        sqLiteHelper = new SQLiteHelper(view.onContext(),"Database.sqlite",null,1);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAccount = firebaseUser.getUid();
//        Cursor cursor = sqLiteHelper.getData("SELECT * FROM Race WHERE IdAccount = '"+idAccount+"'");
        Cursor cursor = sqLiteHelper.getData("SELECT * FROM Race");
        while (cursor.moveToNext()){
            int idRace = cursor.getInt(2);
            String nameRace = cursor.getString(3);
            String date = cursor.getString(4);
            raceArrayList.add(new Race(idRace,nameRace,date));
        }
    }
    @Override
    public ArrayList<Race> getListRace() {
        return raceArrayList;
    }
}
