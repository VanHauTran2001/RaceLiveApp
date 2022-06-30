package com.cuongpq.basemvp.view.ui.fragment_signup;

import android.content.Context;
import android.widget.CheckBox;

public interface ISignupView {
    void onClickListener();
    void onSucessfull(String mess);
    void onError(String mess);
    Context onContext();
    boolean onCheckMember();
    boolean onCheckStatist();
    void addSuccessfull();
}
