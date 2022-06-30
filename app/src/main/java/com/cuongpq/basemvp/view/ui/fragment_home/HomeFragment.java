package com.cuongpq.basemvp.view.ui.fragment_home;

import android.content.Intent;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentHomeBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_race.RaceFragment;
import com.cuongpq.basemvp.view.ui.fragment_signup.FragmentSignup;
import com.cuongpq.basemvp.view.ui.login.activity.LoginActivity;

public class HomeFragment extends BaseFragmentMvp<FragmentHomeBinding,HomePresenter> implements IHomeView {
    public static final String TAG = HomeFragment.class.getName();
    @Override
    public int getMainLayout() {
        return R.layout.fragment_home;
    }
    @Override
    protected void initView() {
        super.initView();
        disableLoading();
        presenter = new HomePresenter(this);
        presenter.onInitPresenter();
    }
    @Override
    public void onClickListener() {
        binding.btnBack.setOnClickListener(view -> startActivity(new Intent(getActivity(), LoginActivity.class)));
        binding.btnCreatRace.setOnClickListener(view -> getFragmentManager().beginTransaction().add(R.id.content,new RaceFragment(),RaceFragment.class.getName()).addToBackStack(null).commit());
        binding.btnAddMember.setOnClickListener(view ->
                getFragmentManager().beginTransaction().add(R.id.content,new FragmentSignup(),FragmentSignup.class.getName()).addToBackStack(null).commit());
    }
}
