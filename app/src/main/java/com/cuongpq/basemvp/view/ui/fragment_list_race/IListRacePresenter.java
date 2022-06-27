package com.cuongpq.basemvp.view.ui.fragment_list_race;

import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.presenter.IBasePresenter;

import java.util.ArrayList;

public interface IListRacePresenter extends IBasePresenter {
    void initPresenter();
    void getDataRace();
    ArrayList<Race> getListRace();
}
