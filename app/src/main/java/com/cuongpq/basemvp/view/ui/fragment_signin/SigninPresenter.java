package com.cuongpq.basemvp.view.ui.fragment_signin;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.cuongpq.basemvp.view.base.activity.Ultils;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity2;
import com.cuongpq.basemvp.view.ui.fragment_home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
                    .addOnCompleteListener((Activity) view.onContext(), task -> {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                            FirebaseUser firebaseUser = Auth.getCurrentUser();
                            DocumentReference documentReference = firestore.collection("Users").document(firebaseUser.getUid());
                            documentReference.get().addOnSuccessListener(documentSnapshot -> {
                                if (documentSnapshot.getString("isMember")!=null){
                                    Ultils.permision = 1;
                                    view.onContext().startActivity(new Intent(view.onContext(), MainActivity2.class));
                                }
                                if (documentSnapshot.getString("isStatist")!=null){
                                    Ultils.permision = 2;
                                    view.onContext().startActivity(new Intent(view.onContext(), MainActivity2.class));
                                }if (firebaseUser.getEmail().equals("admin@gmail.com")){
                                    Ultils.permision = 0;
                                    view.onContext().startActivity(new Intent(view.onContext(), MainActivity.class));
                                }
                            });
                        }else {
                            view.onError("Login failed !");
                        }
                    });
        }
    }
}
