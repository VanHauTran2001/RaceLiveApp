package com.cuongpq.basemvp.view.ui.fragment_profile;

import android.content.Context;

public interface IProfileView {
    void onClickListenner();
    void setUserInformation();
    void onClickRequestPermission();
    void onUpdateUser();
    Context ongetContext();
}
