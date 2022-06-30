package com.cuongpq.basemvp.view.ui.fragment_signup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SignupPresenter extends BasePresenter implements ISignupPresenter{
    private ISignupView view;
    private FirebaseFirestore fireStore;

    public SignupPresenter(ISignupView view) {
        this.view = view;
    }

    public void onLoginSignup(String email, String user, String password, String confirmPassword, String phone){
        if (TextUtils.isEmpty(email)){
            view.onError("Email can not be empty !");
        }else if(TextUtils.isEmpty(user)){
            view.onError("User can not be empty !");
        }else if(TextUtils.isEmpty(password)){
            view.onError("Password can not be empty !");
        }else if(TextUtils.isEmpty(confirmPassword)){
            view.onError("Confirm Password can not be empty !");
        }else if(TextUtils.isEmpty(phone)){
            view.onError("Phone number can not be empty !");
        }else {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            fireStore = FirebaseFirestore.getInstance();
            ProgressDialog progressDialog = new ProgressDialog(view.onContext());;
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener((Activity) view.onContext(), task -> {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            DocumentReference documentReference = fireStore.collection("Users").document(firebaseUser.getUid());
                            Map<String,Object> userInfo =  new HashMap<>();
                            userInfo.put("Email",email);
                            userInfo.put("UserName",user);
                            userInfo.put("Phone",phone);
                            if (view.onCheckMember()){
                                userInfo.put("isMember","1");
                            }
                            if (view.onCheckStatist()){
                                userInfo.put("isStatist","2");
                            }
                            documentReference.set(userInfo);
                            view.addSuccessfull();
                        }else {
                            view.onError("Register user failed !");
                        }
                    });
        }

    }

    @Override
    public void onUnit() {
        view.onClickListener();
    }

}
