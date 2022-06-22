package com.cuongpq.basemvp.view.ui.fragment_signin;


import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.cuongpq.basemvp.view.ui.fragment_signup.ISignupView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninPresenter extends BasePresenter implements ISigninPresenter {
    private ISigninView view;

    public SigninPresenter(ISigninView view) {
        this.view = view;
    }

    @Override
    public void onInit() {
        view.onClickListener();
    }

    @Override
    public void onLogin(String email, String password) {
        if (TextUtils.isEmpty(email)){
            view.onError("User can not be empty !");
        }else if(TextUtils.isEmpty(password)){
            view.onError("Password can not be empty !");
        }else {
            FirebaseAuth Auth = FirebaseAuth.getInstance();
            ProgressDialog progressDialog = new ProgressDialog(view.onContext());;
            progressDialog.show();
            Auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener((Activity) view.onContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()){
                                view.onSucessfull("Login successfully !");
                            }else {
                                view.onError("Login failed !");
                            }
                        }
                    });
        }
    }
}
