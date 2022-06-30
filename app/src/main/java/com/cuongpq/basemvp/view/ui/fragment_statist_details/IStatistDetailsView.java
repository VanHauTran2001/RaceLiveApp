package com.cuongpq.basemvp.view.ui.fragment_statist_details;

import android.content.Context;

public interface IStatistDetailsView {
    void onClickListenner();
    void initRecylerView();
    void getDataListCar();
    void check();
    Context onGetContext();
}
