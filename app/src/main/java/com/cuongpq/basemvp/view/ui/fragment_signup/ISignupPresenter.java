package com.cuongpq.basemvp.view.ui.fragment_signup;

public interface ISignupPresenter {
   void onLoginSignup(String email,String user,String password,String confirmPassword,String phone);
   void onUnit();
}
