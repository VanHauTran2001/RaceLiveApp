package com.cuongpq.basemvp.view.ui.fragment_signup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentSignupBinding;
import com.cuongpq.basemvp.model.User;
import com.cuongpq.basemvp.view.base.activity.Ultils;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.mindrot.jbcrypt.BCrypt;

public class FragmentSignup extends BaseFragmentMvp<FragmentSignupBinding, SignupPresenter> implements ISignupView {
    private String email1 , password1;
    private boolean check;
    @Override
    public int getMainLayout() {
        return R.layout.fragment_signup;
    }

    @Override
    protected void initView() {
        super.initView();
        presenter = new SignupPresenter(this);
        presenter.onUnit();
    }

    @Override
    public void onClickListener() {
        binding.btnSignup.setOnClickListener(view -> {
            String email = binding.edtEmail.getText().toString().trim();
            String user = binding.edtUser.getText().toString().trim();
            String password = binding.edtPassword.getText().toString().trim();
            String confirmPassword = binding.edtConfirmPassword.getText().toString().trim();
            String phone = binding.edtPhone.getText().toString().trim();
            presenter.onLoginSignup(email,user,password,confirmPassword,phone);
            //BCrypt
            String pashHash = BCrypt.hashpw(password, BCrypt.gensalt());
            User user1 = new User(email,pashHash);
            FirebaseDatabase.getInstance().getReference("hash").setValue(user1)
                    .addOnSuccessListener(unused -> {
                        onSucessfull("Register user onsuccessfull !");
                    }).addOnFailureListener(e -> onError("Fail!"));
        });
        binding.btnBackSignup.setOnClickListener(view -> getFragmentManager().popBackStack());
        binding.cbMember.setOnCheckedChangeListener((compoundButton, b) -> {
            if (compoundButton.isChecked()){
                binding.cbStatist.setChecked(false);
            }
        });
        binding.cbStatist.setOnCheckedChangeListener((compoundButton, b) -> binding.cbMember.setChecked(false));
    }
    private void getDataUser(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dataLogin",Context.MODE_PRIVATE);
        String emailLogin = sharedPreferences.getString("email","");
        String passwordLogin = sharedPreferences.getString("pass","");
        check = sharedPreferences.getBoolean("checked",false);
        if (check){
            email1 = emailLogin;
            password1 = passwordLogin;
        }
    }
    private void loginAgain(String email , String password){
        FirebaseAuth Auth = FirebaseAuth.getInstance();
        ProgressDialog progressDialog = new ProgressDialog(getActivity());;
        progressDialog.show();
        Auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener((Activity) getActivity(), task -> {
                    progressDialog.dismiss();
                    if (task.isSuccessful()){
                        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                        FirebaseUser firebaseUser = Auth.getCurrentUser();
                        DocumentReference documentReference = firestore.collection("Users").document(firebaseUser.getUid());
                        documentReference.get().addOnSuccessListener(documentSnapshot -> {
                            if (documentSnapshot.getString("isMember")!=null){
                                Ultils.permision = 1;
                                getActivity().startActivity(new Intent(getActivity(), MainActivity2.class));
                            }
                            if (documentSnapshot.getString("isStatist")!=null){
                                Ultils.permision = 2;
                                getActivity().startActivity(new Intent(getActivity(), MainActivity2.class));
                            }if (firebaseUser.getEmail().equals("admin@gmail.com")){
                                Ultils.permision = 0;
                                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                            }
                        });
                    }else {
                        onError("Login failed !");
                    }
                });
    }
    @Override
    public void onSucessfull(String mess) {
        Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String mess) {
        Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context onContext() {
        return getActivity();
    }

    @Override
    public boolean onCheckMember() {
        return binding.cbMember.isChecked();
    }

    @Override
    public boolean onCheckStatist() {
        return binding.cbStatist.isChecked();
    }

    @Override
    public void addSuccessfull() {
        FirebaseAuth.getInstance().signOut();
        getDataUser();
        loginAgain(email1,password1);
    }
}
