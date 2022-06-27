package com.cuongpq.basemvp.view.ui.fragment_car;

import android.content.Context;

public interface ICarView {
    void onClickListener();
    void onBackStack();
    Context onContext();
    void onSucessfull(String mess);
    void onError(String mess);
}
