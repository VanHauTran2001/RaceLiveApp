package com.cuongpq.basemvp.view.ui.fragment_signin;


public interface ISigninPresenter {
    void onInit();
    void onLogin(String email,String password);
}
