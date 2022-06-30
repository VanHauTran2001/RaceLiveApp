package com.cuongpq.basemvp.view.ui.fragment_statist;

import android.database.Cursor;

import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class FragmentStatistPresenter extends BasePresenter implements IStatistPresenter {
    private IStatistView view;
    private ArrayList<Race> raceArrayList;
    private SQLiteHelper sqLiteHelper;

    public FragmentStatistPresenter(IStatistView view) {
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
