package com.cuongpq.basemvp.view.ui.fragment_car;

import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class CarPresenter extends BasePresenter implements ICarPresenter {
    private final ICarView view;
    private SQLiteHelper sqLiteHelper;
    private String idAccount;
    private FirebaseUser firebaseUser;

    public CarPresenter(ICarView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        view.onClickListener();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAccount = firebaseUser.getUid();

    }

    @Override
    public void onAddCar(int idCar, String nameCar, String namePerson, int idRace) {
        String txtIdCar = String.valueOf(idCar);
        if (txtIdCar.isEmpty()||nameCar.isEmpty()||namePerson.isEmpty()){
            view.onError("Data is not empty !");
        }else {
            view.onSucessfull("Add Car onSuccessfully !");
            sqLiteHelper = new SQLiteHelper(view.onContext(), "Database.sqlite",null,1);
            sqLiteHelper.QueryData("INSERT INTO Car1 VALUES(null,'"+idAccount+"','"+idRace+"','"+idCar+"','"+nameCar+"','"+namePerson+"','0',null,null,null,null,null,null,null,null)");
        }
    }
}
