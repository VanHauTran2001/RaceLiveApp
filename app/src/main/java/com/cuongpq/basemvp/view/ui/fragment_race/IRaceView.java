package com.cuongpq.basemvp.view.ui.fragment_race;

import android.content.Context;

public interface IRaceView {
    void onClickListener();
    void onSucessfull(String mess);
    void onError(String mess);
    void onSetDate(String date);
    void onInitToolbar();
    Context onContext();
    void onMoveToRace();
}
