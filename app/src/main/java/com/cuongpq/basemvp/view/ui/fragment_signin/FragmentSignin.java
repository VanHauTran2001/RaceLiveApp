package com.cuongpq.basemvp.view.ui.fragment_signin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentSiginBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;

public class FragmentSignin extends BaseFragmentMvp<FragmentSiginBinding,SigninPresenter> implements ISigninView{
    private int passwordNotVisible = 1;
    private SharedPreferences sharedPreferences;
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


    @Override
    public void onClickListener() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customLogin();
            }
        });
        binding.imgCheckPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChecked();
            }
        });
    }

    @Override
    public void customLogin() {
        String email = binding.edtEmail.getText().toString().trim();
        String passWord = binding.edtPassword.getText().toString().trim();
        if (binding.checkPass.isChecked()){
            Toast.makeText(getActivity(),"Đã lưu tài khoản",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email",email);
            editor.putString("pass",passWord);
            editor.putBoolean("checked",true);
            editor.commit();
        }else {
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
        Intent intent =new Intent(getActivity(),MainActivity.class);
        getActivity().startActivity(intent);
        getActivity().finishAffinity();
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
