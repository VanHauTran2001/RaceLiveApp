package com.cuongpq.basemvp.view.ui.fragment_racedetails;

import android.content.Context;

public interface IRaceDetailsView {
    void onClickListenner();
    void initRecylerView();
    void getDataListCar();
    void check();
    void Toat(String message);
    Context onGetContext();
}
