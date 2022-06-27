package com.cuongpq.basemvp.view.ui.fragment_race;

import android.database.Cursor;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RacePresenter extends BasePresenter implements IRacePresenter {
    private IRaceView view;
    private ArrayList<Race> listRace;
    private Race race ;
    private String date;
    private String idAccount;
    private FirebaseUser firebaseUser;
    private SQLiteHelper sqLiteHelper;
    public RacePresenter(IRaceView view) {
        this.view = view;
    }


    @Override
    public void onCreateRace(int idRace ,String nameRace) {

        String txtIDRace = String.valueOf(idRace);
        if (nameRace.isEmpty()){
            view.onError("Name Race is not empty !");
        }else if (txtIDRace.isEmpty()) {
            view.onError("ID is not null");
        }else {
            view.onSucessfull("Create Race successfully !");
            sqLiteHelper = new SQLiteHelper(view.onContext(),"Database.sqlite",null,1);
            race = new Race(idRace,nameRace,date);
            listRace.add(race);
            Cursor cursor = sqLiteHelper.getData("SELECT * FROM Race WHERE IdAccount = '"+ idAccount +
                    "' AND IdRace = '" + idRace + "'");
            if (cursor.getCount()<= 0){
                sqLiteHelper.QueryData("INSERT INTO Race VALUES(null,'" + idAccount + "','" +
                        idRace + "','" + nameRace + "','" + date + "')");
                view.onSucessfull("Create race onsuccesfull !");
                view.onMoveToRace();
            }else {
                view.onError("Data is already exist !");
            }
        }
    }

    @Override
    public void onGetDate() {
        Calendar calendar = Calendar.getInstance();
        Date d = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = simpleDateFormat.format(d);
        view.onSetDate(date);
    }

    @Override
    public void onInit() {
        listRace = new ArrayList<>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAccount = firebaseUser.getUid();
        view.onClickListener();
        onGetDate();
        view.onInitToolbar();
    }

    @Override
    public ArrayList<Race> getListRace() {
        return listRace;
    }

    @Override
    public void setListRace(ArrayList<Race> listRaces) {
        this.listRace = listRaces;
    }
    @Override
    public Race getRace() {
        return race;
    }
}
