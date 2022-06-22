package com.cuongpq.basemvp.view.ui.fragment_signup;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentSignupBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragment;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;

public class FragmentSignup extends BaseFragmentMvp<FragmentSignupBinding , SignupPresenter> implements ISignupView {

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
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.edtEmail.getText().toString().trim();
                String user = binding.edtUser.getText().toString().trim();
                String password = binding.edtPassword.getText().toString().trim();
                String confirmPassword = binding.edtConfirmPassword.getText().toString().trim();
                String phone = binding.edtPhone.getText().toString().trim();
                presenter.onLoginSignup(email,user,password,confirmPassword,phone);
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
}
