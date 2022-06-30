package com.cuongpq.basemvp.view.ui.fragment_signin;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentSiginBinding;
import com.cuongpq.basemvp.model.User;
import com.cuongpq.basemvp.view.base.activity.Ultils;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;
import com.cuongpq.basemvp.view.ui.fragment_home.HomeFragment;
import com.cuongpq.basemvp.view.ui.fragment_list_race.ListRaceFragment;
import com.cuongpq.basemvp.view.ui.fragment_profile.ProfileFragment;
import com.cuongpq.basemvp.view.ui.fragment_signup.FragmentSignup;
import com.cuongpq.basemvp.view.ui.fragment_statist.FragmentStatist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.mindrot.jbcrypt.BCrypt;
public class FragmentSignin extends BaseFragmentMvp<FragmentSiginBinding,SigninPresenter> implements ISigninView{
    private int passwordNotVisible = 1;
    private SharedPreferences sharedPreferences;
    private String hashPassword;
    @Override
    public int getMainLayout() {
        return R.layout.fragment_sigin;
    }

    @Override
    protected void initView() {
        super.initView();
        presenter = new SigninPresenter(this);
        presenter.onInit();
        sharedPreferences = getActivity().getSharedPreferences("dataLogin",Context.MODE_PRIVATE);
        binding.edtEmail.setText(sharedPreferences.getString("email",""));
        binding.edtPassword.setText(sharedPreferences.getString("pass",""));
        binding.checkPass.setChecked(sharedPreferences.getBoolean("checked",false));
    }
//    private String generateHashedPass(String pass) {
//        // hash a plaintext password using the typical log rounds (10)
//        return BCrypt.hashpw(pass, BCrypt.gensalt());
//    }
    private boolean isValid(String clearTextPassword, String hashedPass) {
        // returns true if password matches hash
        return BCrypt.checkpw(clearTextPassword, hashedPass);
    }
    @Override
    public void onClickListener() {
        binding.btnLogin.setOnClickListener(view -> customLogin());
        binding.imgCheckPass.setOnClickListener(view -> onChecked());
    }
    @SuppressLint("ApplySharedPref")
    @Override
    public void customLogin() {
        String email = binding.edtEmail.getText().toString().trim();
        String passWord = binding.edtPassword.getText().toString().trim();
        Ultils.emailProfile = email;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("hash/passWord").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hashPassword = snapshot.getValue(String.class);
                if (isValid(passWord,hashPassword)){
                    onSucessfull("Onsuccessfull !");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onError("Login fail !");
            }
        });
        if (binding.checkPass.isChecked()){
                    Toast.makeText(getActivity(),"Đã lưu tài khoản",Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email",email);
                    editor.putString("pass",passWord);
                    editor.putBoolean("checked",true);
                    editor.commit();
        }
        else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("email");
                    editor.remove("pass");
                    editor.remove("checked");
                    editor.commit();
                }
                presenter.onLogin(email,passWord);
            }
    @Override
    public void onChecked() {
        if (passwordNotVisible == 1) {
            binding.imgCheckPass.setImageResource(R.drawable.ic_baseline_visibility_off_24);
            binding.edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordNotVisible = 0;
        } else {
            binding.imgCheckPass.setImageResource(R.drawable.ic_check_on);
            binding.edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordNotVisible = 1;
        }
        binding.edtPassword.setSelection(binding.edtPassword.length());
    }
    @Override
    public void onSucessfull(String mess) {
        Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String mess) {
        Toast.makeText(getActivity(),mess,Toast.LENGTH_SHORT).show();
    }
    @Override
    public Context onContext() {
        return getActivity();
    }

}
