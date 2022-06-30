package com.cuongpq.basemvp.view.ui.fragment_statist;

import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.presenter.IBasePresenter;

import java.util.ArrayList;

public interface IStatistPresenter extends IBasePresenter {
    void initPresenter();
    void getDataRace();
    ArrayList<Race> getListRace();
}
