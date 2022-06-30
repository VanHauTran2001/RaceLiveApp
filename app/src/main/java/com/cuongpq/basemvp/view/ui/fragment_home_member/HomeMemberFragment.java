package com.cuongpq.basemvp.view.ui.fragment_home_member;

import android.content.Intent;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentHomeMemberBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment_list_race.ListRaceFragment;
import com.cuongpq.basemvp.view.ui.login.activity.LoginActivity;

public class HomeMemberFragment extends BaseFragmentMvp<FragmentHomeMemberBinding,HomeMemberPresenter> implements IHomeMemberView {
    public static final String TAG = HomeMemberFragment.class.getName();
    @Override
    public int getMainLayout() {
        return R.layout.fragment_home_member;
    }

    @Override
    protected void initView() {
        super.initView();
        disableLoading();
        presenter = new HomeMemberPresenter(this);
        presenter.onInitPresenter();
    }

    @Override
    public void onClickListener() {
        binding.btnBack.setOnClickListener(view -> startActivity(new Intent(getActivity(), LoginActivity.class)));
        binding.btnNext.setOnClickListener(view -> getFragmentManager().beginTransaction().add(R.id.frame,new ListRaceFragment(),ListRaceFragment.class.getName()).addToBackStack(null).commit());

    }
}
