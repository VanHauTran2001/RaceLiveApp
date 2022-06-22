package com.cuongpq.basemvp.view.ui.fragment_signin;

import android.content.Context;

public interface ISigninView {
    void onClickListener();
    void customLogin();
    void onChecked();
    void onSucessfull(String mess);
    void onError(String mess);
    Context onContext();
}
