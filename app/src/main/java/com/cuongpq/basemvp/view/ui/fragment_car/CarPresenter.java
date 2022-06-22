package com.cuongpq.basemvp.view.ui.fragment_car;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class CarPresenter extends BasePresenter implements ICarPresenter {
    private ICarView view;


    public CarPresenter(ICarView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {

        view.onClickListener();
//        view.getListCarSuccess();
        view.initRecylerView();
        view.onInitToolbar();


    }
}
