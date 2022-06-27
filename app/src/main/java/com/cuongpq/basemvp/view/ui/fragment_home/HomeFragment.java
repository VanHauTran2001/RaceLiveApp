package com.cuongpq.basemvp.view.ui.fragment_home;

import android.content.Intent;
import android.view.View;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentHomeBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_list_race.ListRaceFragment;
import com.cuongpq.basemvp.view.ui.fragment_race.RaceFragment;
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
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        binding.btnCreatRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().add(R.id.content,new RaceFragment(),RaceFragment.class.getName()).addToBackStack(null).commit();
            }
        });
        binding.btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
